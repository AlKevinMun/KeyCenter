package com.proyect.keycenter.service;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dao.*;
import com.proyect.keycenter.modelo.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;
    public List<User> readAllUsers() {
        return userDAO.findAll();
    }

    public User getUserById(Integer id) {
        return userDAO.findById(id).orElse(null);
    }

    @Transactional
    public User addUser(User user) throws WriterException {
        return userDAO.save(user);
    }

    public void deleteUser(Integer id){
        userDAO.deleteById(id);
    }

    /*public User updateUser(User user) {
        return userDAO.save(user);
    }

    public User modify(User user) {
        return userDAO.save(user);
    }*/
}
