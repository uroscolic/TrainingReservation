package com.example.reservation.service;

import com.example.reservation.dto.ClientCreateDto;


import com.example.reservation.dto.ClientDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ClientService {

    Page<ClientDto> findAll(Pageable pageable);
    ClientDto register(ClientCreateDto clientCreateDto);
}
