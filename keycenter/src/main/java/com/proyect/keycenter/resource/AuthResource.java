package com.proyect.keycenter.resource;

import com.proyect.keycenter.modelo.AuthenticationRequest;
import com.proyect.keycenter.modelo.AuthenticationResponse;
import com.proyect.keycenter.util.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * AuthResource es un controlador REST para manejar solicitudes relacionadas con la autenticación de usuarios.
 *
 * @author Alejandro Parrilla Ruiz
 */
@RestController
public class AuthResource {
    /**
     * El AuthenticationManager para autenticar a los usuarios.
     */
    @Autowired
    private AuthenticationManager authenticationManager;

    /**
     * El UserDetailsService para cargar los detalles del usuario durante la autenticación.
     */
    @Autowired
    private UserDetailsService userDetailsService;

    /**
     * El JWTUtil para generar y validar tokens JWT.
     */
    @Autowired
    private JWTUtil jwtTokenUtil;

    /**
     * Retorna un saludo "Hello World!", es una comprobación del endpoint.
     * @return un String que contiene "Hello World!".
     */
    @GetMapping("/hello")
    public String hello() {
        return "Hello World!";
    }

    /**
     * Maneja solicitudes POST para autenticar a los usuarios y generar un token JWT.
     * @param authenticationRequest el objeto de solicitud de autenticación que contiene el email y la contraseña del usuario.
     * @return una ResponseEntity que contiene un objeto AuthenticationResponse con el token JWT generado.
     * @throws Exception si las credenciales de autenticación son incorrectas.
     */
    @PostMapping("/auth")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        System.out.println("Email: " + authenticationRequest.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword())
            );
            System.out.println("Authentication OK");
        } catch (BadCredentialsException e) {
            System.out.println(e.getMessage());
            throw new Exception("Incorrect email or password", e);
        }
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        System.out.println("JWT: " + jwt);
        return ResponseEntity.ok(new AuthenticationResponse(jwt).getJwt());
    }
}
