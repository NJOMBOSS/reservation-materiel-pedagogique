package com.example.reservation.controller;


import com.example.reservation.dto.MaterielDTO;
import com.example.reservation.dto.PersonneDTO;
import com.example.reservation.service.impl.MaterielService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/materiels")
@RequiredArgsConstructor
public class MaterielController {

    private final MaterielService materielService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> save(@RequestBody MaterielDTO materielDTO){
       materielService.save(materielDTO);
        return  ResponseEntity.ok("Le matériel a été enregistrée avec succès");
    }

    @PutMapping("/{materiel-id}")
    public ResponseEntity<String> update( @PathVariable("materiel-id") Integer materielId,
                                          @RequestBody MaterielDTO materielDTO){
        materielDTO.setId(materielId);
        materielService.update(materielDTO);
        return  ResponseEntity.ok("Le matériel a été modifié avec succès");
    }

    @GetMapping("/")
    public ResponseEntity<List<MaterielDTO>> findAll(){
        return ResponseEntity.ok(materielService.findAll());
    }

    @GetMapping("/{materiel-id}")
    public ResponseEntity<MaterielDTO> findById(@PathVariable("materiel-id") Integer materielId){
        return ResponseEntity.ok(materielService.findById(materielId));
    }

   /* @GetMapping("/{nom}")
    public ResponseEntity<MaterielDTO> findByLibelle(@PathVariable("nom") String nom){
        return ResponseEntity.ok(materielService.findByLibelle(nom));
    }*/

    @DeleteMapping("/{materiel-id}")
    public ResponseEntity<String> delete(@PathVariable("materiel-id")Integer materielId){
        materielService.delete(materielId);
        return ResponseEntity.accepted().body("Supprssion effectuée avec succès");
    }
}
