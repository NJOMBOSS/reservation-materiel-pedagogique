package com.example.reservation.entite;

import com.example.reservation.enums.TypePersonne;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Le nom est obligatoire")
    private String nom;

    @NotNull(message = "Le prenom est obligatoire")
    private String prenom;

    @NotNull(message = "La date naissance est obligatoire")
    private LocalDate dateNaissance;

    private char sexe;

    private String filiere;

    private int niveau;

    @Enumerated(EnumType.STRING)
    private TypePersonne role;

    @OneToMany(mappedBy = "personne")
    Set<Reservation> reservations;
}
