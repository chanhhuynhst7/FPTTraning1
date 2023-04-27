package com.FPT.webbasic.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {
    // Jwt Secret
    private String jwtSecret="sIoVC8OFOgmxbk9XRYtY2zMKXuYXBGL2d3x1IV37";

    // Jwt Expiration in millis
    public static final long jwtExpiration = (24 * 60 * 60 * 1000);

    private Claims parseToken(String token){
        // Create JwtParser
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(jwtSecret.getBytes())
                .build();
        try {
            return jwtParser.parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedJwtException e) {
            System.out.println(e.getMessage());
        } catch (MalformedJwtException e) {
            System.out.println(e.getMessage());
        } catch (SignatureException e) {
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public boolean validateToken(String token){
        return parseToken(token) != null;
    }

    public String getUsernameFromToken(String token){
        //get Claims
        Claims claims = parseToken(token);
        // Extract subject
        if(claims != null){
            return claims.getSubject();
        }

        return null;
    }

    public String generateToken(String username){

        //Create Key
        Key key= Keys.hmacShaKeyFor(jwtSecret.getBytes());
        //Generate Token
        var currentDate = new Date();
        var expiration = new Date(currentDate.getTime() + jwtExpiration);

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(currentDate)
                .setExpiration(expiration)
                .signWith(key)
                .compact();
    }
}
