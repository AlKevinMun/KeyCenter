package com.proyect.keycenter.service;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dao.*;
import com.proyect.keycenter.entities.Incidence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class IncidenceService {
    @Autowired
    IncidenceDAO incidenceDAO;
    public List<Incidence> readAllIncidence() {
        return incidenceDAO.findAll();
    }

    public Incidence getIncidenceById(Integer id) {
        return incidenceDAO.findById(id).orElse(null);
    }

    @Transactional
    public Incidence addIncidence(Incidence incidence) throws WriterException {
        return incidenceDAO.save(incidence);
    }

    public void deleteIncidence(Integer id){
        incidenceDAO.deleteById(id);
    }

    /*public Incidence updateIncidence(Incidence incidence) {
        return incidenceDAO.save(incidence);
    }

    public User modify(Incidence incidence) {
        return incidenceDAO.save(incidence);
    }*/
}
