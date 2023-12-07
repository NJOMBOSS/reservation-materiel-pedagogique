package com.example.reservation.entite;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "materiel")
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_materiel")
    private  int id;

    private String libelle;

    private  int quantite;

    @OneToMany(mappedBy = "materiel")
    Set<Reservation> reservations;
}