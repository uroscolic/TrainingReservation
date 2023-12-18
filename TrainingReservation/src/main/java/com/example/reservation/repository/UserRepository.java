package com.example.reservation.repository;

import com.example.reservation.domain.Client;
import com.example.reservation.domain.Role;
import com.example.reservation.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByRole(Role role);
    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);



}
