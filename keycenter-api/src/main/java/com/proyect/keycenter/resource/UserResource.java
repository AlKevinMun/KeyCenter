package com.proyect.keycenter.resource;

import com.google.zxing.WriterException;
import com.proyect.keycenter.controller.*;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Qr;
import com.proyect.keycenter.entities.User;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * UserResource es un controlador REST que maneja las operaciones relacionadas con los usuarios.
 * Proporciona endpoints para gestionar usuarios en el sistema.
 *
 * @author Alejandro Parrilla Ruiz
 */
@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    /**
     * La ruta base para todas las operaciones de usuario.
     */
    public static final String USERS = "/api/users";

    /**
     * Controlador que maneja la lógica relacionada con los usuarios.
     */
    @Autowired
    UserController userController;

    /**
     * Obtiene una lista de todos los usuarios.
     * @return una ResponseEntity con la lista de UserDto y el estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return new ResponseEntity<>(userController.readAll(), HttpStatus.OK);
    }

    /**
     * Obtiene un usuario por su ID.
     * @param id el identificador del usuario.
     * @return una ResponseEntity con el UserDto y el estado HTTP OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDto> user(@PathVariable Integer id){
        return new ResponseEntity<>(userController.getUserById(id), HttpStatus.OK);
    }

    /**
     * Obtiene un usuario por su correo electrónico.
     * @param email el correo electrónico del usuario.
     * @return una ResponseEntity con el UserDto y el estado HTTP OK.
     */
    @GetMapping("/email/{email}")
    public ResponseEntity<UserDto> user(@PathVariable String email){
        return new ResponseEntity<>(userController.getUserByEmail(email), HttpStatus.OK);
    }

    /**
     * Crea un nuevo usuario.
     * @param user el objeto User a crear.
     * @return una ResponseEntity con el UserDto creado y el estado HTTP OK.
     * @throws WriterException si hay un error al generar el código QR.
     */
    @PostMapping
    public ResponseEntity<UserDto> newUser(@RequestBody User user) throws WriterException {
        return ResponseEntity.ok(userController.addUser(user));
    }

    /**
     * Elimina un usuario por su ID.
     * @param id el identificador del usuario a eliminar.
     * @return una ResponseEntity con el estado HTTP OK.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userController.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Actualiza un usuario existente.
     * @param id el identificador del usuario a actualizar.
     * @param user el objeto User con los nuevos datos.
     * @return una ResponseEntity con el UserDto actualizado y el estado HTTP OK.
     */
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody User user){
        return ResponseEntity.ok(new UserDto(userController.updateUser(id, user)));
    }

    /**
     * Crea un usuario administrador automáticamente después de la construcción del bean.
     * @throws WriterException si hay un error al generar el código QR.
     */
    @PostConstruct
    public void addAdmin() throws WriterException {
        userController.autoAdmin();
    }
}

