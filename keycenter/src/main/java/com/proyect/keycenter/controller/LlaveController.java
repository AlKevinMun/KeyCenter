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

@Controller
public class LlaveController {
    @Autowired
    LlaveService llaveService;

    @Autowired
    QrController qrController;

    public List<LlaveDto> readAll() {
        List<LlaveDto> llaveDtos;
        llaveDtos = llaveService.readAllLlaves().stream().map(LlaveDto::new).toList();
        return llaveDtos;
    }

    public LlaveDto getLlaveById(Integer id) {
        return new LlaveDto(llaveService.getLlaveById(id));
    }

    public LlaveDto addLlave(Llave llave) throws WriterException {
        Qr.setIdMas(qrController.readAll().size());
        Qr qr = new Qr(llave);
        Llave llave1 = llave;
        llave1.setUser_id(new long[llave1.getCantidad()]);
        llave1.setQr(qr);
        llave1.setHora(new Timestamp(new Date().getTime()));
        qrController.addQr(llave1.getQr());
        return new LlaveDto(llaveService.addLlave(llave1));
    }

    public void deleteLlave(Integer id){
        llaveService.deleteLlave(id);
    }
}
