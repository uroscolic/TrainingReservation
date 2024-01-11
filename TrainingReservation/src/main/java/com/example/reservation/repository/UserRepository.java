package com.example.reservation.repository;
import com.example.reservation.domain.RoleType;
import com.example.reservation.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.role.roleType = ?1")
    Page<User> findByRoleType(RoleType roleType, Pageable pageable);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmailAndPassword(String email, String password);
    Optional<User> findByActivationCode(String activationCode);
}
