package com.raulsales.demojwt.infrastructure.rest;

import com.raulsales.demojwt.application.dto.CuentaDto;
import com.raulsales.demojwt.application.service.CuentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api/cuenta")
public class CuentaController {

    @Autowired
    private CuentaService cuentaService;

    @GetMapping("/allAccounts")
    public String getAllAccounts(Model model) {
        List<CuentaDto> accounts = cuentaService.getAllAcounts();
        model.addAttribute("accounts", accounts);
        return "/account/accountList";
    }

    @GetMapping("/insertAccount")
    public String showInsertAccountForm(Model model) {
        model.addAttribute("accountDto", new CuentaDto());
        return "/account/accountInsert";
    }

    @PostMapping("/insertAccount")
    public String createAccount(@ModelAttribute CuentaDto cuentaDto) {
        cuentaService.insertAccount(cuentaDto);
        return "redirect:/api/cuenta/allAccounts";
    }

    @GetMapping("/editAccount/{id}")
    public String showEditAccountForm(@PathVariable Long id, Model model) {
        CuentaDto cuentaDto = cuentaService.getAccountById(id);
        model.addAttribute("accountDto", cuentaDto);
        return "/account/accountEdit";
    }

    @PostMapping("/editAccount")
    public String updateAccount(@ModelAttribute CuentaDto cuentaDto) {
        cuentaService.updateAccount(cuentaDto);
        return "redirect:/api/cuenta/allAccounts";
    }

    @GetMapping("/deleteAccount/{id}")
    public String deleteAccount(@PathVariable Long id) {
        cuentaService.deleteAccount(id);
        return "redirect:/api/cuenta/allAccounts";
    }
    @GetMapping("/api/allAccounts")
    public ResponseEntity<List<CuentaDto>> getAllAccountsApi() {
        List<CuentaDto> accounts = cuentaService.getAllAcounts();
        return new ResponseEntity<>(accounts, HttpStatus.OK);
    }

}
