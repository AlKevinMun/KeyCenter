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


/**
 * IncidenceResource es un controlador REST que maneja las solicitudes relacionadas con las incidencias.
 *
 * @author Alejandro Parrilla Ruiz
 */
@RestController
@RequestMapping(IncidenceResource.INCIDENCES)
public class IncidenceResource {
    /**
     * La ruta base para todas las operaciones de llaves.
     */
    public static final String INCIDENCES = "/api/incidences";

    /**
     * Controlador que maneja la lógica relacionada con las incidencias.
     */
    @Autowired
    IncidenceController incidenceController;

    /**
     * Obtiene todas las incidencias y devuelve una respuesta HTTP con una lista de objetos IncidenceDto.
     * @return una respuesta HTTP con una lista de incidencias.
     */
    @GetMapping
    public ResponseEntity<List<IncidenceDto>> getIncidence(){
        return new ResponseEntity<>(incidenceController.readAll(), HttpStatus.OK);
    }

    /**
     * Obtiene una incidencia por su ID y devuelve una respuesta HTTP con un objeto IncidenceDto.
     * @param id el ID de la incidencia.
     * @return una respuesta HTTP con la incidencia solicitada.
     */
    @GetMapping("/{id}")
    public ResponseEntity<IncidenceDto> incidence(@PathVariable Integer id){
        return new ResponseEntity<>(incidenceController.getIncidenceById(id), HttpStatus.OK);
    }

    /**
     * Crea una nueva incidencia y devuelve una respuesta HTTP con el objeto IncidenceDto de la incidencia creada.
     * @param incidence la incidencia a crear.
     * @return una respuesta HTTP con la incidencia creada.
     * @throws WriterException si hay un error al generar el código QR.
     */
    @PostMapping
    public ResponseEntity<IncidenceDto> newIncidence(@RequestBody Incidence incidence) throws WriterException {
        return ResponseEntity.ok(incidenceController.addIncidence(incidence));
    }

    /**
     * Elimina una incidencia por su ID y devuelve una respuesta HTTP con el estado de la operación.
     * @param id el ID de la incidencia a eliminar.
     * @return una respuesta HTTP con el estado de la operación.
     */
    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteIncidence(@PathVariable Integer id){
        incidenceController.deleteIncidence(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * Actualiza una incidencia y devuelve una respuesta HTTP con el objeto IncidenceDto actualizado.
     * @param id el ID de la incidencia a actualizar.
     * @param incidence la incidencia con los cambios a aplicar.
     * @return una respuesta HTTP con la incidencia actualizada.
     */
    @PutMapping("{id}")
    public ResponseEntity<IncidenceDto> updateIncidence(@PathVariable Integer id, @RequestBody Incidence incidence){
        return ResponseEntity.ok(new IncidenceDto(incidenceController.updateIncidence(id , incidence)));
    }
}
