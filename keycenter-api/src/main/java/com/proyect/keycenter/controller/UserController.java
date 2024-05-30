package com.proyect.keycenter.controller;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dto.UserDto;
import com.proyect.keycenter.entities.Qr;
import com.proyect.keycenter.entities.User;
import com.proyect.keycenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * UserController es un controlador que maneja la lógica relacionada con los usuarios.
 * Interactúa con los servicios y controla las operaciones de usuario.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Controller
public class UserController {
    /**
     * Servicio que maneja la lógica relacionada con los usuarios.
     */
    @Autowired
    UserService userService;

    /**
     * Controlador que maneja la lógica relacionada con los QR.
     */
    @Autowired
    QrController qrController;

    /**
     * Encoder para codificar la contraseña del usuario.
     */
    @Autowired
    PasswordEncoder encoder;

    /**
     * Crea automáticamente un usuario administrador con un código QR asociado.
     * @throws WriterException si hay un error al generar el código QR.
     */
    public void autoAdmin() throws WriterException {
        User user = new User(1, "Admin", "$2a$10$j.xlMK99YuKbgwC4l/MFPuOGti9.cAU9HJi6LBO1jSrf72lBNexb2", "admin@elpuig.xeill.net", "Admin", 1);
        Qr qr=new Qr(user);
        qr.setId(1);
        qrController.addQr(qr);
        userService.addUser(user);
    }

    /**
     * Recupera una lista de todos los usuarios en forma de DTOs.
     * @return una lista de UserDto.
     */
    public List<UserDto> readAll() {
        List<UserDto> userDtos;
        userDtos = userService.readAllUsers().stream().map(UserDto::new).toList();
        return userDtos;
    }

    /**
     * Recupera un usuario por su ID.
     * @param id el identificador del usuario.
     * @return un UserDto con los datos del usuario.
     */
    public UserDto getUserById(Integer id) {
        return new UserDto(userService.getUserById(id));
    }

    /**
     * Recupera un usuario por su correo electrónico.
     * @param email el correo electrónico del usuario.
     * @return un UserDto con los datos del usuario.
     */
    public UserDto getUserByEmail(String email) {
        return new UserDto(userService.getUserByEmail(email));
    }

    /**
     * Añade un nuevo usuario al sistema.
     * @param user el objeto User a añadir.
     * @return un UserDto con los datos del usuario añadido.
     * @throws WriterException si hay un error al generar el código QR.
     */
    public UserDto addUser(User user) throws WriterException {
        Qr qr = new Qr(user);
        qr.setId(qrController.readAll().size()+1);
        user.setQr(qr);
        qrController.addQr(qr);
        if (user.getRol() == null){
            user.setRol("Usuario");
        }
        String pass = encoder.encode(user.getPassword());
        user.setPassword(pass);
        return new UserDto(userService.addUser(user));
    }

    /**
     * Elimina un usuario por su ID.
     * @param id el identificador del usuario a eliminar.
     */
    public void deleteUser(Integer id){
        userService.deleteUser(id);
    }

    /**
     * Actualiza los datos de un usuario existente.
     * @param id el identificador del usuario a actualizar.
     * @param user el objeto User con los nuevos datos.
     * @return el usuario actualizado.
     */
    public User updateUser(Integer id, User user) {
        User user1 = userService.getUserById(id);
        if (user.getPassword() != null){
            if (!user.getPassword().isBlank() || !user.getPassword().isEmpty()) {
                String pass = encoder.encode(user.getPassword());
                user.setPassword(pass);
                user1.setPassword(user.getPassword());
            }
        }
        if(user.getEmail() != null){
            if(!user.getEmail().isBlank() || !user.getEmail().isEmpty()) user1.setEmail(user.getEmail());
        }
        if(user.getRol() != null){
            if (!user.getRol().isBlank() || !user.getRol().isEmpty()) user1.setRol(user.getRol());
        }
        if(user.getUsername() != null){
            if (!user.getUsername().isBlank() || !user.getUsername().isEmpty()) user1.setUsername(user.getUsername());
        }
        return userService.updateUser(user1);
    }
}
