package com.utils;/*
  @Classname Token
  @Description TODO token值的操作
  @Date 2022/5/3 0:08
  @Created by mlf02
 */

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TokenUtil {
    private static final long EXPIRE_TIME=240*60*1000;
    private static final String  TOKEN_SECRET="f26e587c28064d0e855e72c0a6a0e618";

    //实现token方法
    public static String getToken(String username,String password){
        String token="";
        //过期时间
        Date date=new Date(System.currentTimeMillis()+EXPIRE_TIME);
        //密钥及加密算法
        Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
        //设置头部信息
        Map<String,Object> head=new HashMap<>(2);
        head.put("typ","JWT");
        head.put("alg","HS256");

    //携带username，password信息，生成签名
        try {
            return JWT.create()
                    .withHeader(head)
                    .withClaim("loginName",username)
                    .withClaim("permission",password)
                    .withExpiresAt(date)
                    .sign(algorithm);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    //验证token
    public static boolean verify(String token){

        try {
            Algorithm algorithm=Algorithm.HMAC256(TOKEN_SECRET);
            JWTVerifier jwtVerifier=JWT.require(algorithm).build();
            DecodedJWT jwt= jwtVerifier.verify(token);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }


    }
    //解密token，返回一个map
    public static String parseJWT(String token){
        DecodedJWT decodedJWT=JWT.decode(token);
        return decodedJWT.getClaim("loginName").asString();
    }
    //判断token是否过期
    public static boolean isJwtExpired(String token){
        try {
            DecodedJWT decodedJWT=JWT.decode(token);
            return decodedJWT.getExpiresAt().before(new Date());
        }catch (Exception e){
            e.printStackTrace();
            return true;
        }
    }

}
