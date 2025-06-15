package com.itsupport.backend.DTO;

public class TicketRequest {
    private String description;
    private Long equipementId;
    private int panneId; // facultatif

    // getters & setters
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getEquipementId() {
        return equipementId;
    }

    public void setEquipementId(Long equipementId) {
        this.equipementId = equipementId;
    }

    public Integer getPanneId() {
        return panneId;
    }

    public void setPanneId(int panneId) {
        this.panneId = panneId;
    }
}
