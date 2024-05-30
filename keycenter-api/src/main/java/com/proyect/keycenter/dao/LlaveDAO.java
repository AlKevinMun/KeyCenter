package com.proyect.keycenter.dao;

import com.proyect.keycenter.entities.Llave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * LlaveDAO es una interfaz que extiende JpaRepository para proporcionar métodos de acceso a datos
 * para la entidad Llave.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Repository
public interface LlaveDAO extends JpaRepository<Llave, Integer> {
    /**
     * Recupera todas las llaves de la base de datos.
     * @return una lista de todas las llaves.
     */
    List<Llave> findAll();

    /**
     * Recupera una llave por su ID.
     * @param id el identificador de la llave.
     * @return un Optional que contiene la llave si se encuentra, o vacío si no.
     */
    Optional<Llave> findById(Integer id);
}