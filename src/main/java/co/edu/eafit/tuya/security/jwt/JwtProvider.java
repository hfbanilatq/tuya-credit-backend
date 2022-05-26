package co.edu.eafit.tuya.security.jwt;


import co.edu.eafit.tuya.security.dto.JwtDto;
import co.edu.eafit.tuya.security.model.PrincipalUser;
import com.nimbusds.jwt.JWT;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.JWTParser;
import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


@Component
public class JwtProvider {
    private final static Logger logger = LoggerFactory.getLogger(JwtProvider.class);

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private int expiration;

    private String errorMessage;

    public String generateToken(Authentication authentication) {
        PrincipalUser principalUser = (PrincipalUser) authentication.getPrincipal();
        List<String> roles = principalUser.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList());
        return Jwts.builder()
                .setSubject(principalUser.getUsername())
                .claim("roles", roles)
                .claim("username", principalUser.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    public String getUserNameFromToken(String token) {
        return Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(secret.getBytes()).parseClaimsJws(token);
            return true;

        }catch (MalformedJwtException e) {
            this.errorMessage = "Token mal formado";
            logger.error(errorMessage);
        }catch (UnsupportedJwtException e) {
            this.errorMessage = "Token no soportado";
            logger.error(errorMessage);
        }catch (ExpiredJwtException e) {
            this.errorMessage = "El token ya ha expirado";
            logger.error(errorMessage);
        }catch (IllegalArgumentException e) {
            this.errorMessage = "Valor ilegal en el toekn o se encuentra vacio";
            logger.error(errorMessage);
        }catch (SignatureException e) {
            this.errorMessage = "Error en la firma";
            logger.error(errorMessage);
        }
        return false;
    }


    public String getErrorMessage() {
        return this.errorMessage;
    }
    
    public String refreshToken(JwtDto jwtDto) throws ParseException {
        JWT jwt = JWTParser.parse(jwtDto.getToken());
        JWTClaimsSet jwtClaimsSet = jwt.getJWTClaimsSet();
        String username = jwtClaimsSet.getSubject();
        List<String> roles = (List<String>) jwtClaimsSet.getClaim("roles");
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles)
                .claim("username", username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(new Date().getTime() + expiration * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }
}
