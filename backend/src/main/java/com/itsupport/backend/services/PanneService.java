package com.itsupport.backend.services;


import com.itsupport.backend.models.Pannes;
import com.itsupport.backend.repository.PanneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    public List<Pannes> getAllPannes() {
        return panneRepository.findAll();
    }

    public Optional<Pannes> getPanneById(int id) {
        return panneRepository.findById(id);
    }

    public Pannes updatePanne(Pannes panne) {
        if (!panneRepository.existsById(panne.getId())) {
        throw new IllegalArgumentException("Aucune panne trouvée avec l'ID: " + panne.getId());
    }

        return panneRepository.save(panne);
    }


    public void deletePanne(int id) {
        panneRepository.deleteById(id);
    }
}
