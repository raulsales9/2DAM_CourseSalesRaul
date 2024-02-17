package com.booking_beta_raul.booking_raul.infraestructure.rest;

import com.booking_beta_raul.booking_raul.aplication.dtos.ReservaDto;
import com.booking_beta_raul.booking_raul.aplication.service.ReservaService;
import com.booking_beta_raul.booking_raul.domain.entity.Reserva;
import com.booking_beta_raul.booking_raul.domain.persistence.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservas")
public class ReservaController {
    @Autowired
    private final ReservaService reservaService;

    @Autowired
    public ReservaController(ReservaService reservaService) {
        this.reservaService = reservaService;
    }


    @GetMapping("/listAll")
    public List<Reserva> getAllReservas() {
        List<ReservaDto> reserva = reservaService.getAllReservas();
        return reservaService.findAll();
    }

    @PostMapping("/create")
    public Reserva createReserva(@RequestBody ReservaDto reservaDto) {
        Reserva reserva = new Reserva();
        reserva.setNumeroPersonas(reservaDto.getNumeroPersonas());
        reserva.setDestino(reservaDto.getDestino());
        reserva.setDias(reservaDto.getDias());
        return reservaService.save(reserva);
    }


}
