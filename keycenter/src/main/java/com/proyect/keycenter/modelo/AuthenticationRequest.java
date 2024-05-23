package com.proyect.keycenter.modelo;

/**
 * Modelo de solicitud de autenticación utilizado en una aplicación Spring Boot.
 * Representa una solicitud de inicio de sesión con un correo electrónico y contraseña.
 *
 * @author Alejandro Parrilla Ruiz
 */
public class AuthenticationRequest {
    /**
     * Correo electrónico del usuario.
     */
    private String email;

    /**
     * Contraseña del usuario.
     */
    private String password;


    /**
     * Constructor vacío para la serialización/deserialización JSON.
     */
    public AuthenticationRequest() {
    }

    /**
     * Constructor que inicializa el correo electrónico y la contraseña.
     * @param email Correo electrónico del usuario.
     * @param password Contraseña del usuario.
     */
    public AuthenticationRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    /**
     * Obtiene el correo electrónico del usuario.
     * @return El correo electrónico del usuario.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Establece el correo electrónico del usuario.
     * @param email El correo electrónico del usuario.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Obtiene la contraseña del usuario.
     * @return La contraseña del usuario.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Establece la contraseña del usuario.
     * @param password La contraseña del usuario.
     */
    public void setPassword(String password) {
        this.password = password;
    }
}