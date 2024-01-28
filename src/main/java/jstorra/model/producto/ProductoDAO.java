package jstorra.model.producto;

import jstorra.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private static final String SELECT_PRODUCTO = "SELECT * FROM producto";
    private static final String SELECT_PRODUCTOBYID = "SELECT * FROM producto WHERE idProducto = ?";
    private static final String INSERT_PRODUCTO = "INSERT INTO producto (nombreProducto, precio, idCategoria, idProveedor) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PRODUCTO = "UPDATE producto SET nombreProducto = ?, precio = ?, idCategoria = ?, idProveedor = ? WHERE idProducto = ?";
    private static final String DELETE_PRODUCTO = "DELETE FROM producto WHERE idProducto = ?";

    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCTO)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setIdProducto(rs.getLong("idProducto"));
                    producto.setNombreProducto(rs.getString("nombreProducto"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setIdCategoria(rs.getLong("idCategoria") == 0 ? null : rs.getLong("idCategoria"));
                    producto.setIdProveedor(rs.getLong("idProveedor") == 0 ? null : rs.getLong("idProveedor"));
                    productos.add(producto);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return productos;
    }

    public Producto getProductoById(long idProducto) {
        Producto producto = new Producto();
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCTOBYID)) {
            ps.setLong(1, idProducto);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    producto.setIdProducto(rs.getLong("idProducto"));
                    producto.setNombreProducto(rs.getString("nombreProducto"));
                    producto.setPrecio(rs.getDouble("precio"));
                    producto.setIdCategoria(rs.getLong("idCategoria") == 0 ? null : rs.getLong("idCategoria"));
                    producto.setIdProveedor(rs.getLong("idProveedor") == 0 ? null : rs.getLong("idProveedor"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return producto;
    }

    public void insertProducto(Producto producto) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PRODUCTO)) {
                ps.setString(1, producto.getNombreProducto());
                ps.setDouble(2, producto.getPrecio());
                ps.setLong(3, producto.getIdCategoria());
                ps.setLong(4, producto.getIdProveedor());
                ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProducto(Producto producto) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PRODUCTO)) {
            ps.setString(1, producto.getNombreProducto());
            ps.setDouble(2, producto.getPrecio());
            ps.setObject(3, producto.getIdCategoria());
            ps.setObject(4, producto.getIdProveedor());
            ps.setLong(5, producto.getIdProducto());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProducto(long idProducto) {
        try (Connection connection = ConnectionDB.MySQLConnection();
        PreparedStatement ps = connection.prepareStatement(DELETE_PRODUCTO)){
            ps.setLong(1, idProducto);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
