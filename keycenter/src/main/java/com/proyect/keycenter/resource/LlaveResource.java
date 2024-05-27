package com.proyect.keycenter.resource;

import com.google.zxing.WriterException;
import com.proyect.keycenter.controller.*;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Incidence;
import com.proyect.keycenter.entities.Llave;
import com.proyect.keycenter.entities.Qr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.*;

@RestController
@RequestMapping(LlaveResource.LLAVES)
public class LlaveResource {
    public static final String LLAVES = "/api/llaves";

    @Autowired
    LlaveController llaveController;

    @GetMapping
    public ResponseEntity<List<LlaveDto>> getLlave(){
        return new ResponseEntity<>(llaveController.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LlaveDto> llave(@PathVariable Integer id){
        return new ResponseEntity<>(llaveController.getLlaveById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LlaveDto> newLlave(@RequestBody Llave llave) throws WriterException {
        return ResponseEntity.ok(llaveController.addLlave(llave));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteLlave(@PathVariable Integer id){
        llaveController.deleteLlave(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<LlaveDto> updateLlave(@PathVariable Integer id, @RequestBody Llave llave){
        return ResponseEntity.ok(new LlaveDto(llaveController.updateLlave(id , llave)));
    }
}
