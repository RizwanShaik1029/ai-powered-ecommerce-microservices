package com.srb.auth.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import java.security.Key;
import java.util.Date;

@Component
public class UtilJwt {

    private final long expiryTime= 1000*60*60;

    private final String secreteKey ="mysecretkeymysecretkeymysecretkeymysecretkey";

    private final Key key = Keys.hmacShaKeyFor(secreteKey.getBytes());

    public String generateJwtToken(String phNumber) {
        return Jwts.builder()
                .setSubject(phNumber)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+expiryTime))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }


    public String extractUserName(String token) {
        Claims claims =extractClaims(token);
        if(claims == null)
        {
            return null;
        }
        return claims.getSubject();
    }

    private Claims extractClaims(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        }catch(Exception e){
            System.out.println("Exception Occured at extractClaims method "+e);
            return null;
        }
    }

    public boolean validateToken(String phNumber, UserDetails userDetails, String token) {

        return phNumber.equals(userDetails.getUsername()) && !isTokenExpeired(token);
    }

    private boolean isTokenExpeired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }
}
