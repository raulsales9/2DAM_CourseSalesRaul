package com.raulsales.demojwt.application.dto;

import com.raulsales.demojwt.domain.entity.Cliente;

import java.util.stream.Collectors;

public class ClienteDtoConverter {
    public static ClienteDto convertToDto(Cliente cliente) {
        ClienteDto clienteDto = new ClienteDto();
        clienteDto.setId(cliente.getId());
        clienteDto.setNif(cliente.getNif());
        clienteDto.setNombre(cliente.getNombre());
        clienteDto.setApellidos(cliente.getApellidos());
        clienteDto.setClaveseguridad(cliente.getClaveseguridad());
        clienteDto.setEmail(cliente.getEmail());

        // Convierte las cuentas si es necesario
        clienteDto.setCuentas(cliente.getCuentas().stream()
                .map(CuentaDtoConverter::convertToDto)
                .collect(Collectors.toList()));

        return clienteDto;
    }

    public static Cliente convertToEntity(ClienteDto clienteDto) {
        Cliente cliente = new Cliente();
        cliente.setId(clienteDto.getId());
        cliente.setNif(clienteDto.getNif());
        cliente.setNombre(clienteDto.getNombre());
        cliente.setApellidos(clienteDto.getApellidos());
        cliente.setClaveseguridad(clienteDto.getClaveseguridad());
        cliente.setEmail(clienteDto.getEmail());

        // Convierte las cuentas si es necesario
        cliente.setCuentas(clienteDto.getCuentas().stream()
                .map(CuentaDtoConverter::convertToEntity)
                .collect(Collectors.toList()));

        return cliente;
    }
}
