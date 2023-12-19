package com.example.reservation.controller;

import com.example.reservation.domain.RoleType;
import com.example.reservation.dto.AdminDto;
import com.example.reservation.dto.UserDto;
import com.example.reservation.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {
    private UserService userService;

    // TODO dodati check security
    @GetMapping
    public ResponseEntity<Page<UserDto>> getAllAdmins(String authorization,
                                                      Pageable pageable) {

        return new ResponseEntity<>(userService.findAllOfRole(pageable, RoleType.ROLE_ADMIN), HttpStatus.OK);
    }
}
