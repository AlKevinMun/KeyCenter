package com.proyect.keycenter.service;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dao.*;
import com.proyect.keycenter.entities.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    UserDAO userDAO;
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Buscar a la bd l'usuari i el password
        User user = userDAO.findByEmail(email);
        System.out.println(user);
        //MyUserDetails myUserDetails = new MyUserDetails(user);
        //return myUserDetails;
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }

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
