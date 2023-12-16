package com.example.reservation.service;

import com.example.reservation.dto.*;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientDto> findAll(Pageable pageable);
    ClientDto register(ClientCreateDto clientCreateDto);
    ClientDto update(ClientUpdateDto clientUpdateDto, boolean isAdmin);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);


}
