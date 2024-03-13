package com.patch.atividade.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Date;

public class JwtUtil {

    public String gerarToken (UserDetails userDetails){
        Algorithm algorithm = Algorithm.HMAC256("senha123");
        return JWT.create().withIssuer("WEG")
                .withIssuedAt(new Date())
                .withExpiresAt(new Date(new Date().getTime() + 60*5*1000))
                .withSubject(userDetails.getUsername())
                .sign(algorithm);

    }



    public String getUsername (String token){
        return JWT.decode(token).getSubject();
    }
}
