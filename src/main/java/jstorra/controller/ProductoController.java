package jstorra.controller;

import jstorra.model.producto.Producto;
import jstorra.model.producto.ProductoDAO;

import java.util.List;

public class ProductoController {
    private static final ProductoDAO PRODUCTODAO = new ProductoDAO();

    public static List<Producto> getAllProductos() {
        return PRODUCTODAO.getAllProductos();
    }

    public static Producto getProductoById(long idProducto) {
        return PRODUCTODAO.getProductoById(idProducto);
    }

    public static void insertProducto(Producto producto) {
        PRODUCTODAO.insertProducto(producto);
    }

    public static void updateProducto(Producto producto) {
        PRODUCTODAO.updateProducto(producto);
    }

    public static void deleteProducto(long idProducto) {
        PRODUCTODAO.deleteProducto(idProducto);
    }
}
