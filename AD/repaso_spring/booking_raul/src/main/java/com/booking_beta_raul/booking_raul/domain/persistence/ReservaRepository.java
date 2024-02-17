package com.booking_beta_raul.booking_raul.domain.persistence;

import com.booking_beta_raul.booking_raul.domain.entity.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
}
