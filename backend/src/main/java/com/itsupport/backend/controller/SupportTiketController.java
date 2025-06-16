package com.itsupport.backend.controller;

import com.itsupport.backend.DTO.TicketRequest;
import com.itsupport.backend.models.SupportTiket;
import com.itsupport.backend.repository.SupportTiketRepository;
import com.itsupport.backend.services.SupportTiketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "http://localhost:4200/")

public class SupportTiketController {


    final SupportTiketService tiketService;

    @Autowired
    public SupportTiketController(SupportTiketService tiketService){
        this.tiketService = tiketService;
    }

    @PostMapping
    public ResponseEntity<SupportTiket> createTicket(@RequestBody TicketRequest request, Principal principal) {
        SupportTiket ticket = tiketService.createTicket(request, principal.getName());
        return ResponseEntity.ok(ticket);
    }
}
