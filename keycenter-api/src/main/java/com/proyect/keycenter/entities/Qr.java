package com.proyect.keycenter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.*;
import java.util.*;
import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;

/**
 * La clase Qr representa un código QR asociado a un objeto en el sistema, en este caso a usuarios y llaves.
 * Se utiliza para generar códigos QR a partir de datos de objetos y almacenarlos en la base de datos.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Data
@Entity
@Table(name = "qr")
public class Qr {
    /**
     * El identificador único del código QR.
     */
    @Id
    private long id;

    /**
     * Los datos binarios del código QR.
     */
    private byte[] data;

    /**
     * Constructor por defecto.
     */
    public Qr() {
    }

    /**
     * Constructor que genera un código QR a partir de un objeto.
     * @param o el objeto del cual se generará el código QR.
     * @throws WriterException si hay un error al generar el código QR.
     */
    public Qr(Object o) throws WriterException {
        data=transformarDatos(o);
    }

    /**
     * Transforma los datos de un objeto en un código QR.
     * @param o el objeto del cual se generarán los datos del código QR.
     * @return un array de bytes que representa los datos del código QR.
     * @throws WriterException si hay un error al generar el código QR.
     */
    private byte[] transformarDatos(Object o) throws WriterException {
        int width = 300;
        int height = 300;

        String data = classToString(o);

        Map<EncodeHintType, Object> hints = new HashMap<>();
        hints.put(EncodeHintType.CHARACTER_SET, "UTF-8");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(data, BarcodeFormat.QR_CODE, width, height, hints);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        try {
            MatrixToImageWriter.writeToStream(bitMatrix, "PNG", outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        byte[] byteArray = outputStream.toByteArray();
        return byteArray;
    }

    /**
     * Convierte los datos de un objeto en una cadena de texto para generar el código QR.
     * @param o el objeto del cual se generarán los datos del código QR.
     * @return una cadena de texto que representa los datos del código QR.
     */
    private String classToString(Object o) {
        String data;
        if (o instanceof User){
            User u = (User) o;
            data="user: "+u.getEmail();
        }else {
            Llave l = (Llave) o;
            data="llave: "+l.getId();
        }
        return data;
    }
}
