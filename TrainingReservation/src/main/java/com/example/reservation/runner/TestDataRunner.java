package com.example.reservation.runner;

import com.example.reservation.domain.*;
import com.example.reservation.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Profile({"default"})
@Component
@AllArgsConstructor
public class TestDataRunner implements CommandLineRunner {

    private RoleRepository roleRepository;
    private UserRepository userRepository;

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
        userRepository.save(admin);

        Admin admin1 = new Admin();
        admin1.setFirstName("Uros");
        admin1.setLastName("Colic");
        admin1.setUsername("uros10");
        admin1.setEmail("uros@dusan.com");
        admin1.setPassword("1144");
        admin1.setRole(adminRole);
        userRepository.save(admin1);

        Client client = new Client();
        client.setFirstName("Uros");
        client.setLastName("Uros");
        client.setUsername("uros");
        client.setEmail("ucolic3021rn@raf.rs");
        client.setPassword("1111");
        client.setRole(clientRole);
        client.setCardNumber(123456789L);
        client.setNumberOfTrainings(0);
        client.setBanned(false);
        client.setActivated(true);
        userRepository.save(client);

        Client client1 = new Client();
        client1.setFirstName("NPC");
        client1.setLastName("FinalBoss");
        client1.setUsername("npc");
        client1.setEmail("dcolic3121rn@raf.rs");
        client1.setPassword("1111");
        client1.setRole(clientRole);
        client1.setCardNumber(1234567890L);
        client1.setNumberOfTrainings(10);
        client1.setBanned(false);
        client1.setActivated(true);
        userRepository.save(client1);

        Client client2 = new Client();
        client2.setFirstName("a");
        client2.setLastName("a");
        client2.setUsername("a");
        client2.setEmail("a");
        client2.setPassword("a");
        client2.setRole(clientRole);
        client2.setCardNumber(12345675890L);
        client2.setNumberOfTrainings(0);
        client2.setBanned(false);
        client2.setActivated(true);
        userRepository.save(client2);

        Manager manager = new Manager();
        manager.setFirstName("Dusan");
        manager.setLastName("Dusan");
        manager.setUsername("dusan");
        manager.setEmail("dusancolic12@gmail.com");
        manager.setPassword("1111");
        manager.setRole(managerRole);
        manager.setBanned(false);
        manager.setNameOfGym("Gym1");
        manager.setDateOfEmployment(java.time.LocalDate.now());
        userRepository.save(manager);

    }
}
