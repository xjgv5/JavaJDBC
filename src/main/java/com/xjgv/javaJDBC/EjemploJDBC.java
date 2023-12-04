package com.xjgv.javaJDBC;

import com.xjgv.javaJDBC.modelo.Producto;
import com.xjgv.javaJDBC.repositorio.ProductoRepositorioImpl;
import com.xjgv.javaJDBC.repositorio.Repositorio;
import com.xjgv.javaJDBC.util.ConexionBaseDatos;

import java.sql.*;

public class EjemploJDBC {
    public static void main(String[] args) {

        try(Connection conection = ConexionBaseDatos.getInstance()) {
            Repositorio<Producto> repositorio = new ProductoRepositorioImpl();
            repositorio.listar().forEach(System.out::println);

            System.out.println(repositorio.porId(1L));

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
