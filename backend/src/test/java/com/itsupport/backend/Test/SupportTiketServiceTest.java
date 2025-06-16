package com.itsupport.backend.Test;

import com.itsupport.backend.DTO.TicketRequest;
import com.itsupport.backend.enums.StatusTiket;
import com.itsupport.backend.models.*;
import com.itsupport.backend.repository.EmployeRepository;
import com.itsupport.backend.repository.EquipementRepository;
import com.itsupport.backend.repository.PanneRepository;
import com.itsupport.backend.repository.SupportTiketRepository;
import com.itsupport.backend.services.SupportTiketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SupportTiketServiceTest {

    @Mock
    private EmployeRepository employeRepository;

    @Mock
    private EquipementRepository equipementRepository;

    @Mock
    private PanneRepository panneRepository;

    @Mock
    private SupportTiketRepository supportTiketRepository;

    @InjectMocks
    private SupportTiketService supportTiketService;

    @Test
    public void testCreateTicket_withValidData_shouldReturnSavedTicket() {
        // Arrange
        String username = "testuser";
        TicketRequest request = new TicketRequest();
        request.setDescription("Problème d'affichage");
        request.setEquipementId(1L);
        request.setPanneId(2);

        Employe mockEmploye = new Employe();
        mockEmploye.setId(1L);
        mockEmploye.setUsername(username);

        Equipement mockEquipement = new Equipement();
        mockEquipement.setIdEquipement(1L);
        mockEquipement.setNameEquipement("PC Dell");

        Pannes mockPanne = new Pannes();
        mockPanne.setId(2);
        mockPanne.setName("Écran noir");

        SupportTiket savedTiket = new SupportTiket();
        savedTiket.setTiketId(10L);
        savedTiket.setDescription(request.getDescription());
        savedTiket.setCreatedDate(LocalDate.now());
        savedTiket.setTiketStatus(StatusTiket.Pending);
        savedTiket.setEmploye(mockEmploye);
        savedTiket.setEquipement(mockEquipement);
        savedTiket.setPannes(mockPanne);

        when(employeRepository.findByUsername(username)).thenReturn(Optional.of(mockEmploye));
        when(equipementRepository.findById(1L)).thenReturn(Optional.of(mockEquipement));
        when(panneRepository.findById(2)).thenReturn(Optional.of(mockPanne));
        when(supportTiketRepository.save(any(SupportTiket.class))).thenReturn(savedTiket);

        // Act
        SupportTiket result = supportTiketService.createTicket(request, username);

        // Assert
        assertNotNull(result);
        assertEquals(StatusTiket.Pending, result.getTiketStatus());
        assertEquals("Problème d'affichage", result.getDescription());
        assertEquals(mockEmploye, result.getEmploye());
        assertEquals(mockEquipement, result.getEquipement());
        assertEquals(mockPanne, result.getPannes());

        verify(supportTiketRepository, times(1)).save(any(SupportTiket.class));
    }

}
