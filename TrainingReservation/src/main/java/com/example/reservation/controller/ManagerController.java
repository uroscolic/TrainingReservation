package com.example.reservation.controller;

import com.example.reservation.dto.ManagerCreateDto;
import com.example.reservation.dto.ManagerDto;
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

    // TODO: manager update

    @GetMapping
    public ResponseEntity<Page<ManagerDto>> getAllManagers(String authorization,
                                                        Pageable pageable) {

        return new ResponseEntity<>(userService.findAllManagers(pageable), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<ManagerDto> saveManager(@RequestBody ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(userService.registerManager(managerCreateDto), HttpStatus.CREATED);
    }
}
