package com.cloudthat.bankingapp.controller;

import com.cloudthat.bankingapp.entity.User;
import com.cloudthat.bankingapp.entity.VerificationToken;
import com.cloudthat.bankingapp.event.RegistrationCompleteEvent;
import com.cloudthat.bankingapp.model.*;
import com.cloudthat.bankingapp.service.CustomUserDetailsService;
import com.cloudthat.bankingapp.service.UserService;
import com.cloudthat.bankingapp.utility.JWTUtility;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class RegistrationController {

    @Autowired
    private JWTUtility jwtUtility;

    @Autowired
    private AuthenticationManager authenticationProvider;

    @Autowired
    private UserService userService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private ApplicationEventPublisher publisher;

    // User Registration endpoint
    @PostMapping("register")
    public ResponseEntity<ApiResponse> registerUser(@RequestBody UserModel userModel, final HttpServletRequest request) {
        // Check if email already exists
        if (userService.existsByEmail(userModel.getEmail())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ApiResponse(false, "Email is already taken!"));
        }

        User user = userService.registerUser(userModel);
        publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
        String verificationLink = applicationUrl(request) + "/verifyRegistration?token=" + userService.getVerificationTokenForUser(user.getId()).getToken();
        final RegisterUserResponse registeredUser = new RegisterUserResponse(verificationLink);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "User created successfully", registeredUser));
    }

    // Login endpoint
    @PostMapping("login")
    public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest jwtRequest) {
        try {
            UsernamePasswordAuthenticationToken unauthenticatedToken = UsernamePasswordAuthenticationToken.unauthenticated(
                    jwtRequest.getUsername(), jwtRequest.getPassword());

            authenticationProvider.authenticate(unauthenticatedToken);

            UserDetails userDetails = customUserDetailsService.loadUserByUsername(jwtRequest.getUsername());
            String token = jwtUtility.generateToken(userDetails);

            return ResponseEntity.ok(new JwtResponse(
                    token,
                    "Token generated successfully",
                    true,
                    userDetails.getUsername(),
                    userDetails.getAuthorities().iterator().next().toString()
            ));

        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JwtResponse(null, "Invalid credentials", false, null, null));
        } catch (NullPointerException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JwtResponse(null, "Username not found", false, null, null));
        } catch (DisabledException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new JwtResponse(null, "User account is disabled", false, null, null));
        }
    }

    // Verify Registration endpoint
    @GetMapping("verifyRegistration")
    public String verifyRegistration(@RequestParam("token") String token) {
        String result = userService.validateVerificationToken(token);
        return result.equalsIgnoreCase("valid") ? "User verified successfully" : "Invalid or expired token";
    }

    // Resend Verification Token
    @GetMapping("resendVerifyToken")
    public String resendVerificationToken(@RequestParam("token") String oldToken, HttpServletRequest httpServletRequest) {
        VerificationToken verificationToken = userService.generateNewVerificationToken(oldToken);
        User user = verificationToken.getUser();
        resendVerificationTokenMail(user, applicationUrl(httpServletRequest), verificationToken);
        return "Verification link sent";
    }

    // Helper method to send verification email (just logging URL here)
    private void resendVerificationTokenMail(User user, String applicationUrl, VerificationToken verificationToken) {
        String url = applicationUrl + "/verifyRegistration?token=" + verificationToken.getToken();
        // Mimicking email sending
        log.info("URL link to verify: {}", url);
    }

    // Helper method to get the full application URL
    private String applicationUrl(HttpServletRequest request) {
        return "http://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
    }
}
