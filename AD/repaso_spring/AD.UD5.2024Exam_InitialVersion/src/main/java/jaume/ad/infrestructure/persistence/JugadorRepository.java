package jaume.ad.infrestructure.persistence;

import jaume.ad.domain.model.Jugador;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JugadorRepository extends JpaRepository<Jugador, Long>{
}
