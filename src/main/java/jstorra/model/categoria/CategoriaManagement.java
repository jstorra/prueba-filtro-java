package jstorra.model.categoria;

import jstorra.controller.CategoriaController;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class CategoriaManagement {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static List<Categoria> categorias;
    private static boolean existe;

    public static void agregarCategoria() throws Exception {
        System.out.println("\n--------- AGREGAR CATEGORIA ---------\n");

        System.out.print("Ingresa el nombre de la categoria: ");
        String nombreCategoria = SCANNER.nextLine();

        if (nombreCategoria.isEmpty())
            throw new Exception("\nError: El nombre no debe estar vacio.");

        categorias = CategoriaController.getAllCategorias();

        existe = categorias.stream().anyMatch(categoria -> categoria.getNombreCategoria().equalsIgnoreCase(nombreCategoria));

        if (existe)
            throw new Exception("\nError: La categoria ya existe.");

        Categoria categoria = new Categoria();
        categoria.setNombreCategoria(nombreCategoria);

        CategoriaController.insertCategoria(categoria);
    }

    public static void actualizarCategoria() {
        try {
            categorias = CategoriaController.getAllCategorias();

            if (categorias.isEmpty())
                throw new Exception("\nMensaje: No hay categorias para actualizar.");

            System.out.println("\n--------- ACTUALIZAR CATEGORIA ---------\n");

            categorias.forEach(categoria -> System.out.println(categoria.getIdCategoria() + ". " + categoria.getNombreCategoria()));

            System.out.print("\nIngresa el ID de la categoria a actualizar: ");
            long idCategoria = SCANNER.nextLong();
            SCANNER.nextLine();

            existe = categorias.stream().anyMatch(categoria -> categoria.getIdCategoria() == idCategoria);

            if (!existe)
                throw new Exception("\nError: La categoria no existe.");

            Categoria categoriaActualizar = CategoriaController.getCategoriaById(idCategoria);

            System.out.println("\nAnterior nombre: " + categoriaActualizar.getNombreCategoria());
            System.out.print("Nuevo nombre: ");
            String nuevoNombreCategoria = SCANNER.nextLine();

            if (nuevoNombreCategoria.isEmpty())
                throw new Exception("\nError: El nombre no debe estar vacio.");

            existe = categorias.stream().anyMatch(categoria -> categoria.getNombreCategoria().equalsIgnoreCase(nuevoNombreCategoria));

            if (existe)
                throw new Exception("\nError: La categoria ya existe.");

            categoriaActualizar.setNombreCategoria(nuevoNombreCategoria);

            CategoriaController.updateCategoria(categoriaActualizar);
        } catch (InputMismatchException e) {
            SCANNER.nextLine();
            System.out.println("\nError: El caracter ingresado no es valido.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void eliminarCategoria() {
        try {
            categorias = CategoriaController.getAllCategorias();

            if (categorias.isEmpty())
                throw new Exception("\nMensaje: No hay categorias para eliminar.");

            System.out.println("\n--------- ELIMINAR CATEGORIA ---------\n");

            categorias.forEach(categoria -> System.out.println(categoria.getIdCategoria() + ". " + categoria.getNombreCategoria()));

            System.out.print("\nIngresa el ID de la categoria a eliminar: ");
            long idCategoria = SCANNER.nextLong();
            SCANNER.nextLine();

            existe = categorias.stream().anyMatch(categoria -> categoria.getIdCategoria() == idCategoria);

            if (!existe)
                throw new Exception("\nError: La categoria no existe.");

            CategoriaController.deleteCategoria(idCategoria);
        } catch (InputMismatchException e) {
            SCANNER.nextLine();
            System.out.println("\nError: El caracter ingresado no es valido.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void mostrarCategorias() throws Exception {
        categorias = CategoriaController.getAllCategorias();

        if (categorias.isEmpty())
            throw new Exception("\nMensaje: No hay categorias para mostrar.");

        System.out.println("\n--------- CATEGORIAS EXISTENTES: " + categorias.size() + " ---------\n");

        categorias.forEach(categoria -> System.out.println(categoria.getIdCategoria() + ". " + categoria.getNombreCategoria()));
    }
}
