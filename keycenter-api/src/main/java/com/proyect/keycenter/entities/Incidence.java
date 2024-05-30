package com.proyect.keycenter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * La clase Incidence representa una incidencia reportada por un usuario.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Data
@Entity
@Table(name = "incidence")
public class Incidence {
    /**
     * El identificador único de la incidencia.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * El tema de la incidencia.
     */
    private String topic;

    /**
     * La descripción detallada de la incidencia.
     */
    private String description;

    /**
     * La fecha y hora en que se envió la incidencia.
     */
    private Timestamp send_date;

    /**
     * El estado de la incidencia.
     * 0 representa una incidencia abierta, mientras que 1 representa una incidencia cerrada.
     */
    private int state;

    /**
     * El identificador del usuario que reportó la incidencia.
     */
    private int user_id;

    /**
     * Constructor por defecto.
     */
    public Incidence() {
    }

    /**
     * Constructor que inicializa una incidencia con el tema, la descripción y el ID de usuario especificados.
     * @param topic el tema de la incidencia.
     * @param description la descripción detallada de la incidencia.
     * @param user_id el identificador del usuario que reportó la incidencia.
     */
    public Incidence(String topic, String description, int user_id) {
        this.topic = topic;
        this.description = description;
        this.send_date = new Timestamp(new Date().getTime());
        this.state = 0;
        this.user_id = user_id;
    }

}
