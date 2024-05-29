package com.proyect.keycenter.dao;

import com.proyect.keycenter.entities.Incidence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * IncidenceDAO es una interfaz que extiende JpaRepository para proporcionar métodos de acceso a datos
 * para la entidad Incidence.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Repository
public interface IncidenceDAO extends JpaRepository<Incidence, Integer> {
    /**
     * Recupera todas las incidencias de la base de datos.
     * @return una lista de todas las incidencias.
     */
    List<Incidence> findAll();

    /**
     * Recupera una incidencia por su ID.
     * @param id el identificador de la incidencia.
     * @return un Optional que contiene la incidencia si se encuentra, o vacío si no.
     */
    Optional<Incidence> findById(Integer id);
}
