package com.raulsales.demojwt.infrastructure.rest;

import com.raulsales.demojwt.application.dto.CajeroDTO;
import com.raulsales.demojwt.application.service.CajeroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cajeros")
public class CajeroController {

    private final CajeroService cajeroService;

    @Autowired
    public CajeroController(CajeroService cajeroService) {
        this.cajeroService = cajeroService;
    }

    @GetMapping
    public List<CajeroDTO> getAllCajeros() {
        return cajeroService.getAllCajeros();
    }

    @GetMapping("/{cajeroId}")
    public CajeroDTO getCajeroById(@PathVariable String cajeroId) {
        return cajeroService.getCajeroById(cajeroId);
    }

    @PostMapping
    public CajeroDTO createCajero(@RequestBody CajeroDTO cajeroDTO) {
        return cajeroService.createCajero(cajeroDTO);
    }

    @PutMapping("/{cajeroId}")
    public CajeroDTO updateCajero(@PathVariable String cajeroId, @RequestBody CajeroDTO cajeroDTO) {
        return cajeroService.updateCajero(cajeroId, cajeroDTO);
    }

    @DeleteMapping("/{cajeroId}")
    public void deleteCajero(@PathVariable String cajeroId) {
        cajeroService.deleteCajero(cajeroId);
    }
}
