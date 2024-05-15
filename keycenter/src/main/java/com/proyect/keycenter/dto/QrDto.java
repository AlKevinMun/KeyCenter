package com.proyect.keycenter.dto;

import com.proyect.keycenter.entities.Qr;
import lombok.Data;

@Data
public class QrDto {
    private long id;
    private byte[] data;

    public QrDto() {
    }

    public QrDto(Qr qr) {
        this.id = qr.getId();
        this.data = qr.getData();
    }
}
