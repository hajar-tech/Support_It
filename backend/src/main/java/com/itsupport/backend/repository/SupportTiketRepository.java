package com.itsupport.backend.repository;

import com.itsupport.backend.models.SupportTiket;
import com.itsupport.backend.models.Employe;
import com.itsupport.backend.models.Technicien;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SupportTiketRepository extends JpaRepository<SupportTiket, Long> {

    List<SupportTiket> findByEmploye(Employe employe);

    List<SupportTiket> findByTechnicien(Technicien technicien);

    List<SupportTiket> findByTiketStatus(String status);
}
