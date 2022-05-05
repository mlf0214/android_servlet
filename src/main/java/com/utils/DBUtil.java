package com.utils;/*
  @Classname DButil
  @Description TODO
  @Date 2022/5/2 21:42
  @Created by mlf02
 */

import java.sql.*;
import java.util.ResourceBundle;

public class DBUtil {
    private static ResourceBundle resourceBundle=ResourceBundle.getBundle("db");
    private static String url=resourceBundle.getString("url");
    private static String drive=resourceBundle.getString("drive");
    private static String usr=resourceBundle.getString("usr");
    private static String psd=resourceBundle.getString("psd");
    static {

        try {
            Class.forName(drive);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
    public static Connection getConnection() throws SQLException {
        Connection connection= DriverManager.getConnection(url,usr,psd);
        return connection;
    }
    public static void close(Connection connection, Statement statement, ResultSet resultSet){
        if (connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (        statement!=null
        ){
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
