package com.example.reservation.dto;

import com.example.reservation.entite.Materiel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class MaterielDTO {

    private  int id;

    private String libelle;

    private  int quantite;

    public static MaterielDTO fromEntity(Materiel materiel){
        return MaterielDTO.builder()
                .id(materiel.getId())
                .libelle(materiel.getLibelle())
                .quantite(materiel.getQuantite())
                .build();
    }

    public static Materiel toEntity(MaterielDTO materielDTO){
        return Materiel.builder()
                .id(materielDTO.getId())
                .libelle(materielDTO.getLibelle())
                .quantite(materielDTO.getQuantite())
                .build();
    }

}
