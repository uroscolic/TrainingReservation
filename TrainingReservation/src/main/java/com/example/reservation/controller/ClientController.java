package com.example.reservation.controller;

import com.example.reservation.domain.RoleType;
import com.example.reservation.dto.*;
import com.example.reservation.security.CheckSecurity;
import com.example.reservation.security.service.TokenService;
import com.example.reservation.service.UserService;
import io.jsonwebtoken.Claims;
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
    private TokenService tokenService;


    // TODO: authorization

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<Page<UserDto>> getAllClients(@RequestHeader("Authorization") String authorization,
                                                       Pageable pageable) {
        System.out.println("authorization: " + authorization);
        return new ResponseEntity<>(userService.findAllOfRole(pageable, RoleType.ROLE_CLIENT), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClient(/*@RequestHeader("Authorization") String authorization,*/
                                                @PathVariable("id") Long id) {
        /*Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        if(claims.get("role").equals("ROLE_CLIENT") && Long.parseLong(claims.get("id").toString()) != id)
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);*/
        return new ResponseEntity<>(userService.findClientById(id), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ClientDto> saveClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return new ResponseEntity<>(userService.registerClient(clientCreateDto), HttpStatus.CREATED);
    }

    //TODO: add admin check
    @CheckSecurity(roles = {"ROLE_CLIENT" , "ROLE_ADMIN"})
    @PutMapping("/modify")
    public ResponseEntity<ClientDto> updateClient(@RequestHeader("Authorization") String authorization,
                                                  @RequestBody @Valid ClientUpdateDto clientUpdateDto) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        System.out.println(claims);
        if(claims.get("role").equals("ROLE_CLIENT") && Long.parseLong(claims.get("id").toString()) != clientUpdateDto.getId())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(userService.updateClient(clientUpdateDto), HttpStatus.OK);
    }
    @PutMapping("/ban")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ClientDto> banClient(@RequestHeader("Authorization") String authorization,
                                               @RequestBody ClientBanDto clientBanDto) {
        return new ResponseEntity<>(userService.banClient(clientBanDto), HttpStatus.OK);
    }

}
