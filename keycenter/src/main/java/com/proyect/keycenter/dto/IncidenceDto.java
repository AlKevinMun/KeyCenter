package com.proyect.keycenter.dto;

import com.proyect.keycenter.entities.Incidence;
import lombok.Data;

import java.sql.Timestamp;

/**
 * IncidenceDto es una clase de transferencia de datos para la entidad Incidence.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Data
public class IncidenceDto {
    /**
     * El identificador único de la incidencia.
     */
    private long id;

    /**
     * El tema de la incidencia.
     */
    private String topic;

    /**
     * La descripción de la incidencia.
     */
    private String description;

    /**
     * La fecha de envío de la incidencia.
     */
    private Timestamp send_date;

    /**
     * El estado de la incidencia.
     */
    private int state;

    /**
     * El identificador del usuario asociado a la incidencia.
     */
    private int user_id;

    /**
     * Constructor por defecto.
     */
    public IncidenceDto() {
    }

    /**
     * Constructor que crea un IncidenceDto a partir de una entidad Incidence.
     * @param incidence la entidad Incidence.
     */
    public IncidenceDto(Incidence incidence) {
        id = incidence.getId();
        topic = incidence.getTopic();
        description = incidence.getDescription();
        send_date = incidence.getSend_date();
        state = incidence.getState();
        user_id = incidence.getUser_id();
    }
}
