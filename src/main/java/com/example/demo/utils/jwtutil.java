package com.example.demo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class jwtutil {

    public static String tee(Map map){
        String JWT = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"itabcda")
                .setClaims(map)
                .setExpiration(new Date(System.currentTimeMillis() + 3600*1000))
                .compact();
        return JWT;
    }

    public static Claims parseJWT(String jwt){
        Claims claims = Jwts.parser()
                .setSigningKey("itabcda")
                .parseClaimsJws(jwt)
                .getBody();
        return claims;
    }
}
