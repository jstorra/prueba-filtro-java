package jstorra.controller;

import jstorra.model.inventario.Inventario;
import jstorra.model.inventario.InventarioDAO;

import java.util.List;

public class InventarioController {
    private static final InventarioDAO INVENTARIODAO = new InventarioDAO();

    public static List<Inventario> getAllInventarios() {
        return INVENTARIODAO.getAllInventarios();
    }

    public static Inventario getInventarioById(long idInventario) {
        return INVENTARIODAO.getInventarioById(idInventario);
    }

    public static void insertInventario(Inventario inventario) {
        INVENTARIODAO.insertInventario(inventario);
    }

    public static void updateInventario(Inventario inventario) {
        INVENTARIODAO.updateInventario(inventario);
    }

    public static void deleteInventario(long idInventario) {
        INVENTARIODAO.deleteInventario(idInventario);
    }
}
