package com.booking_beta_raul.booking_raul.aplication.service;

import com.booking_beta_raul.booking_raul.aplication.dtos.ReservaDto;
import com.booking_beta_raul.booking_raul.domain.entity.Reserva;
import com.booking_beta_raul.booking_raul.domain.persistence.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ReservaService {
    Reserva save(Reserva reserva);
    List<Reserva> findAll();

    List<ReservaDto> getAllReservas();

    void createReserva(ReservaDto reservaDto);
}
