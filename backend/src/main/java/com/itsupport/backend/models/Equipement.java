package com.itsupport.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itsupport.backend.enums.EquipementStatus;
import com.itsupport.backend.enums.TypeEquipement;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class Equipement {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idEquipement;
    private String nameEquipement;
    private String descriptionEquipement;
    private LocalDate dateEquipement;


   @Enumerated(EnumType.STRING)
   @Column(name = "type" , nullable = false , length = 100)
   private TypeEquipement type;

   @Enumerated(EnumType.STRING)
   @Column(name = "status" , nullable = false , length = 100)
   private EquipementStatus status;

//    @OneToMany(mappedBy = "equipement")
//    private List<Panne> pannes;
//

   @OneToMany(mappedBy = "equipement" , cascade = CascadeType.ALL ,orphanRemoval = true)
   @JsonIgnore
   private List<SupportTiket> supportTikets;

    public List<SupportTiket> getSupportTikets() {
        return supportTikets;
    }

    public void setSupportTikets(List<SupportTiket> supportTikets) {
        this.supportTikets = supportTikets;
    }

    public LocalDate getDateEquipement() {
        return dateEquipement;
    }

    public void setDateEquipement(LocalDate dateEquipement) {
        this.dateEquipement = dateEquipement;
    }

    public TypeEquipement getType() {
        return type;
    }

    public void setType(TypeEquipement type) {
        this.type = type;
    }

    public EquipementStatus getStatus() {
        return status;
    }

    public void setStatus(EquipementStatus status) {
        this.status = status;
    }

    public long getIdEquipement() {
        return idEquipement;
    }

    public void setIdEquipement(long idEquipement) {
        this.idEquipement = idEquipement;
    }

    public String getNameEquipement() {
        return nameEquipement;
    }

    public void setNameEquipement(String nameEquipement) {
        this.nameEquipement = nameEquipement;
    }

    public String getDescriptionEquipement() {
        return descriptionEquipement;
    }

    public void setDescriptionEquipement(String descriptionEquipement) {
        this.descriptionEquipement = descriptionEquipement;
    }







}
