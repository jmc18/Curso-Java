package models.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.ConexionJdbc;

import models.entity.Producto;
import util.ConexionJdbcDataSource;

public class ProductoDaoJDBC implements IProductoDao {

    private Connection conn;

    public void setConnection(Connection conn) {
        this.conn = conn;
    }

    public Connection getConnection() {
        if (conn == null) {
            conn = new ConexionJdbcDataSource().getConnection();
        }
        return conn;
    }

    public void closeConnection() {
        try {
            getConnection().close();
            conn = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Producto> listar() {
        List<Producto> lista = new ArrayList<Producto>();
        try {
            Statement stmt = getConnection().createStatement();
            ResultSet resultado = stmt.executeQuery("SELECT * FROM productos");
            while (resultado.next()) {
                Producto producto = new Producto();
                producto.setId(resultado.getInt("id"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getInt("precio"));
                producto.setCantidad(resultado.getInt("cantidad"));

                lista.add(producto);
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return lista;
    }

    @Override
    public Producto buscarPorId(int id) {
        Producto producto = null;
        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                    "SELECT * FROM productos WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet resultado = stmt.executeQuery();
            if (resultado.next()) {
                producto = new Producto();
                producto.setId(resultado.getInt("id"));
                producto.setNombre(resultado.getString("nombre"));
                producto.setPrecio(resultado.getInt("precio"));
                producto.setCantidad(resultado.getInt("cantidad"));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return producto;
    }

    @Override
    public void guardar(Producto producto) {
        try {
            PreparedStatement stmt = null;
            if (producto.getId() > 0) {
                String sql = "UPDATE productos SET nombre=?,precio=?,cantidad=? WHERE id=?";
                stmt = getConnection().prepareStatement(sql);
                stmt.setInt(4, producto.getId());
            } else {
                String sql = "INSERT INTO productos(nombre, precio, cantidad) VALUES (?,?,?)";
                stmt = getConnection().prepareStatement(sql);
            }
            stmt.setString(1, producto.getNombre());
            stmt.setInt(2, producto.getPrecio());
            stmt.setInt(3, producto.getCantidad());

            stmt.executeUpdate();
            stmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }

    @Override
    public void eliminar(Producto producto) {
        try {
            PreparedStatement stmt = getConnection().prepareStatement(
                    "DELETE FROM productos WHERE id=?");
            stmt.setInt(1, producto.getId());
            stmt.executeUpdate();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
    }
}
