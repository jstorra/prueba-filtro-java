package jstorra.view;

import jstorra.model.inventario.Inventario;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static final Scanner SCANNER = new Scanner(System.in);
    public static void main(String[] args) {
        while (true) {
            System.out.println("""
                
                --------- MENU PRINCIPAL ---------
                
                Escoge la seccion a gestionar:
                1. Productos
                2. Categorias
                3. Proveedores
                4. Inventarios
                0. Salir
                """);
            System.out.print("Opcion: ");
            try {
                int opcion = SCANNER.nextInt();
                SCANNER.nextLine();

                if (opcion == 0){
                    System.out.println("\nSaliendo...");
                    break;
                } else if (opcion == 1) {
                    ProductoView.initAppProducto();
                } else if (opcion == 2) {
                    CategoriaView.initAppCategoria();
                } else if (opcion == 3) {
                    ProveedorView.initAppProveedor();
                } else if (opcion == 4) {
                    InventarioView.initAppInventario();
                } else {
                    throw new Exception("\nError: La opcion ingresada no es valida.");
                }
            } catch (InputMismatchException e) {
                SCANNER.nextLine();
                System.out.println("\nError: El caracter ingresado no es valido.");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
