package com.proyecto.sistema.security;

import com.proyecto.sistema.model.UsuarioPrincipal;
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JwtProvider {

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private int expiration;


    private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    public String generateToken(Authentication authentication){
        UsuarioPrincipal usuarioPrincipal = (UsuarioPrincipal) authentication.getPrincipal();
        return Jwts.builder()
                .signWith(getKey(secret))
                //.signWith(SignatureAlgorithm.HS512,secret) -> esta en desuso
                .setSubject(usuarioPrincipal.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000))
                .claim("roles",getRoles(usuarioPrincipal))
                .claim("nombre",usuarioPrincipal.getNombre())
                .compact();
    }

    public String getUsernameFromToken(String token){
        return Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody().getSubject();
        //return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();-> esta en desuso
    }

    public boolean validateToken(String token){
        try {
            Jwts.parserBuilder().setSigningKey(getKey(secret)).build().parseClaimsJws(token).getBody();
            //Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();-> esta en desuso
            return true;
        } catch (ExpiredJwtException e) {
            logger.error("token expired");
        } catch (UnsupportedJwtException e) {
            logger.error("token unsupported");
        } catch (MalformedJwtException e) {
            logger.error("token malformed");
        } catch (SignatureException e) {
            logger.error("mala signature");
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }catch (Exception e) {
            logger.error("token fallo");
        }
        return false;
    }

    private List<String> getRoles(UsuarioPrincipal usuarioPrincipal){
        return usuarioPrincipal.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());

    }

    private Key getKey(String secret){
        byte [] secretBytes = Decoders.BASE64URL.decode(secret);
        return Keys.hmacShaKeyFor(secretBytes);
    }
}
