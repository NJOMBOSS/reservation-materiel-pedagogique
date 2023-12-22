package com.example.reservation.entite;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private  int id;

    @ManyToOne
    @JoinColumn(name = "id_personne")
    private Personne personne;

    @ManyToOne
    @JoinColumn(name = "id_materiel")
    private Materiel materiel;

    @NotNull(message = "La date de réservation obligatoire")
    private LocalDate dateReservation;

    @NotNull(message = "L'heure de début obligatoire")
    private LocalTime heureDebut;

    @NotNull(message = "L'heure de fin  obligatoire")
    private LocalTime heureFin;

}
