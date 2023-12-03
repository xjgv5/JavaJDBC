package com.xjgv.javaJDBC;

import com.xjgv.javaJDBC.util.ConexionBaseDatos;

import java.sql.*;

public class EjemploJDBC {
    public static void main(String[] args) {

        try(Connection conection = ConexionBaseDatos.getInstance();
        Statement statement = conection.createStatement();
        ResultSet resultado = statement.executeQuery("SELECT * FROM productos")) {

            while (resultado.next()){
                System.out.print(resultado.getInt(1));
                System.out.print("\t");
                System.out.print(resultado.getString("nombre"));
                System.out.print("\t");
                System.out.print("$" + resultado.getInt("precio"));
                System.out.print("\t");
                System.out.println(resultado.getDate("fecha_registro"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
