package com.example.reservation.mapper;

import com.example.reservation.domain.Client;
import com.example.reservation.domain.RoleType;
import com.example.reservation.dto.ClientBanDto;
import com.example.reservation.dto.ClientCreateDto;
import com.example.reservation.dto.ClientDto;
import com.example.reservation.dto.ClientUpdateDto;
import com.example.reservation.repository.RoleRepository;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {

    private static long number = 0;
    private RoleRepository roleRepository;

    public ClientMapper(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public ClientDto clientToClientDto(Client client)
    {
        ClientDto clientDto = new ClientDto();
        clientDto.setFirstName(client.getFirstName());
        clientDto.setLastName(client.getLastName());
        clientDto.setUsername(client.getUsername());
        clientDto.setEmail(client.getEmail());
        clientDto.setId(client.getId());
        return clientDto;
    }

    public Client clientCreateDtoToClient(ClientCreateDto clientCreateDto)
    {
        Client client = new Client();
        client.setFirstName(clientCreateDto.getFirstName());
        client.setLastName(clientCreateDto.getLastName());
        client.setUsername(clientCreateDto.getUsername());
        client.setEmail(clientCreateDto.getEmail());
        client.setPassword(clientCreateDto.getPassword());
        client.setRole(roleRepository.findRoleByRoleType(RoleType.ROLE_CLIENT).get());
        client.setBlocked(false);
        client.setNumberOfTrainings(0);
        client.setCardNumber(++number);

        return client;
    }


    public Client clientUpdateDtoToClient(Client client, ClientUpdateDto clientUpdateDto)
    {
        if(clientUpdateDto.getFirstName() != null)
            client.setFirstName(clientUpdateDto.getFirstName());
        if(clientUpdateDto.getLastName() != null)
            client.setLastName(clientUpdateDto.getLastName());
        if(clientUpdateDto.getUsername() != null)
            client.setUsername(clientUpdateDto.getUsername());
        if(clientUpdateDto.getEmail() != null)
            client.setEmail(clientUpdateDto.getEmail());
        if(clientUpdateDto.getPassword() != null)
            client.setPassword(clientUpdateDto.getPassword());
        return client;
    }
    public Client clientBanDtoToClient(Client client, ClientBanDto clientBanDto)
    {
        client.setBlocked(clientBanDto.isBlocked());
        return client;
    }

}
