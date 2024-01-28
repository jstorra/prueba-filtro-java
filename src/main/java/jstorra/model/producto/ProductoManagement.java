package jstorra.model.producto;

import java.util.Scanner;

public class ProductoManagement {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void agregarProducto() {
        System.out.println("--------- AGREGAR PRODUCTO ---------");

        System.out.print("Ingresa el nombre del producto: ");
        String nombreProducto = SCANNER.nextLine();

        System.out.print("Ingresa el precio del producto: ");
        double precio = SCANNER.nextDouble();
    }

    public static void actualizarProducto() {

    }

    public static void eliminarProducto() {

    }

    public static void mostrarProductos() {

    }


}
