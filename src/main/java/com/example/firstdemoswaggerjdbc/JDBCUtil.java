package com.example.firstdemoswaggerjdbc;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*public class JDBCUtil {
    public static Connection getConn(){
        String driver="com.mysql.jdbc.Driver";
        String url="jdbc:mysql://localhost:3306/gjp?useUnicode=true&characterEncoding=utf-8&useSSL=false";
        String username="root";
        String password="root";
        Connection conn=null;
        try {
            Class.forName(driver);
            conn=DriverManager.getConnection(url,username,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}*/

public class JDBCUtil {
    private static String driverClass = null;
    private static String url = null;
    private static String user = null;
    private static String password = null;

    /**
     * 静态块
     * 存在异常已解决
     */
    static {
        //忘记了getClassLoader()，报异常Exception in thread "main" java.lang.ExceptionInInitializerError
        InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("application.properties");
        Properties properties = new Properties();
        try {
            properties.load(in);
            driverClass = properties.getProperty("jdbc.driver");
            url = properties.getProperty("jdbc.url");
            user = properties.getProperty("jdbc.username");
            password = properties.getProperty("jdbc.password");
            //注册驱动器

            Class.forName(driverClass);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConn() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url, user, password);
            return conn;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
