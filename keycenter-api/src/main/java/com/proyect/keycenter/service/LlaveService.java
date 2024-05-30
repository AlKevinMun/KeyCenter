package com.proyect.keycenter.service;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dao.*;
import com.proyect.keycenter.entities.Llave;
import com.proyect.keycenter.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * LlaveService es un servicio que maneja la lógica relacionada con las llaves.
 * Utiliza LlaveDAO para interactuar con la base de datos.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Service
public class LlaveService {
    /**
     * Interactua con la base de datos de llaves.
     */
    @Autowired
    LlaveDAO llaveDAO;

    /**
     * Recupera una lista de todos las llaves.
     * @return una lista de objetos Llave.
     */
    public List<Llave> readAllLlaves() {
        return llaveDAO.findAll();
    }

    /**
     * Recupera una llave por su ID.
     * @param id el identificador de la llave.
     * @return la llave correspondiente, o null si no se encuentra.
     */
    public Llave getLlaveById(Integer id) {
        return llaveDAO.findById(id).orElse(null);
    }

    /**
     * Añade una nueva llave al sistema.
     * @param llave el objeto Llave a añadir.
     * @return la llave añadida.
     */
    @Transactional
    public Llave addLlave(Llave llave){
        return llaveDAO.save(llave);
    }

    /**
     * Elimina una llave por su ID.
     * @param id el identificador de la llave a eliminar.
     */
    public void deleteLlave(Integer id){
        llaveDAO.deleteById(id);
    }

    /**
     * Actualiza una llave existente.
     * @param llave el objeto Llave con los nuevos datos.
     * @return la llave actualizada.
     */
    public Llave updateLlave(Llave llave) {
        return llaveDAO.save(llave);
    }
}
