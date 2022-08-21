package com.cl.evaluation.register.services;

import com.fasterxml.jackson.core.JsonParseException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    private static final String SECRET_KEY = "v9y$B&E)H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPeShVmYq";
    private TimeService timeService;
    @Value("${security.api.token.ttl:60000}")
    private int timeToLive;

    @Autowired
    public TokenService(TimeService timeService) {
        this.timeService = timeService;
    }
    private String getJWTToken(String username) {
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        var key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts
                .builder()
                .setId("softtekJWT")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(timeService.getCurrentTime())
                .setExpiration(timeService.getTime(timeToLive))
                .signWith(key, SignatureAlgorithm.HS512).compact();
    }

    public String tokenize(String email) {
        return getJWTToken(email);
    }

    public Jws<Claims> validateToken(String token) throws JsonParseException {
        String jwtToken = token.substring(7);
        var key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwtToken);
    }
}
