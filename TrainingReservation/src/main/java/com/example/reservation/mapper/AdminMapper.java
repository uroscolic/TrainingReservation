package com.example.reservation.mapper;

import com.example.reservation.domain.Admin;
import com.example.reservation.dto.AdminDto;
import com.example.reservation.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class AdminMapper {

    private RoleRepository roleRepository;

    public AdminMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public AdminDto adminToAdminDto(Admin admin)
    {
        AdminDto adminDto = new AdminDto();
        adminDto.setFirstName(admin.getFirstName());
        adminDto.setLastName(admin.getLastName());
        adminDto.setUsername(admin.getUsername());
        adminDto.setEmail(admin.getEmail());
        adminDto.setId(admin.getId());
        return adminDto;
    }
}
