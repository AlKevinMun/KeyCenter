package com.proyect.keycenter.service;

import com.proyect.keycenter.entities.Qr;
import com.proyect.keycenter.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * QrService es un servicio que maneja la lógica de negocio relacionada con los códigos QR.
 * Utiliza QrDAO para interactuar con la base de datos.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Service
public class QrService {
    /**
     * Interactua con la base de datos de QR.
     */
    @Autowired
    QrDAO qrDAO;

    /**
     * Recupera todos los códigos QR almacenados en la base de datos.
     * @return una lista de todos los códigos QR.
     */
    public List<Qr> readAllQrs() {
        return qrDAO.findAll();
    }

    /**
     * Recupera un código QR por su ID.
     * @param id el identificador del código QR.
     * @return el código QR correspondiente al ID proporcionado, o null si no se encuentra.
     */
    public Qr getQrById(Integer id) {
        return qrDAO.findById(id).orElse(null);
    }

    /**
     * Añade un nuevo código QR al sistema.
     * @param qr el objeto Qr a añadir.
     * @return el Qr añadido.
     */
    @Transactional
    public Qr addQr(Qr qr) {
        return qrDAO.save(qr);
    }

    /**
     * Elimina un código QR por su ID.
     * @param id el identificador del código QR a eliminar.
     */
    public void deleteQr(Integer id){
        qrDAO.deleteById(id);
    }
}
