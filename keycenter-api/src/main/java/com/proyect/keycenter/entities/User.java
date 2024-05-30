package com.proyect.keycenter.entities;

import jakarta.persistence.*;
import lombok.Data;

/**
 * La clase User representa a un usuario en el sistema.
 * Se utiliza para mapear la tabla "users" en la base de datos.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Data
@Entity
@Table(name = "users")
public class User {
    /**
     * El objeto Qr asociado al usuario.
     * Esta propiedad no se persiste en la base de datos.
     */
    @Transient
    Qr qr;

    /**
     * El identificador único del usuario.
     * Se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * El nombre de usuario.
     */
    private String username;

    /**
     * La contraseña del usuario.
     */
    private String password;

    /**
     * El correo electrónico del usuario.
     */
    private String email;

    /**
     * El rol del usuario (por ejemplo, "Admin", "Usuario").
     */
    private String rol;

    /**
     * El identificador del código QR asociado al usuario.
     */
    private long qr_id;

    /**
     * Constructor por defecto.
     */
    public User() {
    }

    /**
     * Asigna un objeto Qr al usuario y actualiza el identificador del código QR.
     * @param qr el objeto Qr a asociar con el usuario.
     */
    public void setQr(Qr qr) {
        this.qr = qr;
        qr_id=qr.getId();
    }

    /**
     * Constructor con parámetros para inicializar todos los atributos del usuario.
     * @param id       el identificador del usuario.
     * @param username el nombre de usuario.
     * @param password la contraseña del usuario.
     * @param email    el correo electrónico del usuario.
     * @param rol      el rol del usuario.
     * @param qr_id    el identificador del código QR asociado al usuario.
     */
    public User(long id, String username, String password, String email, String rol, long qr_id) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.rol = rol;
        this.qr_id = qr_id;
    }
}
