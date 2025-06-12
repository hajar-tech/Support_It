package com.itsupport.backend.services;


import com.itsupport.backend.models.Pannes;
import com.itsupport.backend.repository.PanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PanneService {

    private final PanneRepository panneRepository;

    @Autowired
    public PanneService(PanneRepository panneRepository) {
        this.panneRepository = panneRepository;
    }

    public Pannes addPanne(Pannes panne) {
        return panneRepository.save(panne);
    }

}
