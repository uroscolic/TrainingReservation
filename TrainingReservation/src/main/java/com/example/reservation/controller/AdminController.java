package com.example.reservation.controller;

import com.example.reservation.dto.AdminDto;
import com.example.reservation.dto.ManagerDto;
import com.example.reservation.service.AdminService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }
    // TODO dodati check security
    @GetMapping
    public ResponseEntity<Page<AdminDto>> getAllAdmins(String authorization,
                                                       Pageable pageable) {

        return new ResponseEntity<>(adminService.findAll(pageable), HttpStatus.OK);
    }
}
