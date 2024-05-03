package com.proyect.keycenter.modelo;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dao.UserDAO;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

@Data
@Entity
@Table(name = "users")
public class User {
    @Transient
    Qr qr;
    @Transient
    static long idMas;
    @Id
    private long id;
    private String username;
    private String password;
    private String email;
    private String rol;
    private byte[] profile_picture;
    private long qr_id;

    public User() {
    }

    public static void setIdMas(long idMas) {
        User.idMas = idMas;
    }

    public static long getIdMas() {
        idMas++;
        return idMas;
    }

    public void setQr(Qr qr) {
        this.qr = qr;
        qr_id=qr.getId();
    }
}
