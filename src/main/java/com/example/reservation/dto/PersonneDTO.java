package com.example.reservation.dto;

import com.example.reservation.entite.Personne;
import com.example.reservation.enums.TypePersonne;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;

@Validated
@Builder
@Getter
@Setter
@AllArgsConstructor
public class PersonneDTO {

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

    private TypePersonne role;

    public static PersonneDTO fromEntity(Personne personne){
        return PersonneDTO.builder()
                .id(personne.getId())
                .nom(personne.getNom())
                .prenom(personne.getPrenom())
                .dateNaissance(personne.getDateNaissance())
                .sexe(personne.getSexe())
                .filiere(personne.getFiliere())
                .niveau(personne.getNiveau())
                .role(personne.getRole())
                .build();
    }

    public static Personne toEntity(PersonneDTO personneDTO){
        return Personne.builder()
                .id(personneDTO.getId())
                .nom(personneDTO.getNom())
                .prenom(personneDTO.getPrenom())
                .dateNaissance(personneDTO.getDateNaissance())
                .sexe(personneDTO.getSexe())
                .filiere(personneDTO.getFiliere())
                .niveau(personneDTO.getNiveau())
                .role(personneDTO.getRole())
                .build();
    }
}
