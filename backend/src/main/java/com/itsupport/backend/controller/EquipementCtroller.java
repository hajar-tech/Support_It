package com.itsupport.backend.controller;


import com.itsupport.backend.models.Equipement;
import com.itsupport.backend.services.EquipementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/equipement")
public class EquipementCtroller {

    final EquipementService equipementService;

    public EquipementCtroller(EquipementService equipementService) {
        this.equipementService = equipementService;
    }

        //add equipement

    @PostMapping("/add")
    ResponseEntity<Equipement> addEquipement(@RequestBody Equipement equipment) {
       Equipement newEquipement = equipementService.addEquipement(equipment);
        return ResponseEntity.ok(newEquipement);
    }
}
