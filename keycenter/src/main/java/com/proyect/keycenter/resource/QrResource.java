package com.proyect.keycenter.resource;


import com.proyect.keycenter.controller.QrController;
import com.proyect.keycenter.dto.QrDto;
import com.proyect.keycenter.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(QrResource.QRS)
public class QrResource {
    public static final String QRS = "/api/qr";

    @Autowired
    QrController qrController;

    @GetMapping
    public ResponseEntity<List<QrDto>> getQrs(){
        return new ResponseEntity<>(qrController.readAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<QrDto> qr(@PathVariable Integer id){
        return new ResponseEntity<>(qrController.getQrById(id), HttpStatus.OK);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQr(@PathVariable Integer id){
        qrController.deleteQr(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
