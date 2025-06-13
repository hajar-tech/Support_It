package com.itsupport.backend.controller;


import com.itsupport.backend.models.Pannes;
import com.itsupport.backend.services.PanneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/Panne")
@RestController
public class PanneController {

    private final PanneService panneService;
    @Autowired
    public PanneController(PanneService panneService) {
        this.panneService = panneService;
    }

    @PostMapping("/add")
    public Pannes addPanne(@RequestBody Pannes pannes) {
        return panneService.addPanne(pannes);
    }

    @GetMapping
    public List<Pannes> getAllPannes() {
        return panneService.getAllPannes();
    }

    @PutMapping("/{id}")
    public Pannes updatePanne(@RequestBody Pannes pannes) {
        return panneService.updatePanne(pannes);
    }
}
