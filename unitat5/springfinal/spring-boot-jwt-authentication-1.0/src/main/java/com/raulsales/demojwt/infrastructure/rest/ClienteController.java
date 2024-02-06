package com.raulsales.demojwt.infrastructure.rest;

import com.raulsales.demojwt.application.dto.ClienteDto;
import com.raulsales.demojwt.application.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/allClients")
    public String getAllClients(Model model) {
        List<ClienteDto> clients = clienteService.getAllClients();
        model.addAttribute("clients", clients);
        return "/client/clientList";
    }

    @GetMapping("/insertClient")
    public String showInsertClientForm(Model model) {
        model.addAttribute("clientDto", new ClienteDto());
        return "/client/clientInsert";
    }

    @PostMapping("/insertClient")
    public String createClient(@ModelAttribute ClienteDto clienteDto) {
        clienteService.insertClient(clienteDto);
        return "redirect:/api/cliente/allClients";
    }

    @GetMapping("/editClient/{id}")
    public String showEditClientForm(@PathVariable Long id, Model model) {
        ClienteDto clienteDto = clienteService.getClientById(id);
        model.addAttribute("clientDto", clienteDto);
        return "/client/clientEdit";
    }

    @PostMapping("/editClient")
    public String updateClient(@ModelAttribute ClienteDto clienteDto) {
        clienteService.updateClient(clienteDto);
        return "redirect:/api/cliente/allClients";
    }

    @GetMapping("/deleteClient/{id}")
    public String deleteClient(@PathVariable Long id) {
        clienteService.deleteClient(id);
        return "redirect:/api/cliente/allClients";
    }

    @GetMapping("/api/allClients")
    public ResponseEntity<List<ClienteDto>> getAllClientsApi() {
        List<ClienteDto> clients = clienteService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }
}
