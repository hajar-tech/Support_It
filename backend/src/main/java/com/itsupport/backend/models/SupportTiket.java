package com.itsupport.backend.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.backend.enums.StatusTiket;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class SupportTiket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tiketId;
    private String description;
    private LocalDate createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "tiketStatus" , nullable = false , length = 100)
    private StatusTiket tiketStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "equipement_id")
    private Equipement equipement;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "panne_id")
    private Pannes pannes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Employe employe;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    @JsonIgnore
    private Technicien technicien;
}
