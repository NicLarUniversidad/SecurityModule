package com.cl.evaluation.register.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private static String SECRET_KEY = "v9y$B&E)H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPeShVmYq";
    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        var key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        String token = Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(key, SignatureAlgorithm.HS512).compact();

        return "Bearer " + token;
    }

    public String tokenize(String email) {
        return getJWTToken(email);
    }

    public Jws<Claims> validateToken(String token) {
        String jwtToken = token.substring(7);
        var key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        /*Key hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET_KEY),
                SignatureAlgorithm.HS512.getJcaName());*/

        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken);
    }
}
