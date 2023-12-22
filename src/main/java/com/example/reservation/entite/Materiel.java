package com.example.reservation.entite;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
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

    @NotNull(message = "Le libellé est obligatoire")
    private String libelle;

    @NotNull(message = "La quantité est obligatoire")
    private  int quantite;

    private boolean estEnEtat=true;

    @OneToMany(mappedBy = "materiel")
    Set<Reservation> reservations;
}
