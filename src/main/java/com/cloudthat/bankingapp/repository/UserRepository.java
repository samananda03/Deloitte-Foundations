package com.cloudthat.bankingapp.repository;

import com.cloudthat.bankingapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);  // Email is now the correct field
    Boolean existsByEmail(String email);  // Email is now the correct field
}
