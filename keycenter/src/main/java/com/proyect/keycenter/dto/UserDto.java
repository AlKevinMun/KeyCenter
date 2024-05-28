package com.proyect.keycenter.dto;

import com.proyect.keycenter.entities.User;
import lombok.Data;

/**
 * La clase UserDto es un Data Transfer Object (DTO) para la entidad User.
 * Se utiliza para transferir datos de usuario sin exponer la entidad completa.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Data
public class UserDto {
    /**
     * El identificador único del usuario.
     */
    private long id;

    /**
     * El nombre de usuario.
     */
    private String username;

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
    public UserDto() {
    }

    /**
     * Constructor que inicializa un UserDto a partir de una entidad User.
     * @param user la entidad User de la cual se copian los datos.
     */
    public UserDto(User user){
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        rol = user.getRol();
        qr_id = user.getQr_id();
    }
}