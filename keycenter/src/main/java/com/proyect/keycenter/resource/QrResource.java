package com.proyect.keycenter.resource;


import com.proyect.keycenter.controller.QrController;
import com.proyect.keycenter.dto.QrDto;
import com.proyect.keycenter.modelo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * QrResource es un controlador REST que maneja las solicitudes HTTP relacionadas con los códigos QR.
 *
 * @author Alejandro Parrilla Ruiz
 */
@RestController
@RequestMapping(QrResource.QRS)
public class QrResource {
    /**
     * La ruta base para las solicitudes relacionadas con los códigos QR.
     */
    public static final String QRS = "/api/qr";

    /**
     * Controlador que maneja la lógica relacionada con los QR.
     */
    @Autowired
    QrController qrController;

    /**
     * Maneja las solicitudes GET para recuperar todos los códigos QR.
     * @return una lista de QrDto que representa todos los códigos QR.
     */
    @GetMapping
    public ResponseEntity<List<QrDto>> getQrs(){
        return new ResponseEntity<>(qrController.readAll(), HttpStatus.OK);
    }

    /**
     * Maneja las solicitudes GET para recuperar un código QR por su ID.
     * @param id el identificador del código QR.
     * @return el QrDto correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<QrDto> qr(@PathVariable Integer id){
        return new ResponseEntity<>(qrController.getQrById(id), HttpStatus.OK);
    }

    /**
     * Maneja las solicitudes DELETE para eliminar un código QR por su ID.
     * @param id el identificador del código QR a eliminar.
     * @return una respuesta HTTP con el estado OK si la eliminación fue exitosa.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteQr(@PathVariable Integer id){
        qrController.deleteQr(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
