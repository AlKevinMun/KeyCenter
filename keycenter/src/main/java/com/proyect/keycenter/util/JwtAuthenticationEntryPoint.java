package com.proyect.keycenter.util;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.PrintWriter;

/**
 * Implementación personalizada de {@link AuthenticationEntryPoint} para manejar errores de autenticación en una aplicación Spring Boot.
 * En caso de fallo en la autenticación, este componente redirige al cliente a una página o muestra un mensaje indicando que el acceso está denegado.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Maneja la situación cuando un cliente intenta acceder a un recurso protegido sin estar autenticado.
     * En este caso, se establece el estado HTTP a UNAUTHORIZED (401) y se envía un mensaje de error al cliente.
     * @param request La solicitud HTTP que causó la excepción de autenticación.
     * @param response La respuesta HTTP que será enviada al cliente.
     * @param authException La excepción de autenticación que ocurrió.
     * @throws IOException Si ocurre un error al escribir en la respuesta.
     * @throws ServletException Si ocurre un error al procesar la solicitud.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException)
            throws IOException, ServletException {

        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        PrintWriter writer = response.getWriter();
        writer.println("Access Denied !! " + authException.getMessage());
    }
}