package com.proyect.keycenter.controller;

import com.google.zxing.WriterException;
import com.proyect.keycenter.dto.*;
import com.proyect.keycenter.modelo.*;
import com.proyect.keycenter.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class LlaveController {
    @Autowired
    LlaveService llaveService;
    public List<LlaveDto> readAll() {
        List<LlaveDto> llaveDtos;
        llaveDtos = llaveService.readAllLlaves().stream().map(LlaveDto::new).toList();
        return llaveDtos;
    }

    public LlaveDto getLlaveById(Integer id) {
        return new LlaveDto(llaveService.getLlaveById(id));
    }

    public LlaveDto addLlave(Llave llave) throws WriterException {
        llave.setUser_id(1);
        return new LlaveDto(llaveService.addLlave(llave));
    }

    public void deleteLlave(Integer id){
        llaveService.deleteLlave(id);
    }
}
