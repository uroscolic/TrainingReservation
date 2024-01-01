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
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
@AllArgsConstructor
public class ManagerController {
    private UserService userService;
    private TokenService tokenService;

    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Page<UserDto>> getAllManagers(@RequestHeader("Authorization") String authorization,
                                                        Pageable pageable) {

        return new ResponseEntity<>(userService.findAllOfRole(pageable, RoleType.ROLE_MANAGER), HttpStatus.OK);
    }

    @PostMapping("/register")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ManagerDto> saveManager(@RequestHeader("Authorization") String authorization, @RequestBody @Valid ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(userService.registerManager(managerCreateDto), HttpStatus.CREATED);
    }
    @PutMapping("/modify")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_MANAGER"})
    public ResponseEntity<ManagerDto> updateManager(@RequestHeader("Authorization") String authorization, @RequestBody @Valid ManagerUpdateDto managerUpdateDto) {
        Claims claims = tokenService.parseToken(authorization.split(" ")[1]);
        System.out.println(claims);
        if(claims.get("role").equals("ROLE_MANAGER") && Long.parseLong(claims.get("id").toString()) != managerUpdateDto.getId())
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        return new ResponseEntity<>(userService.updateManager(managerUpdateDto), HttpStatus.OK);
    }
    @PutMapping("/ban")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<ManagerDto> banManager(@RequestHeader("Authorization") String authorization, @RequestBody ManagerBanDto managerBanDto) {
        return new ResponseEntity<>(userService.banManager(managerBanDto), HttpStatus.OK);
    }

}
