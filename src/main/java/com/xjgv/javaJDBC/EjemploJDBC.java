package com.xjgv.javaJDBC;

import java.sql.*;

public class EjemploJDBC {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/java_curso?serverTimezone=Mexico/General";
        String username = "root";
        String password = "admin";
        try {
            Connection conection = DriverManager.getConnection(url, username, password);
            Statement statement = conection.createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM productos");

            while (resultado.next()){
                System.out.println(resultado.getString("nombre"));
            }
            resultado.close();
            statement.close();
            conection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}