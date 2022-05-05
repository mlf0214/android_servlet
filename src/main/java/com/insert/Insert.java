package com.insert;/*
  @Classname Inster
  @Description TODO 往数据库添加数据
  @Date 2022/5/3 13:33
  @Created by mlf02
 */

import com.callck.UserLoginCallck;
import com.select.Select;
import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Insert {

    Select select=new Select();
    public Insert() {
    }
    public void addUserLogin(String username, String password, String phone, String nickname,UserLoginCallck userLoginCallck){
        boolean selectUserLogin = select.selectUsername(username, phone);
        if (!selectUserLogin){
            Connection connection=null;
            PreparedStatement ps=null;
            int cont=0;
            try {
                 connection= DBUtil.getConnection();
                 connection.setAutoCommit(false);
                 String sql="insert into booksql.login (username,password,phone,nickname) values (?,?,?,?)";
                ps = connection.prepareStatement(sql);
                ps.setString(1,username);
                ps.setString(2,password);
                ps.setString(3,phone);
                ps.setString(4,nickname);
                cont = ps.executeUpdate();
                connection.commit();
            } catch (SQLException e) {
                try {
                    assert connection != null;
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                e.printStackTrace();
            }finally {
                DBUtil.close(connection,ps,null);
            }
            if (cont==1){
                userLoginCallck.RequestRegisterData("操作成功");
            }
        }else {
           userLoginCallck.RequestRegisterData("注册的用户名/手机号重复,请重新输入");
        }
    }

}
