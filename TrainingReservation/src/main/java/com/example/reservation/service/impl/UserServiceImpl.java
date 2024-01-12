package com.example.reservation.service.impl;

import com.example.reservation.domain.Client;
import com.example.reservation.domain.Manager;
import com.example.reservation.domain.RoleType;
import com.example.reservation.domain.User;
import com.example.reservation.dto.*;
import com.example.reservation.listener.MessageHelper;
import com.example.reservation.mapper.AdminMapper;
import com.example.reservation.mapper.ClientMapper;
import com.example.reservation.mapper.ManagerMapper;
import com.example.reservation.mapper.UserMapper;
import com.example.reservation.notificationService.EmailDto;
import com.example.reservation.repository.UserRepository;
import com.example.reservation.security.service.TokenService;
import com.example.reservation.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


@Getter
@Setter
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private ClientMapper clientMapper;
    private AdminMapper adminMapper;
    private ManagerMapper managerMapper;
    private UserMapper userMapper;
    private UserRepository userRepository;
    private TokenService tokenService;
    private JmsTemplate jmsTemplate;
    private String emailDestination;
    private MessageHelper messageHelper;

    public UserServiceImpl(ClientMapper clientMapper, AdminMapper adminMapper, ManagerMapper managerMapper,
                           UserMapper userMapper, UserRepository userRepository, TokenService tokenService,
                           JmsTemplate jmsTemplate,
                           @Value("${destination.sendEmails}") String emailDestination,
                           MessageHelper messageHelper) {
        this.clientMapper = clientMapper;
        this.adminMapper = adminMapper;
        this.managerMapper = managerMapper;
        this.userMapper = userMapper;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
        this.jmsTemplate = jmsTemplate;
        this.emailDestination = emailDestination;
        this.messageHelper = messageHelper;
    }

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public ClientDto findClientById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            return clientMapper.clientToClientDto(client);
        }
        throw new RuntimeException("Client not found");
    }

    @Override
    public ManagerDto findManagerById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("Manager not found"));
        if(user instanceof Manager manager){
            return managerMapper.managerToManagerDto(manager);
        }
        throw new RuntimeException("Client not found");
    }

    @Override
    public Page<UserDto> findAllOfRole(Pageable pageable, RoleType roleType) {

        return userRepository.findByRoleType(roleType,pageable).map(userMapper::userToUserDto);

    }
    @Override
    public ClientDto registerClient(ClientCreateDto clientCreateDto) {
        String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        Random random = new Random();
        StringBuilder sb = new StringBuilder(32);
        for (int i = 0; i < 32; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            sb.append(CHARACTERS.charAt(randomIndex));
        }
        client.setActivationCode(sb.toString());
        userRepository.save(client);
        List<String> params = new ArrayList<>();
        params.add(client.getFirstName());
        params.add(client.getLastName());
        params.add("http://localhost:8080/api/client/activate/" + client.getActivationCode());

        jmsTemplate.convertAndSend(emailDestination, messageHelper.createTextMessage(new EmailDto("activation", client.getEmail(), params)));
        return clientMapper.clientToClientDto(client);

    }
    @Override
    public ManagerDto registerManager(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        userRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);

    }
    @Override
    public ClientDto updateClient(ClientUpdateDto clientUpdateDto) {
        User user = userRepository.findByUsername(clientUpdateDto.getOldUsername()).orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            String password = client.getPassword();
            client = clientMapper.clientUpdateDtoToClient(client, clientUpdateDto);
            userRepository.save(client);
            if(!password.equals(client.getPassword()))
            {
                List<String> params = new ArrayList<>();
                params.add(client.getFirstName());
                params.add(client.getLastName());
                jmsTemplate.convertAndSend(emailDestination, messageHelper.createTextMessage(new EmailDto("passwordChange", client.getEmail(), params)));
            }
            return clientMapper.clientToClientDto(client);
        }
        throw new RuntimeException("Client not found");
    }
    @Override
    public ManagerDto updateManager(ManagerUpdateDto managerUpdateDto) {
        User user = userRepository.findByUsername(managerUpdateDto.getOldUsername()).orElseThrow(() -> new RuntimeException("Manager not found"));
        if(user instanceof Manager manager){
            manager = managerMapper.managerUpdateDtoToManager(manager, managerUpdateDto);
            userRepository.save(manager);
            return managerMapper.managerToManagerDto(manager);
        }
        throw new RuntimeException("Manager not found");
    }
    @Override
    public ClientDto banClient(ClientBanDto clientBanDto) {
        User user = userRepository.findByUsername(clientBanDto.getUsername()).orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            client = clientMapper.clientBanDtoToClient(client, clientBanDto);
            userRepository.save(client);
            return clientMapper.clientToClientDto(client);
        }
        throw new RuntimeException("Client not found");
    }
    @Override
    public ManagerDto banManager(ManagerBanDto managerBanDto) {
        User user = userRepository.findByUsername(managerBanDto.getUsername()).orElseThrow(() -> new RuntimeException("Manager not found"));
        if(user instanceof Manager manager){
            manager = managerMapper.managerBanDtoToManager(manager, managerBanDto);
            userRepository.save(manager);
            return managerMapper.managerToManagerDto(manager);
        }
        throw new RuntimeException("Manager not found");
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        //Try to find active user for specified credentials
        User user = userRepository
                .findByEmailAndPassword(tokenRequestDto.getEmail(), tokenRequestDto.getPassword())
                .orElseThrow(() -> new RuntimeException(String
                        .format("User with username: %s and password: %s not found.", tokenRequestDto.getEmail(),
                                tokenRequestDto.getPassword())));
        //Create token payload
        Claims claims = Jwts.claims();
        claims.put("id", user.getId());
        claims.put("role", user.getRole().getRoleType());
        claims.put("email", user.getEmail());
        //Generate token
        if(user instanceof Client client && client.isBanned()){
            throw new RuntimeException("Client is banned");
        }
        if (user instanceof Client client && !client.isActivated()) {
            throw new RuntimeException("Client is not activated");
        }
        if(user instanceof Manager manager && manager.isBanned()){
            throw new RuntimeException("Manager is banned");
        }
        return new TokenResponseDto(tokenService.generate(claims));
    }

    @Override
    public void incrementReservationCount(IncrementReservationCountDto incrementReservationCountDto) {
        User user = userRepository.findById(incrementReservationCountDto.getUserId())
                    .orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            client.setNumberOfTrainings(client.getNumberOfTrainings() + 1);
            userRepository.save(client);
        }
        else{
            throw new RuntimeException("Client not found");
        }
    }

    @Override
    public void decrementReservationCount(DecrementReservationCountDto decrementReservationCountDto) {
        User user = userRepository.findById(decrementReservationCountDto.getUserId())
                .orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            client.setNumberOfTrainings(client.getNumberOfTrainings() - 1);
            userRepository.save(client);
        }
        else{
            throw new RuntimeException("Client not found");
        }
    }

    @Override
    public void activateClient(String activationCode) {
        User user = userRepository.findByActivationCode(activationCode).orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            client.setActivated(true);
            userRepository.save(client);
        }
        else{
            throw new RuntimeException("Client not found");
        }
    }


}
