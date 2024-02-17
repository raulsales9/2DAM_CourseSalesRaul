package jaume.ad.aplicattion.dto;

import jaume.ad.domain.model.Equipo;

public class EquipoDtoConverter {
    public static EquipoDto fromModel(Equipo equipo) {
        EquipoDto dto = new EquipoDto();
        dto.setIdEquipo(equipo.getIdEquipo());
        dto.setNombre(equipo.getNombre());
        dto.setCiudad(equipo.getCiudad());
        return dto;
    }

    public static Equipo toModel(EquipoDto dto) {
        Equipo equipo = new Equipo();
        equipo.setIdEquipo(dto.getIdEquipo());
        equipo.setNombre(dto.getNombre());
        equipo.setCiudad(dto.getCiudad());
        return equipo;
    }


}