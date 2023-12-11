package com.example.reservation.service;

import com.example.reservation.dto.PersonneDTO;

import java.util.List;

public interface PersonneService {

   Integer save(PersonneDTO personneDTO);

    PersonneDTO findById(Integer id);

    List<PersonneDTO> findAll();

    void delete(Integer id);
}
