package com.cloudthat.bankingapp.model;

import com.cloudthat.bankingapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor // Lombok generates a constructor with all fields
@NoArgsConstructor  // Lombok generates a no-argument constructor
@Getter
@Setter
public class UserProfile {

    // Read-only fields (fields that should not be updated)
    private Long id;
    private String firstName;
    private String email;
    private Role role;

    // Fields allowed to update
    @Length(min = 0, max = 30)
    private String lastName;

    @Length(min = 0, max = 255)
    private String profilePicture;

    @Length(min = 0, max = 10)
    private String phoneNumber;





}
