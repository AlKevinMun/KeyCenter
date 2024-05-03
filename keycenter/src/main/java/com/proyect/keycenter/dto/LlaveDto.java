package com.proyect.keycenter.dto;

import com.proyect.keycenter.modelo.*;
import lombok.Data;

@Data
public class LlaveDto {
    private long id;
    private String room_name;
    private long qr_id;
    private long user_id;

    public LlaveDto() {
    }

    public LlaveDto(Llave llave) {
        id = llave.getId();
        room_name = llave.getRoom_name();
        qr_id = llave.getQr_id();
        user_id = llave.getUser_id();
    }
}