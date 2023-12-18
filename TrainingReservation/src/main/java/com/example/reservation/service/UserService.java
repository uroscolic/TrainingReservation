package com.example.reservation.service;

import com.example.reservation.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> findAll(Pageable pageable);
    Page<ClientDto> findAllClients(Pageable pageable);
    Page<ManagerDto> findAllManagers(Pageable pageable);

    Page<AdminDto> findAllAdmins(Pageable pageable);

    ClientDto registerClient(ClientCreateDto clientDto);
    ManagerDto registerManager(ManagerCreateDto managerDto);
    ClientDto updateClient(ClientUpdateDto clientUpdateDto, boolean isAdmin);
    //TODO updateManager
    //ManagerDto updateManager(ManagerUpdateDto managerUpdateDto, boolean isAdmin);

}
