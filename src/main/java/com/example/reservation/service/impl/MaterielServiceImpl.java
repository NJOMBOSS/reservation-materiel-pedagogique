package com.example.reservation.service.impl;

import com.example.reservation.dto.MaterielDTO;
import com.example.reservation.entite.Materiel;
import com.example.reservation.repository.MaterielRepository;
import com.example.reservation.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MaterielServiceImpl implements MaterielService{

    private final MaterielRepository materielRepository;

    private final ObjectsValidator<MaterielDTO> validator;
    @Override
    public Integer save(MaterielDTO materielDTO) {
        validator.validate(materielDTO);
        Materiel materiel = MaterielDTO.toEntity(materielDTO);
        Optional<Materiel> mat = materielRepository.findByLibelle(materiel.getLibelle());
        if(mat.isPresent()){
            throw new EntityNotFoundException("Ce Matériel a déjà été enregistré !!!");
        }
        return materielRepository.save(materiel).getId();
    }

    @Override
    public MaterielDTO findById(Integer id) {

        return   materielRepository.findById(id)
                .map(MaterielDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Ce Materiel n'existe pas !!!"));
    }

/*    @Override
    public MaterielDTO findByLibelle(String nom) {
        return   materielRepository.findByLibelle(nom)
                .map(MaterielDTO::fromEntity)
                .orElseThrow(()-> new EntityNotFoundException("Ce Materiel n'existe pas !!!"));
    }*/

    @Override
    public List<MaterielDTO> findAll() {
        return materielRepository.findAll()
                .stream()
                .map(MaterielDTO::fromEntity)
                .collect(Collectors.toList());
    }

    @Override
    public void update(MaterielDTO materielDTO) {
        Materiel materiel = MaterielDTO.toEntity(materielDTO);
        Optional<Materiel> existeMateriel = materielRepository.findById(materiel.getId());
        if(existeMateriel.isEmpty()){
            throw new EntityNotFoundException("Ce matérile n'existe pas !!!");
        }

     Materiel existe = existeMateriel.get();
        existe.setLibelle(materiel.getLibelle());
        existe.setQuantite(materiel.getQuantite());
        existe.setEstEnEtat(materiel.isEstEnEtat());

        materielRepository.save(existe);

    }

    @Override
    public void delete(Integer id) {
        Optional<Materiel> materiel = materielRepository.findById(id);

        if(materiel.isEmpty()){
            throw new EntityNotFoundException("Ce matériel n'existe pas !!!");
        }
          materielRepository.deleteById(id);
    }
}
