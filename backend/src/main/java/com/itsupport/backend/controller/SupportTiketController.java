package com.itsupport.backend.controller;

import com.itsupport.backend.DTO.TicketRequest;
import com.itsupport.backend.enums.StatusTiket;
import com.itsupport.backend.models.SupportTiket;
import com.itsupport.backend.repository.SupportTiketRepository;
import com.itsupport.backend.services.SupportTiketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/tickets")

public class SupportTiketController {

    final SupportTiketService tiketService;

    @Autowired
    public SupportTiketController(SupportTiketService tiketService) {
        this.tiketService = tiketService;
    }

    @PostMapping
    public ResponseEntity<SupportTiket> createTicket(@RequestBody TicketRequest request, Principal principal) {
        SupportTiket ticket = tiketService.createTicket(request, principal.getName());
        return ResponseEntity.ok(ticket);
    }

    @GetMapping
    public ResponseEntity<List<SupportTiket>> getAllTickets() {
        List<SupportTiket> tickets = tiketService.getAllTickets();
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupportTiket> getTicketById(@PathVariable Long id) {
        SupportTiket ticket = tiketService.getTicketById(id);
        return ResponseEntity.ok(ticket);
    }

    @GetMapping("/employee")
    public ResponseEntity<List<SupportTiket>> getTicketsByEmployee(Principal principal) {
        List<SupportTiket> tickets = tiketService.getTicketsByEmployee(principal.getName());
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/technician")
    public ResponseEntity<List<SupportTiket>> getTicketsByTechnician(Principal principal) {
        List<SupportTiket> tickets = tiketService.getTicketsByTechnician(principal.getName());
        return ResponseEntity.ok(tickets);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<SupportTiket>> getTicketsByStatus(@PathVariable StatusTiket status) {
        List<SupportTiket> tickets = tiketService.getTicketsByStatus(status);
        return ResponseEntity.ok(tickets);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<SupportTiket> updateTicketStatus(
            @PathVariable Long id,
            @RequestParam StatusTiket status,
            Principal principal) {
        SupportTiket ticket = tiketService.updateTicketStatus(id, status, principal.getName());
        return ResponseEntity.ok(ticket);
    }
}
