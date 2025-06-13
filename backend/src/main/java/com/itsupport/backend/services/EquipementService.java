package com.itsupport.backend.services;

import com.itsupport.backend.models.Equipement;
import com.itsupport.backend.repository.EquipementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EquipementService {

    @Autowired
    final EquipementRepository equipementRepository;
    public EquipementService(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

    //add equipement
    public Equipement addEquipement(Equipement equipment) {
        return equipementRepository.save(equipment);
    }

}
