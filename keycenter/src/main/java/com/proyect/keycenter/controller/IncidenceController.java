package com.proyect.keycenter.controller;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Incidence;
import com.proyect.keycenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * IncidenceController es un controlador que maneja las solicitudes relacionadas con las incidencias.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Controller
public class IncidenceController {
    /**
     * Servicio que maneja la lógica relacionada con las incidencias.
     */
    @Autowired
    IncidenceService incidenceService;

    /**
     * Lee todas las incidencias y las convierte en una lista de objetos IncidenceDto.
     * @return una lista de IncidenceDto que representa todas las incidencias.
     */
    public List<IncidenceDto> readAll() {
        List<IncidenceDto> incidenceDtos;
        incidenceDtos = incidenceService.readAllIncidence().stream().map(IncidenceDto::new).toList();
        return incidenceDtos;
    }

    /**
     * Obtiene una incidencia por su ID y la convierte en un objeto IncidenceDto.
     * @param id el ID de la incidencia.
     * @return un objeto IncidenceDto que representa la incidencia con el ID especificado.
     */
    public IncidenceDto getIncidenceById(Integer id) {
        return new IncidenceDto(incidenceService.getIncidenceById(id));
    }

    /**
     * Agrega una nueva incidencia a la base de datos y devuelve su representación como un objeto IncidenceDto.
     * @param incidence la incidencia a agregar.
     * @return un objeto IncidenceDto que representa la incidencia agregada.
     * @throws WriterException si hay un error al generar el código QR.
     */
    public IncidenceDto addIncidence(Incidence incidence) throws WriterException {
        Incidence incidence1 = new Incidence(incidence.getTopic(), incidence.getDescription(), incidence.getUser_id());
        return new IncidenceDto(incidenceService.addIncidence(incidence1));
    }

    /**
     * Elimina una incidencia de la base de datos por su ID.
     * @param id el ID de la incidencia a eliminar.
     */
    public void deleteIncidence(Integer id){
        incidenceService.deleteIncidence(id);
    }

    /**
     * Actualiza una incidencia en la base de datos y devuelve su representación como un objeto Incidence.
     * @param id el ID de la incidencia a actualizar.
     * @param incidence la incidencia con los cambios a aplicar.
     * @return la incidencia actualizada.
     */
    public Incidence updateIncidence(Integer id, Incidence incidence) {
        Incidence incidence1 = incidenceService.getIncidenceById(id);
        incidence1.setState(incidence.getState());
        return incidenceService.updateIncidence(incidence1);
    }
}
