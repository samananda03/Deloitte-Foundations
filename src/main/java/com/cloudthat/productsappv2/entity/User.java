package com.cloudthat.productsappv2.entity;

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
    private String emailId;

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
}
