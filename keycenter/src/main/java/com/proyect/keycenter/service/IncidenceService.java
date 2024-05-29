package com.proyect.keycenter.service;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dao.*;
import com.proyect.keycenter.dto.UserDto;
import com.proyect.keycenter.entities.Incidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * IncidenceService es una clase de servicio que proporciona métodos para interactuar con las incidencias en la base de datos.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Service
public class IncidenceService {
    /**
     * Interactua con la base de datos de incidencias.
     */
    @Autowired
    IncidenceDAO incidenceDAO;

    /**
     * Lee todas las incidencias de la base de datos.
     * @return una lista de todas las incidencias.
     */
    public List<Incidence> readAllIncidence() {
        return incidenceDAO.findAll();
    }

    /**
     * Obtiene una incidencia por su ID.
     * @param id el ID de la incidencia.
     * @return la incidencia con el ID especificado, o null si no se encuentra.
     */
    public Incidence getIncidenceById(Integer id) {
        return incidenceDAO.findById(id).orElse(null);
    }

    /**
     * Añade una nueva incidencia a la base de datos.
     * @param incidence la incidencia a añadir.
     * @return la incidencia añadida.
     * @throws WriterException si hay un error al generar el código QR.
     */
    @Transactional
    public Incidence addIncidence(Incidence incidence) throws WriterException {
        return incidenceDAO.save(incidence);
    }

    /**
     * Elimina una incidencia de la base de datos por su ID.
     * @param id el ID de la incidencia a eliminar.
     */
    public void deleteIncidence(Integer id){
        incidenceDAO.deleteById(id);
    }

    /**
     * Actualiza una incidencia en la base de datos.
     * @param incidence la incidencia actualizada.
     * @return la incidencia actualizada.
     */
    public Incidence updateIncidence(Incidence incidence) {
        return incidenceDAO.save(incidence);
    }
}
