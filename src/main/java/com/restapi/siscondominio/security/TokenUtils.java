package com.restapi.siscondominio.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtils {

    private final static String ACCESS_TOKEN_SECRET="RH1IRp6ajBhkQ5j4Bo2Fw1Qmq699v3NEkspAxSnLQX1iu6VHw5";


    private final static Long ACCESS_TOKEN_VALIDITY_SECONDS= 2_592_00L;

    public static String createToken(String nombre, String cedula){
        long expirationTime= ACCESS_TOKEN_VALIDITY_SECONDS*1_000;
        Date expirationDate= new Date(System.currentTimeMillis()+expirationTime);

        Map<String, Object> extra = new HashMap<>();
        extra.put("usuNombres", nombre);

        return Jwts.builder()
                .setSubject("usuCedula")
                .setExpiration(expirationDate)
                .addClaims(extra)
                .signWith(Keys.hmacShaKeyFor(ACCESS_TOKEN_SECRET.getBytes()))
                .compact();
    }

    public static UsernamePasswordAuthenticationToken getAuthentication(String token){
       try {
           Claims claims= Jwts.parserBuilder()
                   .setSigningKey(ACCESS_TOKEN_SECRET.getBytes())
                   .build()
                   .parseClaimsJws(token)
                   .getBody();
           String cedula = claims.getSubject();


           return new UsernamePasswordAuthenticationToken(cedula,null, Collections.emptyList());
       }catch (JwtException e){
           return null;

       }

    }





}
