package com.example.reservation.service.impl;

import com.example.reservation.dto.AdminCreateDto;
import com.example.reservation.dto.AdminDto;
import com.example.reservation.mapper.AdminMapper;
import com.example.reservation.repository.AdminRepository;
import com.example.reservation.service.AdminService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
@Transactional
public class AdminServiceImpl implements AdminService {
    private AdminMapper adminMapper;
    private AdminRepository adminRepository;

    public AdminServiceImpl(AdminMapper adminMapper, AdminRepository adminRepository) {
        this.adminMapper = adminMapper;
        this.adminRepository = adminRepository;
    }

    @Override
    public Page<AdminDto> findAll(Pageable pageable) {


        return adminRepository.findAll(pageable).map(adminMapper::adminToAdminDto);
    }


}
