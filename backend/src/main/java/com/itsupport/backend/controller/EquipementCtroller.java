package com.itsupport.backend.controller;


import com.itsupport.backend.models.Equipement;
import com.itsupport.backend.services.EquipementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    //update equipement

    @PutMapping("/{id}")
    public ResponseEntity<Equipement> updateEquipement( @PathVariable Long id,  @RequestBody Equipement equipmentDetails) {
        Equipement updatedEquipement = equipementService.updateEquipement(id, equipmentDetails);
        return ResponseEntity.ok(updatedEquipement);
    }
}
