package com.itsupport.backend.DTO;

import jakarta.validation.constraints.NotBlank;

import java.util.Date;

public record PanneDto(
        @NotBlank(message = "Le Titre est requis")
        String name



        ) {


}
