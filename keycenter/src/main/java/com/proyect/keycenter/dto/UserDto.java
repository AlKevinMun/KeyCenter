package com.proyect.keycenter.dto;

import com.proyect.keycenter.modelo.*;
import lombok.Data;

@Data
public class UserDto {
    private long id;
    private String username;
    private String email;
    private String rol;
    private byte[] profile_picture;
    private long qr_id;

    public UserDto() {
    }


    public UserDto(User user){
        id = user.getId();
        username = user.getUsername();
        email = user.getEmail();
        rol = user.getRol();
        profile_picture = user.getProfile_picture();
        qr_id = user.getQr_id();
    }
}