package com.itsupport.backend.repository;

import com.itsupport.backend.models.Pannes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PanneRepository extends JpaRepository<Pannes, Integer> {
}
