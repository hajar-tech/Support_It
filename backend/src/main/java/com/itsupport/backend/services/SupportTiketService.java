package com.itsupport.backend.services;

import com.itsupport.backend.DTO.TicketRequest;
import com.itsupport.backend.enums.StatusTiket;
import com.itsupport.backend.models.*;
import com.itsupport.backend.repository.EmployeRepository;
import com.itsupport.backend.repository.EquipementRepository;
import com.itsupport.backend.repository.PanneRepository;
import com.itsupport.backend.repository.SupportTiketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class SupportTiketService {

    final SupportTiketRepository tiketRepository;
    final EquipementRepository equipementRepository;
    final PanneRepository pannesRepository;
    final EmployeRepository employeRepository;

    @Autowired
    public SupportTiketService(SupportTiketRepository tiketRepository, EquipementRepository equipementRepository, PanneRepository pannesRepository, EmployeRepository employeRepository) {
        this.tiketRepository = tiketRepository;
        this.employeRepository = employeRepository;
        this.equipementRepository = equipementRepository;
        this.pannesRepository = pannesRepository;
    }

    public SupportTiket createTicket(TicketRequest request, String username) {
        Employe employe = employeRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));

        Equipement equipement = equipementRepository.findById(request.getEquipementId())
                .orElseThrow(() -> new RuntimeException("Équipement non trouvé"));

        Pannes panne = null;
        if (request.getPanneId() != null) {
            panne = pannesRepository.findById(request.getPanneId())
                    .orElseThrow(() -> new RuntimeException("Panne non trouvée"));
        }

        SupportTiket tiket = new SupportTiket();
        tiket.setDescription(request.getDescription());
        tiket.setCreatedDate(LocalDate.now());
        tiket.setTiketStatus(StatusTiket.Pending);
        tiket.setEquipement(equipement);
        tiket.setPannes(panne);
        tiket.setEmploye(employe);

        return tiketRepository.save(tiket);
    }

    public List<SupportTiket> getAllTickets() {
        return tiketRepository.findAll();
    }

    public SupportTiket getTicketById(Long id) {
        return tiketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket non trouvé"));
    }

    public List<SupportTiket> getTicketsByEmployee(String username) {
        Employe employe = employeRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Employé non trouvé"));
        return tiketRepository.findByEmploye(employe);
    }

    public List<SupportTiket> getTicketsByTechnician(String username) {
        User user = employeRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("Technicien non trouvé"));
        
        if (!(user instanceof Technicien)) {
            throw new RuntimeException("L'utilisateur n'est pas un technicien");
        }
        
        return tiketRepository.findByTechnicien((Technicien) user);
    }

    public List<SupportTiket> getTicketsByStatus(StatusTiket status) {
        return tiketRepository.findByTiketStatus(status.toString());
    }

    public SupportTiket updateTicketStatus(Long id, StatusTiket status, String username) {
        SupportTiket ticket = getTicketById(id);
        
        // Verify if the user is authorized to update the status
        if (ticket.getTechnicien() != null && !ticket.getTechnicien().getUsername().equals(username)) {
            throw new RuntimeException("Non autorisé à modifier ce ticket");
        }

        ticket.setTiketStatus(status);
        return tiketRepository.save(ticket);
    }
}
