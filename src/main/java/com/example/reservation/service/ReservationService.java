package com.example.reservation.service;

import com.example.reservation.dto.MaterielDTO;
import com.example.reservation.dto.PersonneDTO;
import com.example.reservation.dto.ReservationDTO;

import java.util.List;

public interface ReservationService {

    Integer save(ReservationDTO reservationDTO);

    void update(ReservationDTO reservationDTO);

    List<ReservationDTO> findAll();

    ReservationDTO findById(Integer id);

    void delete(Integer id);
}
