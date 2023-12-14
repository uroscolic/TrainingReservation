package com.example.reservation.repository;

import com.example.reservation.domain.Role;
import com.example.reservation.domain.RoleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findRoleByRoleType(RoleType roleType);
}
