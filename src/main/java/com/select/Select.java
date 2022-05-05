package com.select;/*
  @Classname Select
  @Description TODO 查询数据库
  @Date 2022/5/3 0:06
  @Created by mlf02
 */

import com.utils.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Select {
    public boolean selectUserLogin(String username, String password) {
        boolean isOk=false;
        Connection connection=null;
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            String sql="select * from booksql.login where username=? and password=?";
            statement= connection.prepareStatement(sql);
            statement.setString(1,username);
            statement.setString(2,password);
            resultSet = statement.executeQuery();
            connection.commit();
            if (resultSet.next()){
                isOk=true;
            }
        } catch (SQLException e) {
            if (connection!=null){
                try {
                    connection.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        }
        finally {
            DBUtil.close(connection,statement,resultSet);
        }
        return isOk;
    }
    public boolean selectUsername(String username,String phone){
        boolean isOk=false;
        Connection connection=null;
        PreparedStatement ps=null;
        ResultSet rs=null;
        try {
            connection = DBUtil.getConnection();
            connection.setAutoCommit(false);
            String sql="select * from booksql.login where username = ? or phone= ? ";
            ps = connection.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,phone);
            rs = ps.executeQuery();
            connection.commit();
            if (rs.next()){
              isOk=true;
          }
        } catch (SQLException e) {
            try {
                assert connection != null;
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        }finally {
            DBUtil.close(connection,ps,rs);
        }
        return isOk;
    }


}
