package com.example.reservation.service.impl;

import com.example.reservation.domain.Client;
import com.example.reservation.domain.Manager;
import com.example.reservation.domain.User;
import com.example.reservation.dto.*;
import com.example.reservation.mapper.AdminMapper;
import com.example.reservation.mapper.ClientMapper;
import com.example.reservation.mapper.ManagerMapper;
import com.example.reservation.mapper.UserMapper;
import com.example.reservation.repository.UserRepository;
import com.example.reservation.service.UserService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Getter
@Setter
@AllArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService {

    private ClientMapper clientMapper;
    private AdminMapper adminMapper;
    private ManagerMapper managerMapper;
    private UserMapper userMapper;
    private UserRepository userRepository;
    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return userRepository.findAll(pageable).map(userMapper::userToUserDto);
    }

    @Override
    public Page<ClientDto> findAllClients(Pageable pageable) {
        return null;

    }

    @Override
    public Page<ManagerDto> findAllManagers(Pageable pageable) {
        return null;
    }

    @Override
    public Page<AdminDto> findAllAdmins(Pageable pageable) {
        return null;
    }

    @Override
    public ClientDto registerClient(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        userRepository.save(client);
        return clientMapper.clientToClientDto(client);

    }

    @Override
    public ManagerDto registerManager(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        userRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);

    }

    @Override
    public ClientDto updateClient(ClientUpdateDto clientUpdateDto, boolean isAdmin) {
        User user = userRepository.findByUsername(clientUpdateDto.getOldUsername()).orElseThrow(() -> new RuntimeException("Client not found"));
        if(user instanceof Client client){
            client = clientMapper.clientUpdateDtoToClient( client, clientUpdateDto,false);
            userRepository.save(client);
            return clientMapper.clientToClientDto(client);
        }
        throw new RuntimeException("Client not found");
    }

    @Override
    public ManagerDto updateManager(ManagerUpdateDto managerUpdateDto, boolean isAdmin) {
        User user = userRepository.findByUsername(managerUpdateDto.getOldUsername()).orElseThrow(() -> new RuntimeException("Manager not found"));
        if(user instanceof Manager manager){
            manager = managerMapper.managerUpdateDtoToManager(manager, managerUpdateDto,false);
            userRepository.save(manager);
            return managerMapper.managerToManagerDto(manager);
        }
        throw new RuntimeException("Manager not found");
    }


}
