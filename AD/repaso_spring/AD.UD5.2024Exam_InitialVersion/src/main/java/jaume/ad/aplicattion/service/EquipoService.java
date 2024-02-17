package jaume.ad.aplicattion.service;

import jaume.ad.aplicattion.dto.EquipoDto;

import java.util.List;

public interface EquipoService {
    List<EquipoDto> findByCiudad(String ciudad);
    EquipoDto findByJugadoresId(Long jugadorId);
    EquipoDto findById(Long idEquipo);
    List<EquipoDto> getAllEquipos();
    EquipoDto insertEquipo(EquipoDto equipoDto);
}
