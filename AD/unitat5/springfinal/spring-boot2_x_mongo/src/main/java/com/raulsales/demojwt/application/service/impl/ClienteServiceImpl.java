package com.raulsales.demojwt.service;

import com.raulsales.demojwt.application.dto.ClientesDTO;
import com.raulsales.demojwt.application.service.ClienteService;
import com.raulsales.demojwt.domain.entity.Cliente;
import com.raulsales.demojwt.domain.persistence.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClienteServiceImpl implements ClienteService {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteServiceImpl(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<ClientesDTO> getAllClientes() {
        List<Cliente> clientes = clienteRepository.findAll();
        return clientes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ClientesDTO getClienteById(String clienteId) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente not found with id: " + clienteId));
        return convertToDTO(cliente);
    }

    @Override
    public ClientesDTO createCliente(ClientesDTO clienteDTO) {
        Cliente newCliente = convertToEntity(clienteDTO);
        clienteRepository.save(newCliente);
        return convertToDTO(newCliente);
    }

    @Override
    public ClientesDTO updateCliente(String clienteId, ClientesDTO clienteDTO) {
        Cliente existingCliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente not found with id: " + clienteId));

        // Update fields manually
        existingCliente.setApellidos(clienteDTO.getApellidos());
        existingCliente.setClaveseguridad(clienteDTO.getClaveseguridad());
        existingCliente.setEmail(clienteDTO.getEmail());
        existingCliente.setNif(clienteDTO.getNif());
        existingCliente.setNombre(clienteDTO.getNombre());
        existingCliente.setAccounts(clienteDTO.getAccounts());
        existingCliente.setRecommendations(clienteDTO.getRecommendations());

        clienteRepository.save(existingCliente);

        return convertToDTO(existingCliente);
    }

    @Override
    public void deleteCliente(String clienteId) {
        clienteRepository.deleteById(clienteId);
    }

    private ClientesDTO convertToDTO(Cliente cliente) {
        ClientesDTO clienteDTO = new ClientesDTO();
        clienteDTO.set_id(cliente.get_id());
        clienteDTO.setApellidos(cliente.getApellidos());
        clienteDTO.setClaveseguridad(cliente.getClaveseguridad());
        clienteDTO.setEmail(cliente.getEmail());
        clienteDTO.setNif(cliente.getNif());
        clienteDTO.setNombre(cliente.getNombre());
        clienteDTO.setAccounts(cliente.getAccounts());
        clienteDTO.setRecommendations(cliente.getRecommendations());
        return clienteDTO;
    }

    private Cliente convertToEntity(ClientesDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.set_id(clienteDTO.get_id());
        cliente.setApellidos(clienteDTO.getApellidos());
        cliente.setClaveseguridad(clienteDTO.getClaveseguridad());
        cliente.setEmail(clienteDTO.getEmail());
        cliente.setNif(clienteDTO.getNif());
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setAccounts(clienteDTO.getAccounts());
        cliente.setRecommendations(clienteDTO.getRecommendations());
        return cliente;
    }
}
