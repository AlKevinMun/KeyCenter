package com.proyect.keycenter.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Utilidad para manejar tokens JWT (JSON Web Tokens) en una aplicación Spring Boot.
 * Proporciona funcionalidades para generar, validar y extraer información de tokens JWT.
 *
 * @author Alejandro Parrilla Ruiz
 */
@Service
public class JWTUtil {
    /**
     * Clave secreta utilizada para firmar los tokens JWT.
     */
    private String secret="secret2024abcabcabcacvahshshjscklbjsdiofbdsj";


    /**
     * Extrae el correo electrónico del token JWT.
     * @param token El token JWT del cual se extraerá el correo electrónico.
     * @return El correo electrónico asociado al token.
     */
    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    /**
     * Extrae un reclamo específico del token JWT.
     * @param token El token JWT del cual se extraerá el reclamo.
     * @param claimResolver Función que determina cómo resolver el reclamo.
     * @param <T> Tipo genérico del reclamo.
     * @return El valor del reclamo resuelto.
     */
    private <T> T extractClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    /**
     * Extrae todos los reclamos del token JWT.
     * @param token El token JWT del cual se extraerán los reclamos.
     * @return Los reclamos contenidos en el token.
     */
    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * Verifica si el token JWT ha expirado.
     * @param token El token JWT a verificar.
     * @return Verdadero si el token ha expirado, falso en caso contrario.
     */
    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }


    /**
     * Extrae la fecha de expiración del token JWT.
     * @param token El token JWT del cual se extraerá la fecha de expiración.
     * @return La fecha de expiración del token.
     */
    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }

    /**
     * Genera un nuevo token JWT para el usuario dado.
     * @param userDetails Detalles del usuario para el cual se generará el token.
     * @return El token JWT generado.
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    /**
     * Crea un token JWT con los reclamos dados y el sujeto especificado.
     * @param claims Reclamos a incluir en el token.
     * @param subject Sujeto del token (generalmente el nombre de usuario).
     * @return El token JWT creado.
     */
    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }


    /**
     * Valida un token JWT contra los detalles del usuario.
     * @param token El token JWT a validar.
     * @param userDetails Detalles del usuario contra los cuales se validará el token.
     * @return Verdadero si el token es válido para el usuario, falso en caso contrario.
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }
}
