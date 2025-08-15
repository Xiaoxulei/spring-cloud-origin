package com.xuxiaolei.security.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

// JwtUtils.java
@Component
public class JwtUtils {

    private final String SECRET = "mySecretKeymySecretKeymySecretKeymySecretKeymySecretKey"; // JWT 秘钥，生产环境要安全存储
    private final long EXPIRATION_MS = 3600_000; // 1小时过期

    // 生成 JWT
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_MS))
                .signWith(SignatureAlgorithm.HS256, SECRET)
                .compact();
    }

    // 解析 JWT 获取用户名
    public String getUsernameFromToken(String token) {
        return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody().getSubject();
    }

    // 验证 JWT 是否有效
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
