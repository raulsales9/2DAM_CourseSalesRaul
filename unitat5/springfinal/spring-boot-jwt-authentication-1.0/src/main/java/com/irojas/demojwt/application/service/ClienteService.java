package com.irojas.demojwt.application.service;


import com.irojas.demojwt.application.dto.ClienteDto;

import java.util.List;

public interface ClienteService {
    void insertClient(ClienteDto clienteDto);
    ClienteDto getClientById(long id);
    List<ClienteDto> getAllClients();
    void updateClient(ClienteDto clienteDto); // Agrega este método
    void deleteClient(long id);
}
