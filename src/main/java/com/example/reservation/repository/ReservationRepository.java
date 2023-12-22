package com.example.reservation.repository;

import com.example.reservation.entite.Reservation;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    Optional<Reservation>findByMaterielIdAndDateReservationAndHeureDebutLessThanEqualAndHeureFinGreaterThanEqual(int mateterielId,
                                                                                                                 LocalDate dateReservation, LocalTime heureDebut, LocalTime heureFin);
}
