package com.example.reservation.dto;

import com.example.reservation.entite.Materiel;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

@Validated
@Builder
@Getter
@Setter
@AllArgsConstructor
public class MaterielDTO {

    private  int id;

    @NotNull(message = "Le libellé est obligatoire")
    private String libelle;

    @NotNull(message = "La quantité est obligatoire")
    private  int quantite;

    private boolean estEnEtat;

    public static MaterielDTO fromEntity(Materiel materiel){
        return MaterielDTO.builder()
                .id(materiel.getId())
                .libelle(materiel.getLibelle())
                .quantite(materiel.getQuantite())
                .estEnEtat(materiel.isEstEnEtat())
                .build();
    }

    public static Materiel toEntity(MaterielDTO materielDTO){
        return Materiel.builder()
                .id(materielDTO.getId())
                .libelle(materielDTO.getLibelle())
                .quantite(materielDTO.getQuantite())
                .estEnEtat(materielDTO.isEstEnEtat())
                .build();
    }

}
