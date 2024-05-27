package com.proyect.keycenter.controller;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Incidence;
import com.proyect.keycenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class IncidenceController {
    @Autowired
    IncidenceService incidenceService;
    public List<IncidenceDto> readAll() {
        List<IncidenceDto> incidenceDtos;
        incidenceDtos = incidenceService.readAllIncidence().stream().map(IncidenceDto::new).toList();
        return incidenceDtos;
    }

    public IncidenceDto getIncidenceById(Integer id) {
        return new IncidenceDto(incidenceService.getIncidenceById(id));
    }

    public IncidenceDto addIncidence(Incidence incidence) throws WriterException {
        Incidence incidence1 = new Incidence(incidence.getTopic(), incidence.getDescription(), incidence.getUser_id());
        return new IncidenceDto(incidenceService.addIncidence(incidence));
    }

    public void deleteIncidence(Integer id){
        incidenceService.deleteIncidence(id);
    }
}
