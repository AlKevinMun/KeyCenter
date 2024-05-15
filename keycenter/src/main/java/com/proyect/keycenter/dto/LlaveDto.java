package com.proyect.keycenter.dto;

import com.proyect.keycenter.entities.Llave;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class LlaveDto {
    private long id;
    private String room_name;
    private Timestamp hora;
    private long qr_id;
    private long[] user_id;
    private int cantidad;

    public LlaveDto() {
    }

    public LlaveDto(Llave llave) {
        id = llave.getId();
        room_name = llave.getRoom_name();
        hora = llave.getHora();
        qr_id = llave.getQr_id();
        user_id = llave.getUser_id();
        cantidad = llave.getCantidad();
    }
}
