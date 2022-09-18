package com.example.flight.security.service;

import com.example.flight.security.dto.WebUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expire.duration}")
    private Long expirationDuration;
    public String getGenerateToken(WebUser user) {
        Claims claims=Jwts.claims().setSubject(user.getUsername());
        Date now= new Date();
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(new Date(now.getTime()+expirationDuration))
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }
}
