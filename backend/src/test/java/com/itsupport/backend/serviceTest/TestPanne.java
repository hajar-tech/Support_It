package com.itsupport.backend.serviceTest;

import com.itsupport.backend.models.Pannes;
import com.itsupport.backend.repository.PanneRepository;
import com.itsupport.backend.services.PanneService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)

public class TestPanne {
    @Mock
    PanneRepository panneRepository;

    @InjectMocks
    PanneService panneService;

    @Test
    void TestAddPanne(){
        Pannes panne = new Pannes();

        panne.setName("Panne");

        when(panneRepository.save(panne)).thenReturn(panne);

        panneService.addPanne(panne);
        assertEquals("Panne", panne.getName());
    }
}
