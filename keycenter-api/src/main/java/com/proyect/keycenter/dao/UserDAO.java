package com.proyect.keycenter.dao;

import com.proyect.keycenter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * La interfaz UserDAO extiende JpaRepository para proporcionar métodos CRUD para la entidad User.
 * Se utiliza para interactuar con la base de datos de usuarios.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    /**
     * Recupera todos los usuarios de la base de datos.
     * @return una lista de todos los usuarios.
     */
    List<User> findAll();

    /**
     * Recupera un usuario por su identificador.
     * @param id el identificador del usuario.
     * @return un Optional que contiene el usuario si se encuentra, o vacío si no.
     */
    Optional<User> findById(Integer id);

    /**
     * Recupera un usuario por su correo electrónico.
     * @param email el correo electrónico del usuario.
     * @return el usuario con el correo electrónico dado.
     */
    User findByEmail(String email);
}
