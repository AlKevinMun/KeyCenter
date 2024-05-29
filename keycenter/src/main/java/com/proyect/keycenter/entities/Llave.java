package com.proyect.keycenter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

/**
 * La clase Llave representa una entidad que mapea la tabla "llave" en la base de datos.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Data
@Entity
@Table(name = "llave")
public class Llave {
    /**
     * Código QR asociado a la llave. No se almacena en la base de datos.
     */
    @Transient
    private Qr qr;

    /**
     * El identificador único de la llave.
     */
    @Id
    private long id;

    /**
     * El nombre de la sala asociada a la llave.
     */
    private String room_name;

    /**
     * La hora asociada a la llave.
     */
    private Timestamp hora;

    /**
     * El identificador del código QR asociado a la llave.
     */
    private long qr_id;

    /**
     * El identificador del usuario asociado a la llave.
     */
    private long user_id;

    /**
     * Constructor por defecto.
     */
    public Llave() {
    }

    /**
     * Asigna un código QR a la llave y actualiza el identificador del código QR.
     * @param qr el código QR a asignar.
     */
    public void setQr(Qr qr) {
        this.qr = qr;
        qr_id=qr.getId();
    }
}
