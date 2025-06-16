package com.itsupport.backend.controller;


import com.itsupport.backend.DTO.PanneDto;
import com.itsupport.backend.models.Pannes;
import com.itsupport.backend.services.PanneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@CrossOrigin(origins = "http://localhost:4200/")
@RequestMapping("/Panne")
@RestController

public class PanneController {

    private final PanneService panneService;
    @Autowired
    public PanneController(PanneService panneService) {
        this.panneService = panneService;
    }

    @PostMapping("/add")
    public ResponseEntity<Pannes> addPanne(@Valid @RequestBody PanneDto panneDto) {
        Pannes panne = new Pannes();
        panne.setName(panneDto.name());
        Pannes savedPanne = panneService.addPanne(panne);
        return new ResponseEntity<>(savedPanne, HttpStatus.CREATED);
    }

    @GetMapping
    public List<Pannes> getAllPannes() {
        return panneService.getAllPannes();
    }

//    @PutMapping("/{id}")
//    public Pannes updatePanne(@RequestBody Pannes pannes) {
//        return panneService.updatePanne(pannes);
//    }
@PutMapping("/{id}")
public ResponseEntity<Pannes> updatePanne(@PathVariable int id, @Valid @RequestBody PanneDto panneDto) {
    Optional<Pannes> existingPanne = panneService.getPanneById(id);
    if (existingPanne.isPresent()) {
        Pannes panne = existingPanne.get();
        panne.setName(panneDto.name());
        Pannes updatedPanne = panneService.updatePanne(panne);
        return ResponseEntity.ok(updatedPanne);
    }
    return ResponseEntity.notFound().build();
}

    @DeleteMapping("/{id}")
    public void deletePanne(@PathVariable int id) {
        panneService.deletePanne(id);
    }
}
