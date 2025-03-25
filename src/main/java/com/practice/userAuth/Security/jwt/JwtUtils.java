package com.practice.userAuth.Security.jwt;
import com.practice.userAuth.Security.srevices.UserDetailsImpl;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.xml.crypto.Data;
import java.security.Key;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${bezkoder.app.jwtSecret}")
    private String jwtSecret ;

    @Value("${bezkoder.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    public String generateJwtToken(Authentication authentication){
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
//        SecretKey key = Jwts.SIG.HS256.key().build();
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime()+jwtExpirationMs))
                .signWith(key(), SignatureAlgorithm.HS256) // Use HS512, not ES512
                .compact();
    }

    private SecretKey key() {
//        byte[] keyBytes = Base64.getDecoder().decode(jwtSecret);
        return Jwts.SIG.HS256.key().build();
//                return Keys.hmacShaKeyFor(keyBytes);
//        return Keys.hmacShaKeyFor(jwtSecret.getBytes());
//        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtSecret));
    }

    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }

    public boolean validateJwtToken(String authToken){
        try {
            Jwts.parser().setSigningKey(jwtSecret).build().parseClaimsJws(authToken);
            return true;
        }catch (MalformedJwtException e){
            logger.error("Invalid JWT token: {}",e.getMessage());
        }catch (ExpiredJwtException e){
            logger.error("JWT token is expired: {}", e.getMessage());
        }catch (UnsupportedJwtException e){
            logger.error("JWT token is unsupported: {}", e.getMessage());
        }catch (IllegalArgumentException e){
            logger.error("JWT claims string is empty: {}",e.getMessage());
        }
        return false;
    }

}
