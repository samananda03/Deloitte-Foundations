package com.cloudthat.bankingapp.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.Instant;

@Entity
@Table(name = "app_user") // Custom table name to avoid conflicts
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Using MySQL-compatible ID generation strategy
    private Long id;

    @Length(min = 0, max = 30)
    private String firstName;

    @Length(min = 0, max = 30)
    private String lastName;

    @Column(unique = true)
    @Length(min = 0, max = 30)
    private String email;  // Renamed from emailId to email

    @Length(min = 6, max = 128)
    private String password;

    @Length(min = 0, max = 10)
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Length(min = 0, max = 255)
    private String profilePicture;

    @Column(name = "is_active")
    private Boolean isActive = false;

    @CreationTimestamp
    @Column(updatable = false) // To ensure createdAt is not modified
    private Instant createdAt;

    @UpdateTimestamp
    private Instant modifiedAt;

    // Custom getter and setter logic (if needed)

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @Length(min = 0, max = 30) String getFirstName() {
        return firstName;
    }

    public void setFirstName(@Length(min = 0, max = 30) String firstName) {
        this.firstName = firstName;
    }

    public @Length(min = 0, max = 30) String getLastName() {
        return lastName;
    }

    public void setLastName(@Length(min = 0, max = 30) String lastName) {
        this.lastName = lastName;
    }

    public @Length(min = 0, max = 30) String getEmail() {
        return email;
    }

    public void setEmail(@Length(min = 0, max = 30) String email) {
        this.email = email;
    }

    public @Length(min = 6, max = 128) String getPassword() {
        return password;
    }

    public void setPassword(@Length(min = 6, max = 128) String password) {
        this.password = password;
    }

    public @Length(min = 0, max = 10) String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@Length(min = 0, max = 10) String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public @Length(min = 0, max = 255) String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(@Length(min = 0, max = 255) String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public Instant getModifiedAt() {
        return modifiedAt;
    }

    public void setModifiedAt(Instant modifiedAt) {
        this.modifiedAt = modifiedAt;
    }
}
