package com.proyect.keycenter.dao;

import com.proyect.keycenter.entities.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IncidenceDAO extends JpaRepository<Incidence, Integer> {
    List<Incidence> findAll();
    Optional<Incidence> findById(Integer id);
}
