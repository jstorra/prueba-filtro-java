package jstorra.controller;

import jstorra.model.proveedor.Proveedor;
import jstorra.model.proveedor.ProveedorDAO;

import java.util.List;

public class ProveedorController {
    private static final ProveedorDAO PROVEEDORDAO = new ProveedorDAO();

    public static List<Proveedor> getAllProveedores() {
        return PROVEEDORDAO.getAllProveedores();
    }

    public static Proveedor getProveedorById(long idProveedor) {
        return PROVEEDORDAO.getProveedorById(idProveedor);
    }

    public static void insertProveedor(Proveedor proveedor) {
        PROVEEDORDAO.insertProveedor(proveedor);
    }

    public static void updateProveedor(Proveedor proveedor) {
        PROVEEDORDAO.updateProveedor(proveedor);
    }

    public static void deleteProveedor(long idProveedor) {
        PROVEEDORDAO.deleteProveedor(idProveedor);
    }
}
