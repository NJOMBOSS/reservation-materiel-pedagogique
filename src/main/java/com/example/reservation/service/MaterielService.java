package com.example.reservation.service;

import com.example.reservation.dto.MaterielDTO;
import jakarta.persistence.criteria.CriteriaBuilder;


import java.util.List;

public interface MaterielService {

    Integer save(MaterielDTO materielDTO);

    MaterielDTO findById(Integer id);

   // MaterielDTO findByLibelle(String nom);

    List<MaterielDTO> findAll();

  void update(MaterielDTO materielDTO);

    void delete(Integer id);
}
