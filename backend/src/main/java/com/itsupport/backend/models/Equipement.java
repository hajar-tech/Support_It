package com.itsupport.backend.models;

import jakarta.persistence.*;

@Entity
public class Equipement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private long idEquipement;
    private String nameEquipement;
    private String descriptionEquipement;
    private String typeEquipement;
    private String dataEquipement;
    private String statusEquipement;

//    @OneToMany(mappedBy = "equipement")
//    private List<Panne> pannes;
//


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

    public String getTypeEquipement() {
        return typeEquipement;
    }

    public void setTypeEquipement(String typeEquipement) {
        this.typeEquipement = typeEquipement;
    }

    public String getDataEquipement() {
        return dataEquipement;
    }

    public void setDataEquipement(String dataEquipement) {
        this.dataEquipement = dataEquipement;
    }

    public String getStatusEquipement() {
        return statusEquipement;
    }

    public void setStatusEquipement(String statusEquipement) {
        this.statusEquipement = statusEquipement;
    }
}
