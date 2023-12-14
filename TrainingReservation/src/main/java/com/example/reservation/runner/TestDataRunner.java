package com.example.reservation.runner;

import com.example.reservation.domain.*;
import com.example.reservation.repository.AdminRepository;
import com.example.reservation.repository.ClientRepository;
import com.example.reservation.repository.RoleRepository;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private AdminRepository adminRepository;

    public TestDataRunner(RoleRepository roleRepository, AdminRepository adminRepository) {
        this.roleRepository = roleRepository;
        this.adminRepository = adminRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role(RoleType.ROLE_ADMIN);
        roleRepository.save(adminRole);
        Admin admin = new Admin();
        admin.setFirstName("Dusan");
        admin.setLastName("Colic");
        admin.setUsername("dusan77");
        admin.setEmail("dusan@uros.com");
        admin.setPassword("1114");
        admin.setRole(adminRole);
        adminRepository.save(admin);
        Admin admin1 = new Admin();
        admin1.setFirstName("Uros");
        admin1.setLastName("Colic");
        admin1.setUsername("uros10");
        admin1.setEmail("uros@dusan.com");
        admin1.setPassword("1144");
        admin1.setRole(adminRole);
        adminRepository.save(admin1);


    }
}
