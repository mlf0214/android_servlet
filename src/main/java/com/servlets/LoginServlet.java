package com.servlets;/*
  @Classname LoginServlet
  @Description TODO
  @Date 2022/5/2 17:17
  @Created by mlf02
 */

import com.bean.LoginBean;
import com.google.gson.Gson;
import com.select.Select;
import com.utils.TokenUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        String username = null;
        String password=null;
        try {
            BufferedReader reader = request.getReader();
            Gson gson=new Gson();
            LoginBean loginBean = gson.fromJson(reader, LoginBean.class);
             username = loginBean.getUsername();
             password = loginBean.getPassword();
        }catch (Exception e){
            e.printStackTrace();
            System.out.printf(e.toString());
        }

        Select select=new Select();
        boolean b = select.selectUserLogin(username, password);
        if (b){
            String token = TokenUtil.getToken(username, password);
            String json="{msg:'登录成功',token:"+token+"}";
            response.getWriter().write(json);
        }else {
            String json="{msg:'登录失败,用户名或密码错误'}";
            response.getWriter().write(json);
        }
    }


}
