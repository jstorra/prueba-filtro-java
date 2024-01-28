package jstorra.controller;

import jstorra.model.categoria.Categoria;
import jstorra.model.categoria.CategoriaDAO;

import java.util.List;

public class CategoriaController {
    private static final CategoriaDAO CATEGORIADAO = new CategoriaDAO();

    public static List<Categoria> getAllCategorias() {
        return CATEGORIADAO.getAllCategorias();
    }

    public static Categoria getCategoriaById(long idCategoria) {
        return CATEGORIADAO.getCategoriaById(idCategoria);
    }

    public static void insertCategoria(Categoria categoria) {
        CATEGORIADAO.insertCategoria(categoria);
    }

    public static void updateCategoria(Categoria categoria) {
        CATEGORIADAO.updateCategoria(categoria);
    }

    public static void deleteCategoria(long idCategoria) {
        CATEGORIADAO.deleteCategoria(idCategoria);
    }
}
