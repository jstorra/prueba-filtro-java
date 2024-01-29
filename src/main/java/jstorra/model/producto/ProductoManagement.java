package jstorra.model.producto;

import jstorra.controller.CategoriaController;
import jstorra.controller.ProductoController;
import jstorra.controller.ProveedorController;
import jstorra.model.categoria.Categoria;
import jstorra.model.proveedor.Proveedor;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProductoManagement {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static List<Producto> productos;
    private static List<Categoria> categorias;
    private static List<Proveedor> proveedores;
    private static boolean existe;

    public static void agregarProducto() {
        try {
            System.out.println("\n--------- AGREGAR PRODUCTO ---------\n");

            System.out.print("Ingresa el nombre del producto: ");
            String nombreProducto = SCANNER.nextLine();

            if (nombreProducto.isEmpty())
                throw new Exception("\nError: El nombre no debe estar vacio.");

            productos = ProductoController.getAllProductos();

            existe = productos.stream().anyMatch(producto -> producto.getNombreProducto().equalsIgnoreCase(nombreProducto));

            if (existe)
                throw new Exception("\nError: El producto ya existe.");

            System.out.print("Ingresa el precio del producto: ");
            double precio = SCANNER.nextDouble();
            SCANNER.nextLine();

            if (precio < 0)
                throw new Exception("\nError: El precio no es valido.");

            categorias = CategoriaController.getAllCategorias();

            Long idCategoria;
            if (!categorias.isEmpty()) {
                categorias.forEach(categoria -> System.out.println(categoria.getIdCategoria() + ". " + categoria.getNombreCategoria()));
                System.out.print("\nIngresa el ID de la categoria: ");
                idCategoria = SCANNER.nextLong();
                SCANNER.nextLine();

                existe = categorias.stream().anyMatch(categoria -> categoria.getIdCategoria() == idCategoria);

                if (!existe)
                    throw new Exception("\nError: La categoria no existe.");
            } else {
                idCategoria = null;
            }

            proveedores = ProveedorController.getAllProveedores();

            Long idProveedor;
            if (!proveedores.isEmpty()) {
                proveedores.forEach(proveedor -> System.out.println(proveedor.getIdProveedor() + ". " + proveedor.getNombreProveedor()));
                System.out.print("\nIngresa el ID del proveedor: ");
                idProveedor = SCANNER.nextLong();
                SCANNER.nextLine();

                existe = proveedores.stream().anyMatch(proveedor -> proveedor.getIdProveedor() == idProveedor);

                if (!existe)
                    throw new Exception("\nError: El proveedor no existe.");
            } else {
                idProveedor = null;
            }

            Producto producto = new Producto();
            producto.setNombreProducto(nombreProducto);
            producto.setPrecio(precio);
            producto.setIdCategoria(idCategoria);
            producto.setIdProveedor(idProveedor);

            ProductoController.insertProducto(producto);
        } catch (InputMismatchException e) {
            SCANNER.nextLine();
            System.out.println("\nError: El caracter ingresado no es valido.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void actualizarProducto() {
        try {
            productos = ProductoController.getAllProductos();

            if (productos.isEmpty())
                throw new Exception("\nMensaje: No hay productos para eliminar.");

            System.out.println("\n--------- ACTUALIZAR PRODUCTO ---------\n");

            productos.forEach(producto -> System.out.println(
                    producto.getIdProducto() + ". " + producto.getNombreProducto()
                            + ", Categoria: " + CategoriaController.getCategoriaById(producto.getIdCategoria()).getNombreCategoria()
                            + ", Proveedor: " + ProveedorController.getProveedorById(producto.getIdProveedor()).getNombreProveedor()
            ));

            System.out.print("\nIngresa el ID del producto a actualizar: ");
            long idProducto = SCANNER.nextLong();

            existe = productos.stream().anyMatch(producto -> producto.getIdProducto() == idProducto);

            if (!existe)
                throw new Exception("\nError: El producto no existe.");

            Producto productoActualizar = ProductoController.getProductoById(idProducto);

            System.out.println("\nAntiguo nombre: " + productoActualizar.getNombreProducto());
            System.out.print("Nuevo nombre: ");
            String nuevoNombreProducto = SCANNER.nextLine();

            if (nuevoNombreProducto.isEmpty())
                throw new Exception("\nError: El nombre no debe estar vacio.");

            productos = ProductoController.getAllProductos();

            existe = productos.stream().anyMatch(producto -> producto.getNombreProducto().equalsIgnoreCase(nuevoNombreProducto));

            if (existe)
                throw new Exception("\nError: El producto ya existe.");

            System.out.println("\nAnterior precio: " + productoActualizar.getPrecio());
            System.out.print("Nuevo precio: ");
            double nuevoPrecio = SCANNER.nextDouble();
            SCANNER.nextLine();

            if (nuevoPrecio < 0)
                throw new Exception("\nError: El precio no es valido.");

            categorias = CategoriaController.getAllCategorias();

            Long nuevoIdCategoria;
            if (!categorias.isEmpty()) {
                categorias.forEach(categoria -> System.out.println(categoria.getIdCategoria() + ". " + categoria.getNombreCategoria()));
                System.out.println("\nAnterior categoria: " + CategoriaController.getCategoriaById(productoActualizar.getIdCategoria()).getNombreCategoria());
                System.out.print("Nueva categoria: ");
                nuevoIdCategoria = SCANNER.nextLong();
                SCANNER.nextLine();

                existe = categorias.stream().anyMatch(categoria -> categoria.getIdCategoria() == nuevoIdCategoria);

                if (!existe)
                    throw new Exception("\nError: La categoria no existe.");
            } else {
                nuevoIdCategoria = null;
            }

            proveedores = ProveedorController.getAllProveedores();

            Long nuevoIdProveedor;
            if (!proveedores.isEmpty()) {
                proveedores.forEach(proveedor -> System.out.println(proveedor.getIdProveedor() + ". " + proveedor.getNombreProveedor()));
                System.out.println("\nAnterior proveedor: " + ProveedorController.getProveedorById(productoActualizar.getIdProveedor()).getNombreProveedor());
                System.out.print("Nuevo proveedor: ");
                nuevoIdProveedor = SCANNER.nextLong();
                SCANNER.nextLine();

                existe = proveedores.stream().anyMatch(proveedor -> proveedor.getIdProveedor() == nuevoIdProveedor);

                if (!existe)
                    throw new Exception("\nError: El proveedor no existe.");
            } else {
                nuevoIdProveedor = null;
            }

            productoActualizar.setNombreProducto(nuevoNombreProducto);
            productoActualizar.setPrecio(nuevoPrecio);
            productoActualizar.setIdCategoria(nuevoIdCategoria);
            productoActualizar.setIdProveedor(nuevoIdProveedor);

            ProductoController.updateProducto(productoActualizar);
        } catch (InputMismatchException e) {
            SCANNER.nextLine();
            System.out.println("\nError: El caracter ingresado no es valido.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarProducto() {
        try {
            productos = ProductoController.getAllProductos();

            if (productos.isEmpty())
                throw new Exception("\nMensaje: No hay productos para eliminar.");

            System.out.println("\n--------- ELIMINAR PRODUCTO ---------\n");

            productos.forEach(producto -> System.out.println(
                    producto.getIdProducto() + ". " + producto.getNombreProducto()
                            + ", Categoria: " + CategoriaController.getCategoriaById(producto.getIdCategoria()).getNombreCategoria()
                            + ", Proveedor: " + ProveedorController.getProveedorById(producto.getIdProveedor()).getNombreProveedor()
            ));

            System.out.print("\nIngresa el ID del producto a eliminar: ");
            long idProducto = SCANNER.nextLong();

            existe = productos.stream().anyMatch(producto -> producto.getIdProducto() == idProducto);

            if (!existe)
                throw new Exception("\nError: El producto no existe.");

            ProductoController.deleteProducto(idProducto);
        } catch (InputMismatchException e) {
            SCANNER.nextLine();
            System.out.println("\nError: El caracter ingresado no es valido.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void mostrarProductos() throws Exception {
        productos = ProductoController.getAllProductos();

        if (productos.isEmpty())
            throw new Exception("\nMensaje: No hay productos para mostrar.");

        System.out.println("\n--------- PRODUCTOS EXISTENTES: " + productos.size() + " ---------\n");

        productos.forEach(producto -> System.out.println(
                producto.getIdProducto() + ". " + producto.getNombreProducto()
                + ", Categoria: " + (producto.getIdCategoria() == null ? "No tiene categoria" : CategoriaController.getCategoriaById(producto.getIdCategoria()).getNombreCategoria())
                + ", Proveedor: " + (producto.getIdProveedor() == null ? "No tiene proveedor" : ProveedorController.getProveedorById(producto.getIdProveedor()).getNombreProveedor())
        ));
    }
}
