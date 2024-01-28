package jstorra.model.proveedor;

import jstorra.controller.ProveedorController;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ProveedorManagament {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static List<Proveedor> proveedores;
    private static boolean existe;

    public static void agregarProveedor() throws Exception {
        System.out.println("\n--------- AGREGAR PROVEEDOR ---------\n");

        System.out.print("Ingresa el nombre del proveedor: ");
        String nombreProveedor = SCANNER.nextLine();

        if (nombreProveedor.isEmpty())
            throw new Exception("\nError: El nombre no debe estar vacio.");

        proveedores = ProveedorController.getAllProveedores();

        existe = proveedores.stream().anyMatch(proveedor -> proveedor.getNombreProveedor().equalsIgnoreCase(nombreProveedor));

        if (existe)
            throw new Exception("\nError: El proveedor ya existe.");

        Proveedor proveedor = new Proveedor();
        proveedor.setNombreProveedor(nombreProveedor);

        ProveedorController.insertProveedor(proveedor);
    }

    public static void actualizarProveedor() {
        try {
            proveedores = ProveedorController.getAllProveedores();

            if (proveedores.isEmpty())
                throw new Exception("\nMensaje: No hay proveedores para actualizar.");

            System.out.println("\n--------- ACTUALIZAR PROVEEDOR ---------\n");

            proveedores.forEach(proveedor -> System.out.println(proveedor.getIdProveedor() + ". " + proveedor.getNombreProveedor()));

            System.out.print("\nIngresa el ID del proveedor a actualizar: ");
            int idProveedor = SCANNER.nextInt();
            SCANNER.nextLine();

            existe = proveedores.stream().anyMatch(proveedor -> proveedor.getIdProveedor() == idProveedor);

            if (!existe)
                throw new Exception("\nError: El proveedor no existe.");

            Proveedor proveedorActualizar = ProveedorController.getProveedorById(idProveedor);

            System.out.println("\nAnterior nombre: " + proveedorActualizar.getNombreProveedor());
            System.out.print("Nuevo nombre: ");
            String nuevoNombreProveedor = SCANNER.nextLine();

            if (nuevoNombreProveedor.isEmpty())
                throw new Exception("\nError: El nombre no debe estar vacio.");

            existe = proveedores.stream().anyMatch(proveedor -> proveedor.getNombreProveedor().equalsIgnoreCase(nuevoNombreProveedor));

            if (existe)
                throw new Exception("\nError: El proveedor ya existe.");

            proveedorActualizar.setNombreProveedor(nuevoNombreProveedor);

            ProveedorController.updateProveedor(proveedorActualizar);
        } catch (InputMismatchException e) {
            SCANNER.nextLine();
            System.out.println("\nError: El caracter ingresado no es valido.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarProveedor() {

    }

    public static void mostrarProveedores() {

    }

}
