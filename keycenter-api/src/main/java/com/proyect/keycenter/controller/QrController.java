package com.proyect.keycenter.controller;

import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Qr;
import com.proyect.keycenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

/**
 * QrController es un controlador que maneja la lógica de negocio relacionada con los códigos QR.
 * Utiliza QrService para interactuar con la capa de persistencia.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Controller
public class QrController {
    /**
     * Servicio que maneja la lógica relacionada con los QR.
     */
    @Autowired
    QrService qrService;

    /**
     * Recupera todos los códigos QR y los convierte en objetos QrDto.
     * @return una lista de QrDto que representa todos los códigos QR.
     */
    public List<QrDto> readAll() {
        List<QrDto> qrDtos;
        qrDtos = qrService.readAllQrs().stream().map(QrDto::new).toList();
        return qrDtos;
    }

    /**
     * Recupera un código QR por su ID y lo convierte en un objeto QrDto.
     * @param id el identificador del código QR.
     * @return el QrDto correspondiente al ID proporcionado.
     */
    public QrDto getQrById(Integer id) {
        return new QrDto(qrService.getQrById(id));
    }

    /**
     * Añade un nuevo código QR al sistema.
     * @param qr el objeto Qr a añadir.
     * @return el Qr añadido.
     */
    public Qr addQr(Qr qr) {
        return qrService.addQr(qr);
    }

    /**
     * Elimina un código QR por su ID.
     * @param id el identificador del código QR a eliminar.
     */
    public void deleteQr(Integer id){
        qrService.deleteQr(id);
    }
}
