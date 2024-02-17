package jaume.ad.infrestructure.persistence;

import jaume.ad.domain.model.Equipo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EquipoRepository extends JpaRepository<Equipo, Long> {
    List<Equipo> findByCiudad(String ciudad);
    Optional<Equipo> findByJugadoresId(Long jugadorId);
    Optional<Equipo> findById(Long idEquipo);
    List<Equipo> findAll();
    <S extends Equipo> S save(S entity);
}
