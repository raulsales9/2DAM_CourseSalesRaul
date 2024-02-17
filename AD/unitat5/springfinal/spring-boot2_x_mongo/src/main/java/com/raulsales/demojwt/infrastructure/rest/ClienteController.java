package com.raulsales.demojwt.infrastructure.rest;

import com.raulsales.demojwt.application.dto.ClientesDTO;
import com.raulsales.demojwt.application.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    private final ClienteService clienteService;

    @Autowired
    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public List<ClientesDTO> getAllClientes() {
        return clienteService.getAllClientes();
    }

    @GetMapping("/{clienteId}")
    public ClientesDTO getClienteById(@PathVariable String clienteId) {
        return clienteService.getClienteById(clienteId);
    }

    @PostMapping
    public ClientesDTO createCliente(@RequestBody ClientesDTO clienteDTO) {
        return clienteService.createCliente(clienteDTO);
    }

    @PutMapping("/{clienteId}")
    public ClientesDTO updateCliente(@PathVariable String clienteId, @RequestBody ClientesDTO clienteDTO) {
        return clienteService.updateCliente(clienteId, clienteDTO);
    }

    @DeleteMapping("/{clienteId}")
    public void deleteCliente(@PathVariable String clienteId) {
        clienteService.deleteCliente(clienteId);
    }
}