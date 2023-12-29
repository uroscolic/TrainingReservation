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
    ClientDto updateClient(ClientUpdateDto clientUpdateDto);
    ManagerDto updateManager(ManagerUpdateDto managerUpdateDto);
    ClientDto banClient(ClientBanDto clientBanDto);
    ManagerDto banManager(ManagerBanDto managerBanDto);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
    void incrementReservationCount(IncrementReservationCountDto incrementReservationCountDto);
}
