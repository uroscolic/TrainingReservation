package com.example.reservation.runner;

import com.example.reservation.domain.*;
import com.example.reservation.repository.AdminRepository;
import com.example.reservation.repository.ClientRepository;
import com.example.reservation.repository.ManagerRepository;
import com.example.reservation.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
@AllArgsConstructor
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private AdminRepository adminRepository;
    private ClientRepository clientRepository;
    private ManagerRepository managerRepository;

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = new Role(RoleType.ROLE_ADMIN);
        Role clientRole = new Role(RoleType.ROLE_CLIENT);
        Role managerRole = new Role(RoleType.ROLE_MANAGER);
        roleRepository.save(managerRole);
        roleRepository.save(clientRole);
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

        Client client = new Client();
        client.setFirstName("Violeta");
        client.setLastName("Colic");
        client.setUsername("violeta");
        client.setEmail("violeta@violeta.com");
        client.setPassword("1111");
        client.setRole(clientRole);
        client.setCardNumber(123456789L);
        client.setNumberOfTrainings(0);
        client.setBlocked(false);
        clientRepository.save(client);

        Manager manager = new Manager();
        manager.setFirstName("Milisav");
        manager.setLastName("Colic");
        manager.setUsername("milisav");
        manager.setEmail("milisav@milisav.com");
        manager.setPassword("1111");
        manager.setRole(managerRole);
        manager.setBlocked(false);
        manager.setNameOfGym("Gym1");
        manager.setDateOfEmployment(java.time.LocalDate.now());
        managerRepository.save(manager);

    }
}
