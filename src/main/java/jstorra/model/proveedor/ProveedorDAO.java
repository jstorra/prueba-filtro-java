package jstorra.model.proveedor;

import jstorra.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProveedorDAO {
    private static final String SELECT_PROVEEDOR = "SELECT * FROM proveedor";
    private static final String SELECT_PROVEEDORBYID = "SELECT * FROM proveedor WHERE idProveedor = ?";
    private static final String INSERT_PROVEEDOR = "INSERT INTO proveedor (nombreProveedor) VALUES (?)";
    private static final String UPDATE_PROVEEDOR = "UPDATE proveedor SET nombreProveedor = ? WHERE idProveedor = ?";
    private static final String DELETE_PROVEEDOR = "DELETE FROM proveedor WHERE idProveedor = ?";

    public List<Proveedor> getAllProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PROVEEDOR)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Proveedor proveedor = new Proveedor();
                    proveedor.setIdProveedor(rs.getLong("idProveedor"));
                    proveedor.setNombreProveedor(rs.getString("nombreProveedor"));
                    proveedores.add(proveedor);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return proveedores;
    }

    public Proveedor getProveedorById(long idProveedor) {
        Proveedor proveedor = new Proveedor();
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_PROVEEDORBYID)) {
            ps.setLong(1, idProveedor);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    proveedor.setIdProveedor(rs.getLong("idProveedor"));
                    proveedor.setNombreProveedor(rs.getString("nombreProveedor"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return proveedor;
    }

    public void insertProveedor(Proveedor proveedor) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_PROVEEDOR)) {
            ps.setString(1, proveedor.getNombreProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateProveedor(Proveedor proveedor) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_PROVEEDOR)) {
            ps.setString(1, proveedor.getNombreProveedor());
            ps.setLong(2, proveedor.getIdProveedor());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteProveedor(long idProveedor) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_PROVEEDOR)) {
            ps.setLong(1, idProveedor);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
