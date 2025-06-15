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

    public Long getTiketId() {
        return tiketId;
    }

    public void setTiketId(Long tiketId) {
        this.tiketId = tiketId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDate createdDate) {
        this.createdDate = createdDate;
    }

    public StatusTiket getTiketStatus() {
        return tiketStatus;
    }

    public void setTiketStatus(StatusTiket tiketStatus) {
        this.tiketStatus = tiketStatus;
    }

    public Equipement getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement equipement) {
        this.equipement = equipement;
    }

    public Pannes getPannes() {
        return pannes;
    }

    public void setPannes(Pannes pannes) {
        this.pannes = pannes;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Technicien getTechnicien() {
        return technicien;
    }

    public void setTechnicien(Technicien technicien) {
        this.technicien = technicien;
    }

    public SupportTiket(Long tiketId, String description, LocalDate createdDate, StatusTiket tiketStatus, Equipement equipement, Pannes pannes, Employe employe, Technicien technicien) {
        this.tiketId = tiketId;
        this.description = description;
        this.createdDate = createdDate;
        this.tiketStatus = tiketStatus;
        this.equipement = equipement;
        this.pannes = pannes;
        this.employe = employe;
        this.technicien = technicien;
    }

    public SupportTiket() {
    }
}
