package com.example.reservation.repository;

import com.example.reservation.entite.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {

    Optional<Personne> findByNomAndPrenomAndDateNaissance(String nom, String prenom, LocalDate dateNaissance);
}
