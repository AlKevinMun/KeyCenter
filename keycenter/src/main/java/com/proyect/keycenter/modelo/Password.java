package com.proyect.keycenter.modelo;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Password {

    public static String encryptPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean validatePassword(String inputPassword, String storedPassword) {
        String hashedInputPassword = encryptPassword(inputPassword);
        return hashedInputPassword.equals(storedPassword);
    }
}
