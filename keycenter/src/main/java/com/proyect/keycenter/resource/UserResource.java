package com.proyect.keycenter.resource;

import com.google.zxing.WriterException;
import com.proyect.keycenter.controller.*;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Qr;
import com.proyect.keycenter.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(UserResource.USERS)
public class UserResource {
    public static final String USERS = "/api/users";

    @Autowired
    UserController userController;
    @Autowired
    QrController qrController ;

    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(){
        return new ResponseEntity<>(userController.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> user(@PathVariable Integer id){
        return new ResponseEntity<>(userController.getUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserDto> newUser(@RequestBody User user) throws WriterException {
        Qr.setIdMas(qrController.readAll().size());
        User.setIdMas(userController.readAll().size());
        Qr qr = new Qr(user);
        user.setQr(qr);
        user.setId(User.getIdMas());
        qrController.addQr(qr);
        return ResponseEntity.ok(userController.addUser(user));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Integer id){
        userController.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /*@PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable Integer id, @RequestBody User user){
        return ResponseEntity.ok(new UserDto(userController.updateUser(id, user)));
    }

    @PatchMapping("{id}")
    public ResponseEntity<UserDto> modify(@PathVariable Integer id, @RequestBody JsonPatch patch) throws JsonPatchException, JsonProcessingException {
        return ResponseEntity.ok(userController.modify(id, patch));
    }*/
}

