package com.example.reservation.service.impl;

import com.example.reservation.dto.ReservationDTO;
import com.example.reservation.entite.Materiel;
import com.example.reservation.entite.Personne;
import com.example.reservation.entite.Reservation;
import com.example.reservation.exceptions.OperationNonPermittedException;
import com.example.reservation.repository.MaterielRepository;
import com.example.reservation.repository.PersonneRepository;
import com.example.reservation.repository.ReservationRepository;
import com.example.reservation.service.ReservationService;
import com.example.reservation.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final ObjectsValidator<ReservationDTO> validator;

    private final PersonneRepository personneRepository;
    private final MaterielRepository materielRepository;

    @Override
    public Integer save(@Valid ReservationDTO reservationDTO) {
          validator.validate(reservationDTO);
        Reservation reservation = ReservationDTO.toEntity(reservationDTO);
        Optional<Personne> existPersonne = personneRepository.findById(reservationDTO.getPersonneId());
        Optional<Materiel> existMateriel = materielRepository.findById(reservationDTO.getMaterielId());
        Optional<Reservation> existReservation = reservationRepository.findByMaterielIdAndDateReservationAndHeureDebutLessThanEqualAndHeureFinGreaterThanEqual(
              reservationDTO.getMaterielId(),  reservationDTO.getDateReservation(), reservationDTO.getHeureDebut(), reservationDTO.getHeureFin()
        );

        reservation.setPersonne(existPersonne.get());
        if (existMateriel.isEmpty()) {
            throw new EntityNotFoundException("Nous ne pouvons pas effectué la reservation car, le matériel n'existe pas");

        } else if (reservation.getMateriel().isEstEnEtat()) {
             throw new OperationNonPermittedException("Ce materiel n'est pas en bonne état");
        } else if (existReservation.isPresent()){
            throw new OperationNonPermittedException("Le matériel a déjà été enregistré à cette heure !!!");
        }

     // Réduire la quantité du matériel après la réservation
        Materiel materiel = existMateriel.get();
        materiel.setQuantite(materiel.getQuantite()-1);
        materielRepository.save(materiel);

        return reservationRepository.save(reservation).getId();
    }

    @Override
    public void update(ReservationDTO reservationDTO) {
        Reservation reservation = ReservationDTO.toEntity(reservationDTO);
        Optional<Reservation> existReservation = reservationRepository.findById(reservation.getId());
        Optional<Personne> existPersonne = personneRepository.findById(reservationDTO.getPersonneId());
        Optional<Materiel> existMateriel = materielRepository.findById(reservationDTO.getMaterielId());

        if(existReservation.isEmpty()){
            throw new EntityNotFoundException("Cette réservation n'existe pas !!!");
        } else if  (existMateriel.isEmpty()) {
            throw new EntityNotFoundException("Nous ne pouvons pas effectué la reservation car, le matériel n'existe pas");

        } else if  (existPersonne.isEmpty()) {
                throw new EntityNotFoundException("Nous ne pouvons pas effectué la reservation car, la personne n'existe pas");

                }

        Reservation existe = existReservation.get();

        existe.setDateReservation(reservationDTO.getDateReservation());
        existe.setHeureDebut(reservationDTO.getHeureDebut());
        existe.setHeureFin(reservationDTO.getHeureFin());
        existe.setPersonne(existPersonne.get());
        existe.setMateriel(existMateriel.get());

        reservationRepository.save(existe);
    }

    @Override
    public List<ReservationDTO> findAll() {
        return reservationRepository.findAll().stream()
                .map(ReservationDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public ReservationDTO findById(Integer id) {
        return reservationRepository.findById(id)
                .map(ReservationDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune personne n'a été trouvé avec  cet ID: " + id));
    }

    @Override
    public void delete(Integer id) {

        Optional<Reservation>  reservation = reservationRepository.findById(id);
        if (reservation.isEmpty()){
            throw new EntityNotFoundException("Impossible d'effectuer la suppression car, Cette reservation n'existe pas !!!");
        }
        reservationRepository.deleteById(id);
    }
}
