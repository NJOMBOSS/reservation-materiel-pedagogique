package com.example.reservation.controller;

import com.example.reservation.dto.MaterielDTO;
import com.example.reservation.dto.PersonneDTO;
import com.example.reservation.service.PersonneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/personnes")
@RequiredArgsConstructor
public class PersonneController {

    private final PersonneService personneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody PersonneDTO personneDTO){
        personneService.save(personneDTO);
        return  ResponseEntity.ok("La personne a été enregistrée avec succès");
    }

    @PutMapping("/{personne-id}")
    public ResponseEntity<String> update( @PathVariable("personne-id") Integer personneId,
                                          @RequestBody PersonneDTO personneDTO){
        personneDTO.setId(personneId);
        personneService.update(personneDTO);
        return  ResponseEntity.ok("La personne a été modifié avec succès");
    }

    @GetMapping("/")
    public ResponseEntity<List<PersonneDTO>> findAll(){
        return ResponseEntity.ok(personneService.findAll());
    }

    @GetMapping("/{personne-id}")
    public ResponseEntity<PersonneDTO> findById(@PathVariable("personne-id") Integer personneId){
        return ResponseEntity.ok(personneService.findById(personneId));
    }

    @DeleteMapping("/{personne-id}")
    public ResponseEntity<String> delete(@PathVariable("personne-id")Integer personneId){
        personneService.delete(personneId);
        return ResponseEntity.accepted().body("Supprssion effectuée avec succès");
    }
}
