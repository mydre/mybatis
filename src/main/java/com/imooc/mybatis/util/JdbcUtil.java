package com.imooc.mybatis.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class JdbcUtil {
    public JdbcUtil() {
    }

    public static void commonUpdate(String sql, Object... param) {
        Connection conn = null;
        PreparedStatement preparedStatement = null;

        try {
            conn = getConnection();
            preparedStatement = conn.prepareStatement(sql);

            for(int i = 0; i < param.length; ++i) {
                preparedStatement.setObject(i + 1, param[i]);
            }

            preparedStatement.executeUpdate();
        } catch (Exception var8) {
            var8.printStackTrace();
        } finally {
            closeResources(conn, preparedStatement, (ResultSet)null);
        }

    }

    public static void commit(Connection conn){
        try {
            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void begin(Connection conn){
        if(conn != null){
            try {
                conn.setAutoCommit( false );
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static Connection getConnection(){
        Properties properties = new Properties();
        InputStream is = JdbcUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String driver = properties.getProperty("jdbc.driver");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        String url = properties.getProperty("jdbc.url");
        try {
            //注册驱动
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void closeResources(Connection conn, Statement statement, ResultSet resultSet) {
        if (null != conn) {
            try {
                conn.close();
            } catch (SQLException var6) {
                var6.printStackTrace();
            }
        }

        if (null != statement) {
            try {
                statement.close();
            } catch (SQLException var5) {
                var5.printStackTrace();
            }
        }

        if (null != resultSet) {
            try {
                resultSet.close();
            } catch (SQLException var4) {
                var4.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        System.out.println(getConnection());
    }
}
