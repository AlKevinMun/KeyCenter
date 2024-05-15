package com.proyect.keycenter.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.io.*;
import java.util.*;
import com.google.zxing.*;
import com.google.zxing.common.*;
import com.google.zxing.client.j2se.MatrixToImageWriter;

@Data
@Entity
@Table(name = "qr")
public class Qr {
    @Transient
    static private long idMas;
    @Id
    private long id;
    private byte[] data;


    public Qr() {
    }

    public Qr(Object o) throws WriterException {
        idMas++;
        id=idMas;
        data=transformarDatos(o);
    }

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


    private String classToString(Object o) {
        String data;
        if (o instanceof User){
            User u = (User) o;
            data= IP.ipAddress+"/qrIdForUser?idUser="+u.getId()+"&idQrr="+id;
        }else {
            Llave l = (Llave) o;
            data=IP.ipAddress+"/qrIdForKey?idKey="+l.getId()+"&idQrr="+id;
        }
        return data;
    }

    public static void setIdMas(long idMas) {
        Qr.idMas = idMas;
    }
}
