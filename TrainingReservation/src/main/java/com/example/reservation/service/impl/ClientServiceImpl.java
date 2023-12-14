package com.example.reservation.service.impl;

import com.example.reservation.domain.Client;
import com.example.reservation.dto.ClientCreateDto;
import com.example.reservation.dto.ClientDto;
import com.example.reservation.mapper.ClientMapper;
import com.example.reservation.repository.ClientRepository;
import com.example.reservation.service.ClientService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ClientServiceImpl implements ClientService {

    private ClientMapper clientMapper;
    private ClientRepository clientRepository;

    public ClientServiceImpl(ClientMapper clientMapper, ClientRepository clientRepository) {
        this.clientMapper = clientMapper;
        this.clientRepository = clientRepository;
    }

    @Override
    public Page<ClientDto> findAll(Pageable pageable) {
        return clientRepository.findAll(pageable).map(clientMapper::clientToClientDto);
    }

    @Override
    public ClientDto register(ClientCreateDto clientCreateDto) {
        Client client = clientMapper.clientCreateDtoToClient(clientCreateDto);
        clientRepository.save(client);
        return clientMapper.clientToClientDto(client);
    }
}
