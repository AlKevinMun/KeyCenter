package com.proyect.keycenter.service;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dao.*;
import com.proyect.keycenter.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LlaveService {
    @Autowired
    LlaveDAO llaveDAO;
    public List<Llave> readAllLlaves() {
        return llaveDAO.findAll();
    }

    public Llave getLlaveById(Integer id) {
        return llaveDAO.findById(id).orElse(null);
    }

    @Transactional
    public Llave addLlave(Llave llave) throws WriterException {
        return llaveDAO.save(llave);
    }

    public void deleteLlave(Integer id){
        llaveDAO.deleteById(id);
    }
}
