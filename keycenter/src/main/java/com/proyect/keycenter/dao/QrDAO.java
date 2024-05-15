package com.proyect.keycenter.dao;

import com.proyect.keycenter.entities.Qr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QrDAO extends JpaRepository<Qr, Integer> {
    List<Qr> findAll();
    Optional<Qr> findById(Integer id);
}