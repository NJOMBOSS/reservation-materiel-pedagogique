package com.example.reservation.controller;


import com.example.reservation.dto.ReservationDTO;
import com.example.reservation.exceptions.ObjectValidationException;
import com.example.reservation.exceptions.OperationNonPermittedException;
import com.example.reservation.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody ReservationDTO reservationDTO){
        reservationService.save(reservationDTO);
        return  ResponseEntity.ok("La reservation a été enregistrée avec succès");
    }

    @PutMapping("/{reservation-id}")
    public ResponseEntity<String> update( @PathVariable("reservation-id") Integer reservationId,
                                          @RequestBody ReservationDTO reservationDTO){
        reservationDTO.setId(reservationId);
        reservationService.update(reservationDTO);
        return  ResponseEntity.ok("La reservation a été modifié avec succès");
    }

    @GetMapping("/")
    public ResponseEntity<List<ReservationDTO>> findAll(){
        return ResponseEntity.ok(reservationService.findAll());
    }

    @GetMapping("/{reservation-id}")
    public ResponseEntity<ReservationDTO> findById(@PathVariable("reservation-id") Integer reservationId){
        return ResponseEntity.ok(reservationService.findById(reservationId));
    }

    @DeleteMapping("/{reservation-id}")
    public ResponseEntity<String> delete(@PathVariable("reservation-id")Integer reservationId){
        reservationService.delete(reservationId);
        return ResponseEntity.accepted().body("Supprssion effectuée avec succès");
    }
}
