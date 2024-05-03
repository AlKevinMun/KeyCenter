package com.proyect.keycenter.controller;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dto.UserDto;
import com.proyect.keycenter.service.*;
import com.proyect.keycenter.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
@Controller
public class UserController {

    @Autowired
    UserService userService;
    public List<UserDto> readAll() {
        List<UserDto> userDtos;
        userDtos = userService.readAllUsers().stream().map(UserDto::new).toList();
        return userDtos;
    }

    public UserDto getUserById(Integer id) {
        return new UserDto(userService.getUserById(id));
    }

    public UserDto addUser(User user) throws WriterException {
        if (user.getRol() == null){
            user.setRol("Usuario");
        }
        return new UserDto(userService.addUser(user));
    }

    public void deleteUser(Integer id){
        userService.deleteUser(id);
    }

    /*public User updateUser(Integer id, User user) {
        User user1 = userService.getUserById(id);
        user1.setId(user.getId());
        user1.setFullName(user.getFullName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        return userService.updateUser(user1);
    }

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
