package com.example.reservation.controller;

import com.example.reservation.dto.ClientCreateDto;
import com.example.reservation.dto.ClientDto;
import com.example.reservation.dto.ClientUpdateDto;
import com.example.reservation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {

    private UserService userService;


    // TODO: authorization

    // TODO: findAllByType pa da se prosledi client, da bi se dobili samo clienti is UserService-a
    @GetMapping
    public ResponseEntity<Page<ClientDto>> getAllClients(String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(userService.findAllClients(pageable), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<ClientDto> saveClient(@RequestBody ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userService.registerClient(clientCreateDto), HttpStatus.CREATED);
    }

    //TODO: add admin check
    @PutMapping("/modify")
    public ResponseEntity<ClientDto> updateClient(@RequestBody ClientUpdateDto clientUpdateDto) {
        return new ResponseEntity<>(userService.updateClient(clientUpdateDto,false), HttpStatus.OK);
    }
}
