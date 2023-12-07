package com.example.reservation.repository;

import com.example.reservation.entite.Personne;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonneRepository extends JpaRepository<Personne, Integer> {
}
