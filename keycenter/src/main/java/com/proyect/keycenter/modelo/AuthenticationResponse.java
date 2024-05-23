package com.proyect.keycenter.modelo;

/**
 * Modelo de respuesta de autenticación utilizado en una aplicación Spring Boot.
 * Representa la respuesta después de un éxito en la autenticación, conteniendo un token JWT.
 *
 * @author Alejandro Parrilla Ruiz
 */
public class AuthenticationResponse {
    /**
     * Token JWT generado tras un proceso de autenticación exitoso.
     */
    private final String jwt;

    /**
     * Constructor que inicializa el token JWT.
     * @param jwt El token JWT generado.
     */
    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    /**
     * Obtiene el token JWT.
     * @return El token JWT.
     */
    public String getJwt() {
        return jwt;
    }
}