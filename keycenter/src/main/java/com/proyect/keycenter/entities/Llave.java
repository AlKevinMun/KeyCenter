package com.proyect.keycenter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "llave")
public class Llave {
    @Transient
    Qr qr;
    @Id
    private long id;
    private String room_name;
    private Timestamp hora;
    private long qr_id;
    private long[] user_id;
    private int cantidad;

    public Llave() {
    }

    public void setQr(Qr qr) {
        this.qr = qr;
        qr_id=qr.getId();
    }
}
