package io.github.devtiagom.dynamicsearch.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JWTUtils {

    @Value("${security.jwt.secret}")
    private String signtKey;

    @Value("${security.jwt.expiration}")
    private Long expiration;

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(SignatureAlgorithm.HS512, signtKey)
                .compact();
    }

    public String getUsername(String token) throws ClaimJwtException {
        return this.getClaims(token).getSubject();
    }

    public boolean isValidToken(String token) {
        try {
            Claims claims = this.getClaims(token);
            Date expirationDate = claims.getExpiration();
            Date now = new Date(System.currentTimeMillis());
            return now.before(expirationDate);
        } catch (ClaimJwtException exception) {
            return Boolean.FALSE;
        }
    }

    private Claims getClaims(String token) throws ClaimJwtException {
        return Jwts.parser()
                .setSigningKey(signtKey)
                .parseClaimsJws(token)
                .getBody();
    }
}
