package com.example.reservation.controller;

import com.example.reservation.domain.RoleType;
import com.example.reservation.dto.*;
import com.example.reservation.service.UserService;
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

    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllManagers(String authorization,
                                                        Pageable pageable) {

        return new ResponseEntity<>(userService.findAllOfRole(pageable, RoleType.ROLE_MANAGER), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ManagerDto> saveManager(@RequestBody ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(userService.registerManager(managerCreateDto), HttpStatus.CREATED);
    }
    @PutMapping("/modify")
    public ResponseEntity<ManagerDto> updateManager(@RequestBody ManagerUpdateDto managerUpdateDto) {
        return new ResponseEntity<>(userService.updateManager(managerUpdateDto,false), HttpStatus.OK);
    }
}
