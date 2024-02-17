package jaume.ad.aplicattion.service;


import jaume.ad.aplicattion.dto.EquipoDto;
import jaume.ad.aplicattion.dto.EquipoDtoConverter;
import jaume.ad.domain.model.Equipo;
import jaume.ad.infrestructure.persistence.EquipoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class EquipoServiceImpl implements EquipoService {

    private final EquipoRepository equipoRepository;

    public EquipoServiceImpl(EquipoRepository equipoRepository) {
        this.equipoRepository = equipoRepository;
    }

    @Override
    public List<EquipoDto> findByCiudad(String ciudad) {
        List<Equipo> equipos = equipoRepository.findByCiudad(ciudad);
        return equipos.stream()
                .map(EquipoDtoConverter::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDto findByJugadoresId(Long jugadorId) {
        Equipo equipo = equipoRepository.findByJugadoresId(jugadorId).orElse(null);
        return EquipoDtoConverter.fromModel(equipo);
    }

    @Override
    public EquipoDto findById(Long idEquipo) {
        Equipo equipo = equipoRepository.findById(idEquipo).orElse(null);
        return EquipoDtoConverter.fromModel(equipo);
    }

    @Override
    public List<EquipoDto> getAllEquipos() {
        List<Equipo> equipos = equipoRepository.findAll();
        return equipos.stream()
                .map(EquipoDtoConverter::fromModel)
                .collect(Collectors.toList());
    }

    @Override
    public EquipoDto insertEquipo(EquipoDto equipoDto) {
        Equipo equipo = EquipoDtoConverter.toModel(equipoDto);
        Equipo savedEquipo = equipoRepository.save(equipo);
        return EquipoDtoConverter.fromModel(savedEquipo);
    }
}
