package com.cl.evaluation.register.services;

import com.cl.evaluation.register.converters.UserEntityToUserModelConverter;
import com.cl.evaluation.register.models.LoginModel;
import com.cl.evaluation.register.models.UserModel;
import com.cl.evaluation.register.services.database.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TokenService {
    private String getJWTToken(String username) {
        String secretKey = "v9y$B&E)H@McQfTjWnZr4u7w!z%C*F-JaNdRgUkXp2s5v8y/A?D(G+KbPeShVmYq";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_USER");
        var key = Keys.hmacShaKeyFor(secretKey.getBytes());
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
}
