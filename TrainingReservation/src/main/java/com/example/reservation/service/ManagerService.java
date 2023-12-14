package com.example.reservation.service;

import com.example.reservation.dto.ManagerCreateDto;
import com.example.reservation.dto.ManagerDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ManagerService {

    Page<ManagerDto> findAll(Pageable pageable);
    ManagerDto register(ManagerCreateDto managerDto);
}
