package com.proyect.keycenter.modelo;

import com.google.zxing.WriterException;
import com.proyect.keycenter.modelo.Qr;
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
    private long user_id;

    public Llave() {
    }

    public void setQr(Qr qr) {
        this.qr = qr;
        qr_id=qr.getId();
    }
}
