package com.itsupport.backend.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Technicien")
public class Technicien extends User {
}
