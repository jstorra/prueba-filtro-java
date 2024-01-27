package jstorra.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import jstorra.ConnectionDB;

public class ProductoDAO {
    private static final String SELECT_PRODUCTO = "SELECT * FROM producto";
    private static final String SELECT_PRODUCTOBYID = "SELECT * FROM producto WHERE idProducto = ?";
    
    public List<Producto> getAllProductos() {
        List<Producto> productos = new ArrayList<>();
        try (Connection connection = ConnectionDB.MySQLConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCTO);
                 ResultSet rs = ps.executeQuery()) {
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
        try (Connection connection = ConnectionDB.MySQLConnection()) {
            try (PreparedStatement ps = connection.prepareStatement(SELECT_PRODUCTOBYID);
                 ResultSet rs = ps.executeQuery()) {
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

    }

    public void updateProducto(Producto producto) {

    }

    public void deleteProducto(long idProducto) {

    }
}
