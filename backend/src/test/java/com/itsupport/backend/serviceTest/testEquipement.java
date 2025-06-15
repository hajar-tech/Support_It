package com.itsupport.backend.serviceTest;

import com.itsupport.backend.enums.EquipementStatus;
import com.itsupport.backend.models.Equipement;
import com.itsupport.backend.repository.EquipementRepository;
import com.itsupport.backend.services.EquipementService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class testEquipement {

    @Mock// pour créer une simulation de repository
    EquipementRepository equipementRepository;

    @InjectMocks
    EquipementService equipementService; //injecter le service dans le mock

    @Test
    void testGetAll() {

        //création de l'objet
        Equipement equipement1 = new Equipement();
        Equipement equipement2 = new Equipement();

        equipement1.setNameEquipement("testHP");
        equipement2.setNameEquipement("testDELL");


        List<Equipement> equipements = Arrays.asList(equipement1, equipement2); //pour ajouter tout à la fois à la place de .add()

        when(equipementRepository.findAll()).thenReturn(equipements);
        List<Equipement> results = equipementService.displayAllEquipements();

       assertEquals(2,results.size());
    }

    //test pour la fonction d'ajoute

    @Test
    void testAddEquipement() {

        Equipement equipement = new Equipement();

        equipement.setNameEquipement("testHP");
        equipement.setStatus(EquipementStatus.ACTIVE);

        when(equipementRepository.save(equipement)).thenReturn(equipement);

        Equipement savedEquipement = equipementService.addEquipement(equipement);

        assertEquals("testHP" , savedEquipement.getNameEquipement());
        assertEquals(EquipementStatus.ACTIVE ,  savedEquipement.getStatus());
    }

}
