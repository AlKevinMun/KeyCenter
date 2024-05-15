package com.proyect.keycenter.dao;

import com.proyect.keycenter.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
    List<User> findAll();
    Optional<User> findById(Integer id);
    User findByEmail(String email);
}
