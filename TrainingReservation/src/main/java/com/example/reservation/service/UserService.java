package com.example.reservation.service;

import com.example.reservation.dto.UserDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {

    Page<UserDto> findAll(Pageable pageable);
    UserDto register(UserDto userDto);
}
