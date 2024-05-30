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

/**
 * LlaveResource es un controlador REST que maneja las solicitudes HTTP relacionadas con las llaves.
 *
 * @author Alejandro Parrilla Ruiz
 */
@RestController
@RequestMapping(LlaveResource.LLAVES)
public class LlaveResource {
    /**
     * La ruta base para todas las operaciones de llaves.
     */
    public static final String LLAVES = "/api/llaves";

    /**
     * Controlador que maneja la lógica relacionada con las llaves.
     */
    @Autowired
    LlaveController llaveController;

    /**
     * Obtiene una lista de todos las llaves.
     * @return una ResponseEntity con la lista de LlaveDto y el estado HTTP OK.
     */
    @GetMapping
    public ResponseEntity<List<LlaveDto>> getLlave(){
        return new ResponseEntity<>(llaveController.readAll(), HttpStatus.OK);
    }

    /**
     * Obtiene una llave por su ID.
     * @param id el identificador de la llave.
     * @return una ResponseEntity con el LlaveDto y el estado HTTP OK.
     */
    @GetMapping("/{id}")
    public ResponseEntity<LlaveDto> llave(@PathVariable Integer id){
        return new ResponseEntity<>(llaveController.getLlaveById(id), HttpStatus.OK);
    }

    /**
     * Crea una nueva llave.
     * @param llave el objeto Llave a crear.
     * @return una ResponseEntity con el LlaveDto creado y el estado HTTP OK.
     * @throws WriterException si hay un error al generar el código QR.
     */
    @PostMapping
    public ResponseEntity<LlaveDto> newLlave(@RequestBody Llave llave) throws WriterException {
        return ResponseEntity.ok(llaveController.addLlave(llave));
    }

    /**
     * Elimina una llave por su ID.
     * @param id el identificador de la llave a eliminar.
     * @return una ResponseEntity con el estado HTTP OK.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteLlave(@PathVariable Integer id){
        llaveController.deleteLlave(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Actualiza una llave existente.
     * @param id el identificador de la llavee a actualizar.
     * @param llave el objeto Llave con los nuevos datos.
     * @return una ResponseEntity con el LlaveDto actualizado y el estado HTTP OK.
     */
    @PutMapping("{id}")
    public ResponseEntity<LlaveDto> updateLlave(@PathVariable Integer id, @RequestBody Llave llave){
        return ResponseEntity.ok(new LlaveDto(llaveController.updateLlave(id , llave)));
    }
}
