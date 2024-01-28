package jstorra.model.inventario;

import jstorra.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InventarioDAO {
    private static final String SELECT_INVENTARIO = "SELECT * FROM inventario";
    private static final String SELECT_INVENTARIOBYID = "SELECT * FROM inventario WHERE idInventario = ?";
    private static final String INSERT_INVENTARIO = "INSERT INTO inventario (idProducto, cantidad) VALUES (?, ?)";
    private static final String UPDATE_INVENTARIO = "UPDATE inventario SET idProducto = ?, cantidad = ? WHERE idInventario = ?";
    private static final String DELETE_INVENTARIO = "DELETE FROM inventario WHERE idInventario = ?";

    public List<Inventario> getAllInventarios() {
        List<Inventario> inventarios = new ArrayList<>();
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_INVENTARIO)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Inventario inventario = new Inventario();
                    inventario.setIdProducto(rs.getLong("idProducto"));
                    inventario.setCantidad(rs.getInt("cantidad"));
                    inventarios.add(inventario);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return inventarios;
    }

    public Inventario getInventarioById(long idInventario) {
        Inventario inventario = new Inventario();
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_INVENTARIOBYID)) {
            ps.setLong(1, idInventario);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    inventario.setIdInventario(rs.getLong("idInventario"));
                    inventario.setIdProducto(rs.getLong("idProducto"));
                    inventario.setCantidad(rs.getInt("cantidad"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return inventario;
    }

    public void insertInventario(Inventario inventario) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_INVENTARIO)) {
            ps.setLong(1, inventario.getIdProducto());
            ps.setInt(2, inventario.getCantidad());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateInventario(Inventario inventario) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_INVENTARIO)) {
            ps.setLong(1, inventario.getIdProducto());
            ps.setInt(2, inventario.getCantidad());
            ps.setLong(3, inventario.getIdInventario());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteInventario(long idInventario) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_INVENTARIO)) {
            ps.setLong(1, idInventario);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
