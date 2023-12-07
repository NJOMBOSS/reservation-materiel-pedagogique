package com.example.reservation.dto;

import com.example.reservation.entite.Personne;
import com.example.reservation.enums.TypePersonne;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class PersonneDTO {

    private  int id;

    private String nom;

    private String prenom;

    private Date dateNaissance;

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
