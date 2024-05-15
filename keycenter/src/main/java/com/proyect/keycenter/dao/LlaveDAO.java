package com.proyect.keycenter.dao;

import com.proyect.keycenter.entities.Llave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LlaveDAO extends JpaRepository<Llave, Integer> {
    List<Llave> findAll();
    Optional<Llave> findById(Integer id);
}