package com.example.reservation.dto;

import com.example.reservation.entite.Materiel;
import com.example.reservation.entite.Personne;
import com.example.reservation.entite.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ReservationDTO {

    private  int id;

    private LocalDateTime heureDebut;

    private LocalDateTime heureFin;

    private int personneId;

    private int materielId;

    public static ReservationDTO fromEntity(Reservation reservation){
        return ReservationDTO.builder()
                .id(reservation.getId())
                .heureDebut(reservation.getHeureDebut())
                .heureFin(reservation.getHeureFin())
                .personneId(reservation.getPersonne().getId())
                .materielId(reservation.getMateriel().getId())
                .build();
    }

    public static Reservation toEntity(ReservationDTO reservationDTO){
        return Reservation.builder()
                .id(reservationDTO.getId())
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
