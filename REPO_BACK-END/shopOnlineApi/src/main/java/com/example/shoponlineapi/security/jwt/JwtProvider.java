package com.example.shoponlineapi.security.jwt;

import com.example.shoponlineapi.security.userprincal.UserPrinciple;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {
    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);
    private String jwtSecret = "201101";
    private int jwrExpiration = 86400;

    public String createToken(Authentication authentication) {
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        return Jwts.builder().setSubject(userPrinciple.getUsername())
                .setIssuedAt(new Date()).setExpiration(new Date(new Date()
                .getTime()+jwrExpiration*1000))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        } catch (SignatureException e) {
            logger.error("invalid jwt signature -> message: {}",e);
        } catch (MalformedJwtException e) {
            logger.error("invalid format token -> message: {}",e);
        }  catch (ExpiredJwtException e) {
            logger.error("expired jwt token -> message: {}",e);
        }  catch (UnsupportedJwtException e) {
            logger.error("unsupported jwt token -> message: {}",e);
        }  catch (IllegalArgumentException e) {
            logger.error("jwt claims is empty -> message: {}",e);
        }
        return false;
    }

    public String getUserNameFromToken(String token) {
        String userName = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
        return userName;
    }
}
