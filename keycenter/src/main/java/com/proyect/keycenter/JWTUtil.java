package com.proyect.keycenter;
/**
import com.proyect.keycenter.modelo.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
**/
public class JWTUtil {
    /**
    private String secret="keycenter";

    public String extracUserename(String token){
        return extracClaims(token, Claims::getSubject);
    }

    public Date extracExpiration(String token){
        return extracClaims(token, Claims::getExpiration);
    }

    public <T> T extracClaims(String token, Function<Claims, T> ClaimResolver){
        final Claims claims = extracAllClaim(token);
        return ClaimResolver.apply(claims);
    }

    public Claims extracAllClaim(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJwt(token).getBody();
    }

    private Boolean isTokenExpired(String token){
        return extracExpiration(token).before(new Date());
    }

    public String generateToken(String username, String password){
        Map<String, Object> claims = new HashMap<>();
        claims.put("password",password);
        return crearToken(claims, username);
    }

    private String crearToken(Map<String, Object> claims, String username){
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 100 * 60 * 60 * 10))
                .signWith(SignatureAlgorithm.HS256, secret).compact();
    }

    public Boolean validateToken(String token, User user){
        final String username = extracUserename(token);
        return username.equals(user.getUsername()) && isTokenExpired(token);
    }
    **/
}
