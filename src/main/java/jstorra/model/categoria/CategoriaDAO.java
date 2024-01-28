package jstorra.model.categoria;

import jstorra.ConnectionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaDAO {
    private static final String SELECT_CATEGORIA = "SELECT * FROM categoria";
    private static final String SELECT_CATEGORIABYID = "SELECT * FROM categoria WHERE idCategoria = ?";
    private static final String INSERT_CATEGORIA = "INSERT INTO categoria (nombreCategoria) VALUES (?)";
    private static final String UPDATE_CATEGORIA = "UPDATE categoria SET nombreCategoria = ? WHERE idCategoria = ?";
    private static final String DELETE_CATEGORIA = "DELETE FROM categoria WHERE idCategoria = ?";

    public List<Categoria> getAllCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_CATEGORIA)) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(rs.getLong("idCategoria"));
                    categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                    categorias.add(categoria);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categorias;
    }

    public Categoria getCategoriaById(long idCategoria) {
        Categoria categoria = new Categoria();
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(SELECT_CATEGORIABYID)) {
            ps.setLong(1, idCategoria);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    categoria.setIdCategoria(rs.getLong("idCategoria"));
                    categoria.setNombreCategoria(rs.getString("nombreCategoria"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return categoria;
    }

    public void insertCategoria(Categoria categoria) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(INSERT_CATEGORIA)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateCategoria(Categoria categoria) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(UPDATE_CATEGORIA)) {
            ps.setString(1, categoria.getNombreCategoria());
            ps.setLong(2, categoria.getIdCategoria());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCategoria(long idCategoria) {
        try (Connection connection = ConnectionDB.MySQLConnection();
             PreparedStatement ps = connection.prepareStatement(DELETE_CATEGORIA)) {
            ps.setLong(1, idCategoria);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
