package com.example.reservation.mapper;

import com.example.reservation.domain.Manager;
import com.example.reservation.domain.RoleType;
import com.example.reservation.dto.*;
import com.example.reservation.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class ManagerMapper {
    private RoleRepository roleRepository;

    public ManagerMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ManagerDto managerToManagerDto(Manager manager)
    {
        ManagerDto managerDto = new ManagerDto();
        managerDto.setFirstName(manager.getFirstName());
        managerDto.setLastName(manager.getLastName());
        managerDto.setUsername(manager.getUsername());
        managerDto.setEmail(manager.getEmail());
        managerDto.setId(manager.getId());
        managerDto.setNameOfGym(manager.getNameOfGym());
        return managerDto;
    }

    public Manager managerCreateDtoToManager(ManagerCreateDto managerCreateDto)
    {
        Manager manager = new Manager();
        manager.setFirstName(managerCreateDto.getFirstName());
        manager.setLastName(managerCreateDto.getLastName());
        manager.setUsername(managerCreateDto.getUsername());
        manager.setEmail(managerCreateDto.getEmail());
        manager.setPassword(managerCreateDto.getPassword());
        manager.setDateOfBirth(managerCreateDto.getDateOfBirth());
        manager.setNameOfGym(managerCreateDto.getNameOfGym());
        manager.setDateOfEmployment(managerCreateDto.getDateOfEmployment());
        manager.setRole(roleRepository.findRoleByRoleType(RoleType.ROLE_MANAGER).get());
        manager.setBanned(false);
        return manager;
    }

    public Manager managerUpdateDtoToManager(Manager manager, ManagerUpdateDto managerUpdateDto)
    {
        if(managerUpdateDto.getFirstName() != null)
            manager.setFirstName(managerUpdateDto.getFirstName());
        if(managerUpdateDto.getLastName() != null)
            manager.setLastName(managerUpdateDto.getLastName());
        if(managerUpdateDto.getUsername() != null)
            manager.setUsername(managerUpdateDto.getUsername());
        if(managerUpdateDto.getEmail() != null)
            manager.setEmail(managerUpdateDto.getEmail());
        if(managerUpdateDto.getPassword() != null)
            manager.setPassword(managerUpdateDto.getPassword());
        return manager;
    }
    public Manager managerBanDtoToManager(Manager manager, ManagerBanDto managerBanDto)
    {
        manager.setBanned(managerBanDto.isBanned());
        return manager;
    }
}
