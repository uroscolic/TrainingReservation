package com.example.reservation.controller;

import com.example.reservation.dto.ManagerCreateDto;
import com.example.reservation.dto.ClientDto;
import com.example.reservation.dto.ManagerDto;
import com.example.reservation.service.ManagerService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/manager")
public class ManagerController {
    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @GetMapping
    public ResponseEntity<Page<ManagerDto>> getAllManagers(String authorization,
                                                        Pageable pageable) {

        return new ResponseEntity<>(managerService.findAll(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Register manager")
    @PostMapping
    public ResponseEntity<ManagerDto> saveManager(@RequestBody ManagerCreateDto managerCreateDto) {
        return new ResponseEntity<>(managerService.register(managerCreateDto), HttpStatus.CREATED);
    }
}
