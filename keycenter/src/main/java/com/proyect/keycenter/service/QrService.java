package com.proyect.keycenter.service;

import com.proyect.keycenter.entities.Qr;
import com.proyect.keycenter.dao.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class QrService {

    @Autowired
    QrDAO qrDAO;
    public List<Qr> readAllQrs() {
        return qrDAO.findAll();
    }

    public Qr getQrById(Integer id) {
        return qrDAO.findById(id).orElse(null);
    }

    @Transactional
    public Qr addQr(Qr qr) {
        return qrDAO.save(qr);
    }

    public void deleteQr(Integer id){
        qrDAO.deleteById(id);
    }
}
