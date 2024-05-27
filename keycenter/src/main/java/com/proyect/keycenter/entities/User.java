package com.proyect.keycenter.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {
    @Transient
    Qr qr;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String username;
    private String password;
    private String email;
    private String rol;
    private byte[] profile_picture;
    private long qr_id;

    public User() {
    }


    public void setQr(Qr qr) {
        this.qr = qr;
        qr_id=qr.getId();
    }

    public User(long id, String username, String password, String email, String rol, byte[] profile_picture, long qr_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.profile_picture = profile_picture;
        this.qr_id = qr_id;
    }
}
