
package com.cloudthat.bankingapp.model;



import com.cloudthat.bankingapp.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserProfile {
    //Fields Read Only
    private Long id;
    private String firstName;
    private String email;
    private Role role;

    // fields allowed to update
    private String lastName;
    private String profilePicture;
    private String phoneNumber;
}
