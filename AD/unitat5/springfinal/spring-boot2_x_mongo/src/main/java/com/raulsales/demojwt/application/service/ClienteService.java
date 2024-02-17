package com.raulsales.demojwt.application.service;

import com.raulsales.demojwt.application.dto.ClientesDTO;

import java.util.List;

public interface ClienteService {

    List<ClientesDTO> getAllClientes();

    ClientesDTO getClienteById(String clienteId);

    ClientesDTO createCliente(ClientesDTO clienteDTO);

    ClientesDTO updateCliente(String clienteId, ClientesDTO clienteDTO);

    void deleteCliente(String clienteId);
}
