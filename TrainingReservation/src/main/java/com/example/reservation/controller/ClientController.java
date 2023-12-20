package com.example.reservation.controller;

import com.example.reservation.domain.RoleType;
import com.example.reservation.dto.*;
import com.example.reservation.service.UserService;
import jakarta.validation.Valid;
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

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllClients(String authorization,
                                                       Pageable pageable) {
        return new ResponseEntity<>(userService.findAllOfRole(pageable, RoleType.ROLE_CLIENT), HttpStatus.OK);
    }


    @PostMapping("/register")
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userService.registerClient(clientCreateDto), HttpStatus.CREATED);
    }

    //TODO: add admin check
    @PutMapping("/modify")
    public ResponseEntity<ClientDto> updateClient(@RequestBody @Valid ClientUpdateDto clientUpdateDto) {
        return new ResponseEntity<>(userService.updateClient(clientUpdateDto), HttpStatus.OK);
    }
    @PutMapping("/ban")
    public ResponseEntity<ClientDto> banClient(@RequestBody ClientBanDto clientBanDto) {
        return new ResponseEntity<>(userService.banClient(clientBanDto), HttpStatus.OK);
    }
}
