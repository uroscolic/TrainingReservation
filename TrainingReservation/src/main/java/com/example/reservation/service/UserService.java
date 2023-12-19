package com.example.reservation.service;

import com.example.reservation.domain.RoleType;
import com.example.reservation.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> findAll(Pageable pageable);
    Page<UserDto> findAllOfRole(Pageable pageable, RoleType roleType);
    ClientDto registerClient(ClientCreateDto clientDto);
    ManagerDto registerManager(ManagerCreateDto managerDto);
    ClientDto updateClient(ClientUpdateDto clientUpdateDto, boolean isAdmin);
    ManagerDto updateManager(ManagerUpdateDto managerUpdateDto, boolean isAdmin);
    ClientDto banClient(ClientBanDto clientBanDto);

}
