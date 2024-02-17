package com.booking_beta_raul.booking_raul.aplication.service.impl;

import com.booking_beta_raul.booking_raul.aplication.dtos.ReservaDto;
import com.booking_beta_raul.booking_raul.aplication.service.ReservaService;
import com.booking_beta_raul.booking_raul.domain.entity.Reserva;
import com.booking_beta_raul.booking_raul.domain.persistence.ReservaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservaServiceImpl implements ReservaService {

    private final ReservaRepository reservaRepository;

    @Autowired
    public ReservaServiceImpl(ReservaRepository reservaRepository) {
        this.reservaRepository = reservaRepository;
    }

    public Reserva save(Reserva reserva) {
        return reservaRepository.save(reserva);
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Override
    public List<ReservaDto> getAllReservas() {
        return null;
    }

    @Override
    public void createReserva(ReservaDto reservaDto) {

    }
}
