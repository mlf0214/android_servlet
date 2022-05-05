package com.servlets;/*
  @Classname RegisterServlet
  @Description TODO
  @Date 2022/5/3 13:28
  @Created by mlf02
 */

import com.bean.LoginBean;
import com.callck.UserLoginCallck;
import com.google.gson.Gson;
import com.insert.Insert;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegisterServlet extends HttpServlet {
    private String resPnoSeMsg=null;
    private Map<String,String> mapJson;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        BufferedReader reader = request.getReader();
        LoginBean loginBean = new Gson().fromJson(reader, LoginBean.class);
        String password = loginBean.getPassword();
        String username = loginBean.getUsername();
        String phone = loginBean.getPhone();
        String nickname = loginBean.getNickname();
        Insert insert=new Insert();
        insert.addUserLogin(username, password, phone, nickname, new UserLoginCallck() {
            @Override
            public void RequestRegisterData(String s) {
                resPnoSeMsg=s;
            }
        });
        mapJson=new HashMap<>();
        mapJson.put("msg",resPnoSeMsg);
        String json = new Gson().toJson(mapJson);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(json);
    }
}
