package net.lavaguides.student_management_system_2.service;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

import net.lavaguides.student_management_system_2.repository.TokenRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class Jwt_service {
    private static final Logger logger = LoggerFactory.getLogger(Jwt_service.class);

    private final String SECRET_KEY = "2a8559e7f55741e47361006208cef0914838b7f14b331d0f0c3b4533dc60772f";
    private final TokenRepository tokenRepository;

    @Autowired
    public Jwt_service(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    public String extractUsername(String token) {
        String username = extractClaims(token, Claims::getSubject);
        logger.info("Extracted username from token: {}", username);
        return username;
    }

    public boolean isValid(String token, UserDetails user){
        String username = extractUsername(token);

        boolean isValidToken = tokenRepository.findByToken(token)
                .map(t-> !t.isLoggedout()).orElse(false);
        boolean isValid = (username.equals(user.getUsername())) && !isTokenExpired(token) && isValidToken;
        logger.info("Token validation result: {}", isValid);


        return isValid;
    }

    private boolean isTokenExpired(String token) {
        boolean isExpired = extractExpiration(token).before(new Date());
        logger.info("Is token expired: {}", isExpired);
        return isExpired;
    }

    private Date extractExpiration(String token) {
        return extractClaims(token, Claims::getExpiration);
    }

    public <T> T extractClaims(String token, Function<Claims, T> resolver) {
        Claims claims = extractAllClaims(token);
        return resolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .setSigningKey(getSignInKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public String generateToken(UserDetails userDetails) {
        String token = Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // 24 hours
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
        logger.info("Generated token for user: {}", userDetails.getUsername());
        return token;
    }

    private Key getSignInKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}

