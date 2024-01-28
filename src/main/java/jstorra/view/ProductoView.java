package jstorra.view;

import jstorra.model.producto.ProductoManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProductoView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void initAppProducto() {
        while (true) {
            System.out.println("""
                    --------- GESTIONAR PRODUCTOS ---------
                                    
                    Escoge la accion a realizar:
                    1. Agregar producto
                    2. Actualizar producto
                    3. Eliminar producto
                    4. Ver productos
                    0. << Volver al menu
                    """);
            System.out.println("Opcion: ");
            try {
                int opcion = SCANNER.nextInt();
                SCANNER.nextLine();

                if (opcion == 0) {
                    System.out.println("\nVolviendo...");
                    break;
                } else if (opcion == 1) {
                    ProductoManagement.agregarProducto();
                } else if (opcion == 2) {
                    ProductoManagement.actualizarProducto();
                } else if (opcion == 3) {
                    ProductoManagement.eliminarProducto();
                } else if (opcion == 4) {
                    ProductoManagement.mostrarProductos();
                } else {
                    throw new Exception("\nError: La opcion ingresada no es valida.");
                }

            } catch (InputMismatchException e) {
                System.out.println("\nError: El caracter ingresado no es valido.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
