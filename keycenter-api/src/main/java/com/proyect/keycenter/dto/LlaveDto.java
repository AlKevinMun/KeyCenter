package com.proyect.keycenter.dto;

import com.proyect.keycenter.entities.Llave;
import lombok.Data;

import java.sql.Timestamp;

/**
 * LlaveDto es una clase de transferencia de datos para la entidad Llave.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Data
public class LlaveDto {
    /**
     * El identificador único de la llave.
     */
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
    public LlaveDto() {
    }

    /**
     * Constructor que crea un LlaveDto a partir de una entidad Llave.
     * @param llave la entidad Llave.
     */
    public LlaveDto(Llave llave) {
        id = llave.getId();
        room_name = llave.getRoom_name();
        hora = llave.getHora();
        qr_id = llave.getQr_id();
        user_id = llave.getUser_id();
    }
}
