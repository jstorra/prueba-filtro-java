package jstorra.view;

import jstorra.model.categoria.CategoriaManagement;

import java.util.InputMismatchException;
import java.util.Scanner;

public class CategoriaView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void initAppCategoria() {
        while (true) {
            System.out.println("""
                                        
                    --------- GESTIONAR CATEGORIAS ---------
                                    
                    Escoge la accion a realizar:
                    1. Agregar categoria
                    2. Actualizar categoria
                    3. Eliminar categoria
                    4. Ver categorias
                    0. << Volver al menu
                    """);
            System.out.print("Opcion: ");
            try {
                int opcion = SCANNER.nextInt();
                SCANNER.nextLine();

                if (opcion == 0) {
                    System.out.println("\nVolviendo...");
                    break;
                } else if (opcion == 1) {
                    CategoriaManagement.agregarCategoria();
                } else if (opcion == 2) {
                    CategoriaManagement.actualizarCategoria();
                } else if (opcion == 3) {
                    CategoriaManagement.eliminarCategoria();
                } else if (opcion == 4) {
                    CategoriaManagement.mostrarCategorias();
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
