package com.proyect.keycenter.dao;

import com.proyect.keycenter.entities.Qr;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * QrDAO es una interfaz que extiende JpaRepository para manejar operaciones CRUD de la entidad Qr.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Repository
public interface QrDAO extends JpaRepository<Qr, Integer> {
    /**
     * Recupera todos los códigos QR almacenados en la base de datos.
     * @return una lista de todos los códigos QR.
     */
    List<Qr> findAll();

    /**
     * Busca un código QR por su identificador.
     * @param id el identificador del código QR.
     * @return un Optional que contiene el código QR si se encuentra, o vacío si no.
     */
    Optional<Qr> findById(Integer id);
}