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
@Controller
public class UserController {

    @Autowired
    UserService userService;
    @Autowired
    QrController qrController;
    @Autowired
    PasswordEncoder encoder;

    public void autoAdmin() throws WriterException {
        User user = new User(1, "Admin", "$2a$10$j.xlMK99YuKbgwC4l/MFPuOGti9.cAU9HJi6LBO1jSrf72lBNexb2", "admin@elpuig.xeill.net", "Admin", null, 1);
        Qr qr=new Qr(user);
        qr.setId(1);
        qrController.addQr(qr);
        userService.addUser(user);
    }

    public List<UserDto> readAll() {
        List<UserDto> userDtos;
        userDtos = userService.readAllUsers().stream().map(UserDto::new).toList();
        return userDtos;
    }

    public UserDto getUserById(Integer id) {
        return new UserDto(userService.getUserById(id));
    }

    public UserDto getUserByEmail(String email) {
        return new UserDto(userService.getUserByEmail(email));
    }

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

    public void deleteUser(Integer id){
        userService.deleteUser(id);
    }

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
    /*

    public UserDto modify(Integer id, JsonPatch patch) throws JsonPatchException, JsonProcessingException {

        User user = userService.getUserById(id);
        User userPatched = applyPatchToUser(patch, user);
        userService.modify(userPatched);
        return new UserDto(userService.modify(userPatched));

    }

    private User applyPatchToUser(JsonPatch patch, User user) throws JsonPatchException, JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode patched = patch.apply(objectMapper.convertValue(user, JsonNode.class));
        return objectMapper.treeToValue(patched, User.class);
    }*/
}
