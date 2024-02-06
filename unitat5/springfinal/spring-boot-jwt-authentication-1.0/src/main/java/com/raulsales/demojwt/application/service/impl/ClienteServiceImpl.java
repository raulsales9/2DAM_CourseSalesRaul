package com.raulsales.demojwt.application.service.impl;

import ch.qos.logback.core.net.server.Client;
import com.raulsales.demojwt.application.dto.ClienteDto;
import com.raulsales.demojwt.application.dto.ClienteDtoConverter;
import com.raulsales.demojwt.application.service.ClienteService;
import com.raulsales.demojwt.domain.entity.Cliente;
import com.raulsales.demojwt.domain.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClienteServiceImpl  implements ClienteService {
    @Autowired
    private ClienteRepository clientRepository;


    @Override
    public void insertClient(ClienteDto clienteDto) {
        Cliente client = ClienteDtoConverter.convertToEntity(clienteDto);
        clientRepository.save(client);
    }

    @Override
    public ClienteDto getClientById(long id) {
        Cliente post = clientRepository.findById(id).orElse(null);
        return (post != null) ? ClienteDtoConverter.convertToDto(post) : null;
    }

    @Override
    public List<ClienteDto> getAllClients() {
        List<Cliente> postList = clientRepository.findAll();
        List<ClienteDto> resultList = new ArrayList<>();
        for (Cliente client : postList) {
            resultList.add(ClienteDtoConverter.convertToDto(client));
        }
        return resultList;
    }

    @Override
    public void updateClient(ClienteDto clienteDto) {
        Cliente existingClient = clientRepository.findById(clienteDto.getId()).orElse(null);

        // Verificar si el cliente existe
        if (existingClient != null) {
            // Actualizar los campos necesarios del cliente existente
            existingClient.setNif(clienteDto.getNif());
            existingClient.setNombre(clienteDto.getNombre());
            existingClient.setApellidos(clienteDto.getApellidos());
            existingClient.setClaveseguridad(clienteDto.getClaveseguridad());
            existingClient.setEmail(clienteDto.getEmail());

            // Actualizar la lista de cuentas si es necesario
            // (esto dependerá de cómo deseas manejar las actualizaciones de cuentas)

            // Guardar el cliente actualizado en el repositorio
            clientRepository.save(existingClient);
        }
    }

    @Override
    public void deleteClient(long id) {
        if (clientRepository.existsById(id)) {
            // Eliminar el cliente por su ID
            clientRepository.deleteById(id);
        }

    }
}
