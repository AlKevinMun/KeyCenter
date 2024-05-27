package com.proyect.keycenter.resource;

import com.google.zxing.WriterException;
import com.proyect.keycenter.controller.*;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Incidence;
import com.proyect.keycenter.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(IncidenceResource.INCIDENCES)
public class IncidenceResource {
    public static final String INCIDENCES = "/api/incidences";
    @Autowired
    IncidenceController incidenceController;

    @GetMapping
    public ResponseEntity<List<IncidenceDto>> getIncidence(){
        return new ResponseEntity<>(incidenceController.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<IncidenceDto> incidence(@PathVariable Integer id){
        return new ResponseEntity<>(incidenceController.getIncidenceById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<IncidenceDto> newIncidence(@RequestBody Incidence incidence) throws WriterException {
        return ResponseEntity.ok(incidenceController.addIncidence(incidence));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteIncidence(@PathVariable Integer id){
        incidenceController.deleteIncidence(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<IncidenceDto> updateIncidence(@PathVariable Integer id, @RequestBody Incidence incidence){
        return ResponseEntity.ok(new IncidenceDto(incidenceController.updateIncidence(id , incidence)));
    }
}
