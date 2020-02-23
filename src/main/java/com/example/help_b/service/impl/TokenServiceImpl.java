package com.example.help_b.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.help_b.model.BasicUser;
import com.example.help_b.service.TokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Override
    public String getToken(BasicUser basicUser) {
        Date start=new Date();
        long currentTime=System.currentTimeMillis()+60*30*1000;
        Date end=new Date(currentTime);
        String token= JWT.create()
                .withAudience(new String[]{String.valueOf(basicUser.getId())})
                .withIssuedAt(start).withExpiresAt(end)
                .sign(Algorithm.HMAC256(basicUser.getUsername()));
//        System.out.println("token:"+token);
        return token;
    }
}
