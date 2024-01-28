package jstorra.view;

import jstorra.model.proveedor.ProveedorManagament;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ProveedorView {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void initAppProveedor() {
        while (true) {
            System.out.println("""
                                        
                    --------- GESTIONAR PROVEEDORES ---------
                                    
                    Escoge la accion a realizar:
                    1. Agregar proveedor
                    2. Actualizar proveedor
                    3. Eliminar proveedor
                    4. Ver proveedores
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
                    ProveedorManagament.agregarProveedor();
                } else if (opcion == 2){
                    ProveedorManagament.actualizarProveedor();
                } else if (opcion == 3) {
                    ProveedorManagament.eliminarProveedor();
                } else if (opcion == 4) {
                    ProveedorManagament.mostrarProveedores();
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
