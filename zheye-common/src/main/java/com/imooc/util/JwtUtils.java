package com.imooc.util;

import cn.hutool.core.util.StrUtil;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Map;

/**
 * @author wzy
 * @date 2024/11/22 17:11
 */
@Slf4j
public class JwtUtils {
    /**
     * token 签名
     */
    private static final String TOKEN_SIGN_KEY = "0HQF34mO32e2ZrQHHkRT6JzvEKSQAJxdYEd4JbFu2O2NUnz66u0tD2NnTJGlOfUq";

    private static final String BASE64_SECURITY = Base64.getEncoder().encodeToString(TOKEN_SIGN_KEY.getBytes(StandardCharsets.UTF_8));


    /***
     * 创建token
     */
    public static String createToken(Map<String, String> userInfoMap, long expire) {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        // base64加密后，生成签名密钥
        byte[] apiKeySecretBytes = Base64.getDecoder().decode(BASE64_SECURITY);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //添加JWT的类
        JwtBuilder builder = Jwts.builder().setHeaderParam("typ", "JsonWebToken");

        //设置参数到jwt
        userInfoMap.forEach(builder::claim);

        //Token过期时间
        long expMillis = nowMillis + expire * 1000;
        Date exp = new Date(expMillis);
        builder.setIssuedAt(now).setNotBefore(now).setExpiration(exp).signWith(signingKey, signatureAlgorithm);

        return builder.compact();
    }

    /***
     * 解析jwt
     */
    public static Claims parseJwt(String token, long allowedClockSkewSeconds) {
        if (StrUtil.isEmpty(token)) {
            return null;
        }
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Base64.getDecoder().decode(BASE64_SECURITY))
                    .setAllowedClockSkewSeconds(allowedClockSkewSeconds)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
        } catch (ExpiredJwtException ex) {
            log.error("token过期", ex);
            //过期
            //抛异常 让系统捕获到返回到前端
        } catch (SecurityException ex) {
            log.error("签名错误", ex);
            //签名错误
            //抛异常 让系统捕获到返回到前端
        } catch (IllegalArgumentException ex) {
            log.error("token为空", ex);
            //token 为空
            //抛异常 让系统捕获到返回到前端
        } catch (Exception e) {
            log.error("解析token异常", e);
            //抛异常 让系统捕获到返回到前端
        }
        return null;
    }
}
