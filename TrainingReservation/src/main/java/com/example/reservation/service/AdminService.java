package com.example.reservation.service;

import com.example.reservation.dto.AdminCreateDto;
import com.example.reservation.dto.AdminDto;
import com.example.reservation.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<AdminDto> findAll(Pageable pageable);
}
