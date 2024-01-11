package com.example.reservation.mapper;

import com.example.reservation.domain.Admin;
import com.example.reservation.domain.Client;
import com.example.reservation.domain.Manager;
import com.example.reservation.domain.User;
import com.example.reservation.dto.ClientDto;
import com.example.reservation.dto.UserDto;
import com.example.reservation.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {

    private RoleRepository roleRepository;

    public UserDto userToUserDto(User user)
    {
        UserDto userDto = new UserDto();
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setUsername(user.getUsername());
        userDto.setEmail(user.getEmail());
        userDto.setId(user.getId());
        if(user instanceof Client client)
        {
            userDto.setRole("ROLE_CLIENT");
            userDto.setBanned(client.isBanned());
        }
        else if(user instanceof Admin)
        {
            userDto.setRole("ROLE_ADMIN");
            userDto.setBanned(false);
        }
        else
        {
            userDto.setRole("ROLE_MANAGER");
            userDto.setBanned(((Manager)user).isBanned());
        }
        return userDto;
    }

}
