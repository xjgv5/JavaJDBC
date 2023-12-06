package com.xjgv.javaJDBC.repositorio;

import com.xjgv.javaJDBC.modelo.Producto;
import com.xjgv.javaJDBC.util.ConexionBaseDatos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepositorioImpl implements Repositorio<Producto> {

    private Connection obtenerConexion() throws SQLException {
        return ConexionBaseDatos.getInstance();
    }
    @Override
    public List<Producto> listar() {
        List<Producto> productos = new ArrayList<>();
        try(Statement statement  = obtenerConexion().createStatement();
            ResultSet resultado = statement.executeQuery("SELECT * FROM productos")){
            while (resultado.next()){
                Producto p = crearProducto(resultado);
                productos.add(p);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return productos;
    }

    @Override
    public Producto porId(Long id) {
        Producto producto = null;
        try(PreparedStatement statement = obtenerConexion().
                prepareStatement("SELECT * FROM productos WHERE id = ?")) {
            statement.setLong(1, id);
            try (ResultSet resultado = statement.executeQuery()) {
                if (resultado.next()) {
                    producto = crearProducto(resultado);
                }
            }
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        String sql;
        if (producto.getId() != 0 && producto.getId() > 0) {
            sql = "UPDATE productos SET nombre=? precio=? WHERE id=?";
        } else {
            sql = "INSERT INTO productos(nombre, precio, fecha_registro) VALUES (?, ?, ?)";

        }
        try(PreparedStatement statement = obtenerConexion().prepareStatement(sql)){
            statement.setString(1, producto.getNombre());
            statement.setLong(2, producto.getPrecio());


            if (producto.getId() != 0 && producto.getId() > 0) {
                statement.setLong(3, producto.getId());
            }
            else {
                statement.setDate(3, new Date(producto.getFechaRegistro().getTime()));

            }

            statement.executeUpdate();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void eliminar(Long id) {
        try(PreparedStatement statement = obtenerConexion().prepareStatement("DELTE FROM productos WHERE id=? ")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static Producto crearProducto(ResultSet resultado) throws SQLException {
        Producto p = new Producto();
        p.setId(resultado.getLong("id"));
        p.setNombre(resultado.getString("nombre"));
        p.setPrecio(resultado.getInt("precio"));
        p.setFechaRegistro(resultado.getDate("fecha_registro"));
        return p;
    }
}
