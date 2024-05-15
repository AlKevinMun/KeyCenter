package com.proyect.keycenter.controller;

import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.entities.Qr;
import com.proyect.keycenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class QrController {

    @Autowired
    QrService qrService;
    public List<QrDto> readAll() {
        List<QrDto> qrDtos;
        qrDtos = qrService.readAllQrs().stream().map(QrDto::new).toList();
        return qrDtos;
    }

    public QrDto getQrById(Integer id) {
        return new QrDto(qrService.getQrById(id));
    }

    public Qr addQr(Qr qr) {
        return qrService.addQr(qr);
    }

    public void deleteQr(Integer id){
        qrService.deleteQr(id);
    }
}
