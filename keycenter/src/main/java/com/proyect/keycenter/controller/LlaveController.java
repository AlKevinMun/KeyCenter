package com.proyect.keycenter.controller;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Llave;
import com.proyect.keycenter.entities.Qr;
import com.proyect.keycenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * LlaveController es un controlador que maneja la lógica relacionada con las llaves.
 * Interactúa con los servicios y controla las operaciones de llave.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Controller
public class LlaveController {
    /**
     * Servicio que maneja la lógica relacionada con las llaves.
     */
    @Autowired
    LlaveService llaveService;

    /**
     * Controlador que maneja la lógica relacionada con los QR.
     */
    @Autowired
    QrController qrController;

    /**
     * Recupera una lista de todos las llaves en forma de DTOs.
     * @return una lista de LlaveDto.
     */
    public List<LlaveDto> readAll() {
        List<LlaveDto> llaveDtos;
        llaveDtos = llaveService.readAllLlaves().stream().map(LlaveDto::new).toList();
        return llaveDtos;
    }

    /**
     * Recupera una llaves por su ID.
     * @param id el identificador de la llave.
     * @return un LlaveDto con los datos de la llave.
     */
    public LlaveDto getLlaveById(Integer id) {
        return new LlaveDto(llaveService.getLlaveById(id));
    }

    /**
     * Añade una nueva llave al sistema.
     * @param llave el objeto Llave a añadir.
     * @return un LlaveDto con los datos de la llave añadido.
     * @throws WriterException si hay un error al generar el código QR.
     */
    public LlaveDto addLlave(Llave llave) throws WriterException {
        Qr qr = new Qr(llave);
        qr.setId(qrController.readAll().size()+1);
        Llave llave1 = llave;
        if(llave.getUser_id() == 0) llave1.setUser_id(1);
        else llave1.setUser_id(llave.getUser_id());
        llave1.setQr(qr);
        llave1.setHora(new Timestamp(new Date().getTime()));
        qrController.addQr(llave1.getQr());
        return new LlaveDto(llaveService.addLlave(llave1));
    }

    /**
     * Elimina una llave por su ID.
     * @param id el identificador de la llave a eliminar.
     */
    public void deleteLlave(Integer id){
        llaveService.deleteLlave(id);
    }

    /**
     * Actualiza los datos de una llave existente.
     * @param id el identificador de la llave a actualizar.
     * @param llave el objeto Llave con los nuevos datos.
     * @return la llave actualizada.
     */
    public Llave updateLlave(Integer id, Llave llave) {
        Llave llave1 = llaveService.getLlaveById(id);
        llave1.setUser_id(llave.getUser_id());
        return llaveService.updateLlave(llave1);
    }
}
