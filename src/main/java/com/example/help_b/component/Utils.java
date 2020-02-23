package com.example.help_b.component;

import com.example.help_b.dao.UserDao;
import com.example.help_b.model.BasicUser;
import com.example.help_b.model.SysUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.CompressionCodecs;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class Utils {
    @Resource(name = "userDao")
    UserDao userDao;
    @Value("${token.expiration}")
    private Long tokenExpiration;
    @Value("${token.secret}")
    private String secret;
    @Bean
    public SimpleDateFormat simpleDateFormat() {
        return new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
    }
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
    public String getToken(BasicUser basicUser) {
        List<String> list = new ArrayList<>();
        for(GrantedAuthority grantedAuthority:basicUser.getAuthorities()){
            list.add(grantedAuthority.getAuthority());
        }
        Map<String,Object> claims = new HashMap<>();
        claims.put("userId",basicUser.getId());
        claims.put("scope",list.get(0));
        //凭什么get（0）有待观察 TODO
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(basicUser.getUsername())
                .setId(UUID.randomUUID().toString())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+tokenExpiration*1000))
                .compressWith(CompressionCodecs.DEFLATE)
                .signWith(SignatureAlgorithm.HS256,secret)
                .compact();
    }

    public SysUser getUserFromToken(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(token)
                .getBody();
        SysUser sysUser = new SysUser();
        sysUser.setId((Integer) claims.get("userId"));
        sysUser.setUsername(claims.getSubject());
        sysUser.setRole(userDao.selectRoleByName((String)claims.get("scope")));
        System.out.println(sysUser);
        return sysUser;
    }

    public String refreshToken(String oldToken) {
        Claims claims = Jwts.parser()
                .setSigningKey(secret)
                .parseClaimsJws(oldToken)
                .getBody();
        Integer userId = (Integer) claims.get("userId");
        List<SysUser> sysUsers = userDao.selectSysUserById(userId);
        String token = getToken(sysUsers.get(0));
        return token;
    }
}