package com.example.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConexaoDao {
    public static Connection getConexao() {
        try {
            Properties props = new Properties();
            props.load(ConexaoDao.class.getClassLoader().getResourceAsStream("application.properties"));

            String url = props.getProperty("db.url");
            String user = props.getProperty("db.user");
            String password = props.getProperty("db.password");

            return DriverManager.getConnection(url, user, password);
        } catch (SQLException | java.io.IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}