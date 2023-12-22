package com.example.reservation.dto;

import com.example.reservation.entite.Materiel;
import com.example.reservation.entite.Personne;
import com.example.reservation.entite.Reservation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
@Validated
@Builder
@Getter
@Setter
@AllArgsConstructor
public class ReservationDTO {

    private  int id;

    @NotNull(message = "La date de réservation obligatoire")
    private LocalDate dateReservation;

    @NotNull(message = "L'heure de début obligatoire")
    private LocalTime heureDebut;

    @NotNull(message = "L'heure de fin obligatoire")
    private LocalTime heureFin;

   // private PersonneDTO personne;
   private int personneId;
  //  private MaterielDTO materiel;

    private int materielId;

    public static ReservationDTO fromEntity(Reservation reservation){
        return ReservationDTO.builder()
                .id(reservation.getId())
                .dateReservation(reservation.getDateReservation())
                .heureDebut(reservation.getHeureDebut())
                .heureFin(reservation.getHeureFin())
                //.personne(PersonneDTO.fromEntity(reservation.getPersonne()))
                //.personneId(PersonneDTO.fromEntity(reservation.getPersonne()).getId())
               // .materiel(MaterielDTO.fromEntity(reservation.getMateriel()))
              // .materielId(MaterielDTO.fromEntity(reservation.getMateriel()).getId())
                .personneId(reservation.getPersonne() != null ? reservation.getPersonne().getId() : 0)
                .materielId(reservation.getMateriel() != null ? reservation.getMateriel().getId() : 0)
                .build();
    }

    public static Reservation toEntity(ReservationDTO reservationDTO){
        return Reservation.builder()
                .id(reservationDTO.getId())
                .dateReservation(reservationDTO.getDateReservation())
                .heureDebut(reservationDTO.getHeureDebut())
                .heureFin(reservationDTO.getHeureFin())
                .personne(Personne.builder()
                        .id(reservationDTO.getPersonneId())
                        .build())
                .materiel(Materiel.builder()
                        .id(reservationDTO.getMaterielId())
                        .build())
                .build();
    }
}
