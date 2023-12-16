package com.example.reservation.controller;

import com.example.reservation.dto.ClientCreateDto;
import com.example.reservation.dto.ClientDto;
import com.example.reservation.dto.ClientUpdateDto;
import com.example.reservation.service.ClientService;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/client")
public class ClientController {

    private ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }
    @GetMapping
    public ResponseEntity<Page<ClientDto>> getAllClients(String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(clientService.findAll(pageable), HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(clientService.register(clientCreateDto), HttpStatus.CREATED);
    }

    //TODO: add admin check
    @PutMapping
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientUpdateDto clientUpdateDto) {
        return new ResponseEntity<>(clientService.update(clientUpdateDto,false), HttpStatus.OK);
    }
}
