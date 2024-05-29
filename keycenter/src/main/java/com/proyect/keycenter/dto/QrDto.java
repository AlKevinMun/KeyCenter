package com.proyect.keycenter.dto;

import com.proyect.keycenter.entities.Qr;
import lombok.Data;

/**
 * La clase QrDto es un Data Transfer Object (DTO) para la entidad Qr.
 * Se utiliza para transferir datos de códigos QR sin exponer la entidad completa.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Data
public class QrDto {
    /**
     * El identificador único del código QR.
     */
    private long id;

    /**
     * Los datos binarios del código QR.
     */
    private byte[] data;

    /**
     * Constructor por defecto.
     */
    public QrDto() {
    }

    /**
     * Constructor que inicializa un QrDto a partir de una entidad Qr.
     * @param qr la entidad Qr de la cual se copian los datos.
     */
    public QrDto(Qr qr) {
        this.id = qr.getId();
        this.data = qr.getData();
    }
}
