package jaume.ad.aplicattion.dto;

import jaume.ad.domain.model.Jugador;

public class JugadorConverterDto {
    public static JugadorDto fromModel(Jugador jugador) {
        JugadorDto dto = new JugadorDto();
        dto.setIdJugador(jugador.getIdJugador());
        dto.setNombre(jugador.getNombre());
        dto.setIdEquipo(jugador.getIdEquipo());
        return dto;
    }
}
