package com.itsupport.backend.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Panne")
public class Pannes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank(message = "Le nom est requis")
    private String Name;


    @OneToMany(mappedBy = "pannes", fetch = FetchType.LAZY )
    @JsonIgnore
    private List<SupportTiket> supportTicketList;



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public List<SupportTiket> getSupportTicketList() {
        return supportTicketList;
    }

    public void setSupportTicketList(List<SupportTiket> supportTicketList) {
        this.supportTicketList = supportTicketList;
    }

   }
