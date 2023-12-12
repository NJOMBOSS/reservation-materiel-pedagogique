package com.example.reservation.service.impl;

import com.example.reservation.dto.PersonneDTO;
import com.example.reservation.entite.Personne;
import com.example.reservation.exceptions.ObjectValidationException;
import com.example.reservation.repository.PersonneRepository;
import com.example.reservation.service.PersonneService;
import com.example.reservation.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PersonneServiceImpl implements PersonneService {

    private final PersonneRepository personneRepository;

    private final ObjectsValidator<PersonneDTO> validator;


    @Override
    public Integer save(PersonneDTO personneDTO) {
        validator.validate(personneDTO);
        Personne personne = PersonneDTO.toEntity(personneDTO);
        Optional<Personne> p = personneRepository.findByNomAndPrenomAndDateNaissance(personne.getNom(), personne.getPrenom(), personne.getDateNaissance());

        if(p.isPresent()){
            throw new EntityNotFoundException("Cette Personne existe déjà !!!");
        }
        return personneRepository.save(personne).getId();
    }

    @Override
    public PersonneDTO findById(Integer id) {
        return personneRepository.findById(id)
                .map(PersonneDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Aucune personne n'a été trouvé avec  cet ID: " + id));
    }

    @Override
    public List<PersonneDTO> findAll() {
        return personneRepository.findAll()
                .stream()
                //.map(u-> PersonneDTO.fromEntity(u))
                .map(PersonneDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Integer id) {
       Optional<Personne>  personne = personneRepository.findById(id);
       if (personne.isEmpty()){
           throw new EntityNotFoundException("Impossible d'effectuer la suppression car, Cette personne n'existe pas !!!");
       }
       personneRepository.deleteById(id);

    }
}
