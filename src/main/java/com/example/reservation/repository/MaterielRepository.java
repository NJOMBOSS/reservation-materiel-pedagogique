package com.example.reservation.repository;

import com.example.reservation.entite.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MaterielRepository extends JpaRepository<Materiel, Integer> {

    Optional<Materiel> findByLibelle(String libelle);
}
