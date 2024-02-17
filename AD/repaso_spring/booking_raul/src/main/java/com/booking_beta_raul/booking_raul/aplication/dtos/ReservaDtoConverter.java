package com.booking_beta_raul.booking_raul.aplication.dtos;

import com.booking_beta_raul.booking_raul.domain.entity.Reserva;

public class ReservaDtoConverter {
    public static ReservaDto convertToDto(Reserva reserva) {
        ReservaDto reservaDto = new ReservaDto();
        reservaDto.setNumeroPersonas(reserva.getNumeroPersonas());
        reservaDto.setDestino(reserva.getDestino());
        reservaDto.setDias(reserva.getDias());
        return reservaDto;
    }

    public static Reserva convertToEntity(ReservaDto reservaDto) {
        Reserva reserva = new Reserva();
        reserva.setNumeroPersonas(reservaDto.getNumeroPersonas());
        reserva.setDestino(reservaDto.getDestino());
        reserva.setDias(reservaDto.getDias());
        return reserva;
    }
}
