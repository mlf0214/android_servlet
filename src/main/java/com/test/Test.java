package com.test;/*
  @Classname Test
  @Description TODO
  @Date 2022/5/3 14:00
  @Created by mlf02
 */

import com.callck.UserLoginCallck;
import com.insert.Insert;

public class Test {
    public static void main(String[] args) {
        Insert insert=new Insert();
        insert.addUserLogin("mlf", "123456", "33", "44", new UserLoginCallck() {
            @Override
            public void RequestRegisterData(String s) {
                System.out.println(s);
            }
        });

    }

}
