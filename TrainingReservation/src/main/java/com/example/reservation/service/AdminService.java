package com.example.reservation.service;

import com.example.reservation.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AdminService {

    Page<AdminDto> findAll(Pageable pageable);
    TokenResponseDto login(TokenRequestDto tokenRequestDto);
}
