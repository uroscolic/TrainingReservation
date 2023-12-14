package com.example.reservation.service.impl;

import com.example.reservation.dto.UserDto;
import com.example.reservation.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public class UserServiceImpl implements UserService {

    @Override
    public Page<UserDto> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public UserDto register(UserDto userDto) {

        return userDto;
    }
}
