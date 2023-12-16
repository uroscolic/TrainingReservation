package com.example.reservation.service.impl;

import com.example.reservation.domain.Manager;
import com.example.reservation.dto.ManagerCreateDto;
import com.example.reservation.dto.ManagerDto;
import com.example.reservation.dto.TokenRequestDto;
import com.example.reservation.dto.TokenResponseDto;
import com.example.reservation.mapper.ManagerMapper;
import com.example.reservation.repository.ManagerRepository;
import com.example.reservation.service.ManagerService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private ManagerMapper managerMapper;
    private ManagerRepository managerRepository;

    public ManagerServiceImpl(ManagerMapper managerMapper, ManagerRepository managerRepository) {
        this.managerMapper = managerMapper;
        this.managerRepository = managerRepository;
    }

    @Override
    public Page<ManagerDto> findAll(Pageable pageable) {
        return managerRepository.findAll(pageable).map(managerMapper::managerToManagerDto);

    }

    @Override
    public ManagerDto register(ManagerCreateDto managerCreateDto) {
        Manager manager = managerMapper.managerCreateDtoToManager(managerCreateDto);
        managerRepository.save(manager);
        return managerMapper.managerToManagerDto(manager);
    }

    @Override
    public TokenResponseDto login(TokenRequestDto tokenRequestDto) {
        return null;
    }

}
