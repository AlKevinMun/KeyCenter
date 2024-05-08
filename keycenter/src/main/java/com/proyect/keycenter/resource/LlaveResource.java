package com.proyect.keycenter.resource;

import com.google.zxing.WriterException;
import com.proyect.keycenter.controller.*;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.modelo.*;
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
    @Autowired
    QrController qrController ;

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
        Qr.setIdMas(qrController.readAll().size());
        Qr qr = new Qr(llave);
        Llave llave1 = llave;
        llave1.setQr(qr);
        llave1.setHora(new Timestamp(new Date().getTime()));
        qrController.addQr(llave1.getQr());
        return ResponseEntity.ok(llaveController.addLlave(llave1));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteLlave(@PathVariable Integer id){
        llaveController.deleteLlave(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
