package M1S11.configs;

import M1S11.entities.UserEntity;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtConfig {



        // Chave precisa ser Muito forte
        private final String SECRET_KEY = "chaveParaJWTPrecisaSerRealmenteMuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuitoForte";

        // Gera a chave para assinar o token
        private Key getSignInKey () {
            byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
            return Keys.hmacShaKeyFor(keyBytes);
        }

    public String generateToken(UserEntity user) {
        return Jwts.builder()
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // -> 10 horas
                .claim("id", user.getId())
                .claim("username",user.getUsername())
                .claim("profile",user.getProfile())
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    public Jws<Claims> parseClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token);
    }

    public String extractUsername(String token) {
        return parseClaims(token)
                .getBody()
                .getSubject();
    }

    public boolean validateToken(String token) {
        try {
            parseClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }



}
