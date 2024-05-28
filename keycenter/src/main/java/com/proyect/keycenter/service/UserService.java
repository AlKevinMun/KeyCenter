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

/**
 * UserService es un servicio que maneja la lógica relacionada con los usuarios.
 * Implementa UserDetailsService para la autenticación de usuarios.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Service
public class UserService implements UserDetailsService {

    /**
     * Interactua con la base de datos de usuarios.
     */
    @Autowired
    UserDAO userDAO;

    /**
     * Carga un usuario por su nombre de usuario (en este caso, el correo electrónico).
     * @param email el correo electrónico del usuario.
     * @return los detalles del usuario para la autenticación.
     * @throws UsernameNotFoundException si el usuario no se encuentra.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //Buscar a la bd l'usuari i el password
        User user = userDAO.findByEmail(email);
        System.out.println(user);
        //MyUserDetails myUserDetails = new MyUserDetails(user);
        //return myUserDetails;
        return new org.springframework.security.core.userdetails.User(user.getEmail(),user.getPassword(),new ArrayList<>());
    }

    /**
     * Recupera una lista de todos los usuarios.
     * @return una lista de objetos User.
     */
    public List<User> readAllUsers() {
        return userDAO.findAll();
    }

    /**
     * Recupera un usuario por su ID.
     * @param id el identificador del usuario.
     * @return el usuario correspondiente, o null si no se encuentra.
     */
    public User getUserById(Integer id) {
        return userDAO.findById(id).orElse(null);
    }

    /**
     * Recupera un usuario por su correo electrónico.
     * @param email el correo electrónico del usuario.
     * @return el usuario correspondiente.
     */
    public User getUserByEmail(String email) {
        return userDAO.findByEmail(email);
    }

    /**
     * Añade un nuevo usuario al sistema.
     * @param user el objeto User a añadir.
     * @return el usuario añadido.
     * @throws WriterException si hay un error al generar el código QR.
     */
    @Transactional
    public User addUser(User user) throws WriterException {
        return userDAO.save(user);
    }

    /**
     * Elimina un usuario por su ID.
     * @param id el identificador del usuario a eliminar.
     */
    public void deleteUser(Integer id){
        userDAO.deleteById(id);
    }

    /**
     * Actualiza un usuario existente.
     * @param user el objeto User con los nuevos datos.
     * @return el usuario actualizado.
     */
    public User updateUser(User user) {
        return userDAO.save(user);
    }
    /*
    public User modify(User user) {
        return userDAO.save(user);
    }*/

}
