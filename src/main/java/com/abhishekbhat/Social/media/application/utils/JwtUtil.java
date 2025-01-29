package com.abhishekbhat.Social.media.application.utils;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.*;

@Component
public class JwtUtil {

    private final SecretKey key;

    public JwtUtil() {
        this.key = getSecreteKey();
    }

    private SecretKey getSecreteKey() {
        String secretString = "adkkal;kjasjk98u3iu3jkneij89ujp98ua98uasiuhasduihwe9";
        String encodedString = Encoders.BASE64.encode(secretString.getBytes());
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(encodedString));
    }

    public String generateToken(String email, UUID id,int minutes) {

        return Jwts.builder()
                .claim("email", email)
                .claim("id", id)
                .issuedAt(Date.from(new Date().toInstant()))
                .expiration(getExpiration(minutes))
                .signWith(key)
                .compact();
    }


    private Date getExpiration(int minutes) {
        return new Date( new Date().getTime() + (long) minutes * 60 * 1000); // 15 minutes
    }

    public Claims isTokenValid(String token) throws JwtException {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public boolean isTokenValidButExpired(String token) {
        try {
            Jwts.parser()
                    .verifyWith(key)
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();

            // If we get here, token has valid signature and is not expired
            return false;

        } catch (io.jsonwebtoken.ExpiredJwtException e) {
            // This specific exception means token has valid signature but is expired
            return true;

        } catch (Exception e) {
            // Any other exception means token is invalid for other reasons
            return false;
        }
    }

}

