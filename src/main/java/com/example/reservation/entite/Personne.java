package com.example.reservation.entite;

import com.example.reservation.enums.TypePersonne;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "personne")
public class Personne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_personne")
    private  int id;

    private String nom;

    private String prenom;

    private LocalDate dateNaissance;

    private char sexe;

    private String filiere;

    private int niveau;

    @Enumerated(EnumType.STRING)
    private TypePersonne role;

    @OneToMany(mappedBy = "personne")
    Set<Reservation> reservations;
}
