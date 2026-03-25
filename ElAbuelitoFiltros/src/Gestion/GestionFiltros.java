package Gestion;
import Clases.Empresa;
import Clases.Filtro.Filtro;
import Clases.Filtro.FiltroAceite.FiltroDeAceite;
import Clases.Filtro.FiltroAceite.FiltroDeAceiteUEco;
import Clases.Filtro.FiltroAceite.FiltroDeAceiteUSellada;
import Clases.Filtro.FiltroAire.FiltroDeAire;
import Clases.Filtro.FiltroAire.FiltroDeAirePanel;
import Clases.Filtro.FiltroAire.FiltroDeAireRedondo;
import Clases.Filtro.FiltroDeCombustible;
import Clases.Filtro.FiltroDeHabitaculo;
import Validadores.ValidadorMaestro;
import excepciones.Duplicados.CodigoDuplicado;
import excepciones.ConfirmacionFallida;
import excepciones.OpcInvalida;
import excepciones.ValorNegativo;

import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionFiltros {

    private Scanner read;

    public GestionFiltros(Scanner read) {
        this.read = read;
    }

    public void gestionar(Empresa miEmpresa) {
        boolean volver = false;
        while (!volver) {
            System.out.println("""
                 ===================================================
                 |                GESTION DE FILTROS               |
                 ===================================================
                 | (1): Agregar Filtro Nuevo al Inventario.        |
                 | (2): Consultar Inventario Completo.             |
                 | (3): Consultar Inventario segun su Categoría.   |
                 | (4): Modificar Stock o Precio de un Filtro.     |
                 | (5): Eliminar un Filtro.                        |
                 | (6): Volver al Menú Principal.                  |
                 ===================================================""");
            System.out.print("->");
            try {
                int opc = read.nextInt();
                read.nextLine();

                ValidadorMaestro.validarRango(opc, 1, 6);

                switch (opc) {
                    case 1:
                        agregarFiltro(miEmpresa);
                        break;
                    case 2:
                        miEmpresa.consultarInventario();
                        break;
                    case 3:
                        consultarPorCategoria(miEmpresa);
                        break;
                    case 4:
                        modificarFiltro(miEmpresa);
                        break;
                    case 5:
                        eliminarFiltro(miEmpresa);
                        break;
                    case 6:
                        volver = true;
                        break;
                }

            } catch (OpcInvalida e) {
                System.out.println("Error: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                read.nextLine();
            }
        }
    }

    private void agregarFiltro(Empresa miEmpresa) {
        int opcTipo = 0;
        do {

            System.out.println("""
                    -----------------------------------------------------------
                    |----------------- Agregar Nuevo Filtro ------------------|
                    |         ¿Qué tipo de filtro desea agregar?              |
                    |_________________________________________________________|
                    | (1): Filtro de Aceite (Unidad Sellada).                 |
                    | (2): Filtro de Aceite (Unidad Ecológica).               |
                    | (3): Filtro de Aire (Panel).                            |
                    | (4): Filtro de Aire (Redondo).                          |
                    | (5): Filtro de Combustible.                             |
                    | (6): Filtro de Habitáculo.                              |
                    | (7): Cancelar.                                          |
                    -----------------------------------------------------------""");
            System.out.print("->");

            try {

                opcTipo = read.nextInt();
                read.nextLine();

                if (opcTipo==7) {
                    return;
                }

                ValidadorMaestro.validarRango(opcTipo, 1, 7);

                Filtro filtroNuevo = null;

                System.out.print("Código: ");
                String codigo = read.nextLine();
                ValidadorMaestro.validarCodigoUnico(codigo, miEmpresa);

                System.out.print("Marca: ");
                String marca = read.nextLine();

                System.out.print("Descripción: ");
                String desc = read.nextLine();

                System.out.print("Precio: ");
                double precio = read.nextDouble();
                ValidadorMaestro.validarNumeroNoNegativo(precio);

                System.out.print("Stock inicial: ");
                int stock = read.nextInt();
                ValidadorMaestro.validarNumeroNoNegativo(stock);

                read.nextLine();

                switch (opcTipo) {
                    case 1: // Aceite Unidad Sellada
                        System.out.print("Dimensiones (ej: 76x120mm): ");
                        String dimSellada = read.nextLine();
                        System.out.print("Tipo de Rosca (ej: M20x1.5): ");
                        String rosca = read.nextLine();
                        filtroNuevo = new FiltroDeAceiteUSellada(codigo, desc, marca,
                                precio, stock, dimSellada, rosca);
                        break;

                    case 2: // Aceite UEco
                        System.out.print("Dimensiones (ej: 60x150mm): ");
                        String dimEco = read.nextLine();
                        System.out.print("Material (ej: Papel Sintético): ");
                        String material = read.nextLine();
                        filtroNuevo = new FiltroDeAceiteUEco(codigo, desc, marca,
                                precio, stock, dimEco, material);
                        break;

                    case 3: // Aire Panel
                        System.out.print("Tipo de Aire (ej: Rígido, Flexible): ");
                        String tipoAirePanel = read.nextLine();
                        System.out.print("Dimensiones (ej: 20x30cm): ");
                        String dimPanel = read.nextLine();
                        filtroNuevo = new FiltroDeAirePanel(codigo, desc, marca,
                                precio, stock, tipoAirePanel, dimPanel);
                        break;

                    case 4: // Aire Redondo
                        System.out.print("Tipo de Aire (ej: Con Malla Metálica): ");
                        String tipoAireRedondo = read.nextLine();
                        System.out.print("Dimensiones (ej: 15x20cm): ");
                        String dimRedondo = read.nextLine();
                        filtroNuevo = new FiltroDeAireRedondo(codigo, desc, marca,
                                precio, stock, tipoAireRedondo, dimRedondo);
                        break;

                    case 5: // Combustible
                        System.out.print("Dimensiones (ej: 10x15cm): ");
                        String dimComb = read.nextLine();
                        System.out.print("Tipo de Filtrado (ej: Inyección, Carburador): ");
                        String tipoComb = read.nextLine();
                        filtroNuevo = new FiltroDeCombustible(codigo, desc, marca,
                                precio, stock, dimComb, tipoComb);
                        break;

                    case 6: // Habitáculo
                        System.out.print("Dimensiones (ej: 25x30cm): ");
                        String dimHab = read.nextLine();
                        System.out.print("Tipo de Filtrado (ej: Carbón Activado, Simple): ");
                        String tipoHab = read.nextLine();
                        filtroNuevo = new FiltroDeHabitaculo(codigo, desc, marca,
                                precio, stock, dimHab, tipoHab);
                        break;

                }
                miEmpresa.agregarFiltro(filtroNuevo);

            } catch (ValorNegativo | CodigoDuplicado | OpcInvalida e) {
                System.out.println("Error: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Error al ingresar datos. Operación cancelada.");
                read.nextLine();
            }

        } while (opcTipo != 0);
    }

    private void consultarPorCategoria(Empresa miEmpresa) {
        System.out.println("""
            -------------------------------------------------
            ------------ Consultar por Categoría ------------
            | (1): Mostrar SÓLO Filtros de Aceite.          |
            | (2): Mostrar SÓLO Filtros de Aire.            |
            | (3): Mostrar SÓLO Filtros de Combustible.     |
            | (4): Mostrar SÓLO Filtros de Habitáculo.      |
            | (5): Volver.                                  |
            -------------------------------------------------""");
        System.out.print("->");
        try {
            int opc = read.nextInt();
            read.nextLine();

            ValidadorMaestro.validarRango(opc, 1, 5);

            System.out.println("\n-------------------------------------------------");
            System.out.println("------------ RESULTADOS DE LA CONSULTA ------------");
            int contador = 0;

            for (Filtro f : miEmpresa.getFiltros()) {
                boolean coincide = false;
                switch (opc) {
                    case 1:
                        if (f instanceof FiltroDeAceite) coincide = true;
                        break;
                    case 2:
                        if (f instanceof FiltroDeAire) coincide = true;
                        break;
                    case 3:
                        if (f instanceof FiltroDeCombustible) coincide = true;
                        break;
                    case 4:
                        if (f instanceof FiltroDeHabitaculo) coincide = true;
                        break;
                    case 5:
                        return;
                }

                if (coincide) {
                    f.mostrarInfo();
                    contador++;
                }
            }

            if (contador == 0) {
                System.out.println("No se encontraron filtros de esa categoría.");
            }
            System.out.println("-------------------------------------------------");

        } catch (OpcInvalida e) {
            System.out.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un número válido.");
            read.nextLine();
        }
    }

    private void modificarFiltro(Empresa miEmpresa) {
        System.out.println("Ingrese el CÓDIGO del filtro a modificar: ");
        System.out.print("->");
        String codigo = read.nextLine();

        Filtro filtroAModificar = null;
        for (Filtro f : miEmpresa.getFiltros()) {
            if (f.getCodigo().equalsIgnoreCase(codigo)) {
                filtroAModificar = f;
                break;
            }
        }

        if (filtroAModificar == null) {
            System.out.println("Error: No se encontró ningún filtro con ese código.");
            return;
        }

        try {
            System.out.println("Filtro encontrado:");
            filtroAModificar.mostrarInfo();
            System.out.println("¿Qué desea modificar?");
            System.out.println("(1): Precio");
            System.out.println("(2): Stock");
            System.out.print("->");
            int opcMod = read.nextInt();
            read.nextLine();
            ValidadorMaestro.validarRango(opcMod, 1, 2);


            if (opcMod == 1) {
                System.out.print("Ingrese el nuevo precio (actual: $" + filtroAModificar.getPrecio() + "): ");
                System.out.print("->");
                double nuevoPrecio = read.nextDouble();
                ValidadorMaestro.validarNumeroNoNegativo(nuevoPrecio);
                filtroAModificar.setPrecio(nuevoPrecio);
                System.out.println("Precio actualizado.");
            } else if (opcMod == 2) {
                System.out.print("Ingrese el nuevo stock (actual: " + filtroAModificar.getStock() + "): ");
                System.out.print("->");
                int nuevoStock = read.nextInt();
                ValidadorMaestro.validarNumeroNoNegativo(nuevoStock);
                filtroAModificar.setStock(nuevoStock);
                System.out.println("Stock actualizado.");
            } else {
                System.out.println("Opción no válida.");
            }
        } catch (ValorNegativo | OpcInvalida e) {
            System.out.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar datos. Operación cancelada.");
            read.nextLine();
        }
    }

    private void eliminarFiltro(Empresa miEmpresa) {
        try {
            System.out.print("Ingrese el CÓDIGO del filtro a eliminar: ");
            System.out.print("->");
            String codigo = read.nextLine();

            Filtro filtroAEliminar = null;
            for (Filtro f : miEmpresa.getFiltros()) {
                if (f.getCodigo().equalsIgnoreCase(codigo)) {
                    filtroAEliminar = f;
                    break;
                }
            }

            if (filtroAEliminar == null) {
                System.out.println("Error: No se encontró ningún filtro con ese código.");
                return;
            }

            System.out.println("Filtro encontrado:");
            filtroAEliminar.mostrarInfo();
            System.out.print("¿Está seguro que desea eliminar este filtro? (S/N): ");
            System.out.print("->");
            String confirmacion = read.nextLine();
            ValidadorMaestro.validarConfirmacion(confirmacion);

            if (confirmacion.equalsIgnoreCase("S")) {
                miEmpresa.getFiltros().remove(filtroAEliminar);
                System.out.println("Filtro eliminado correctamente.");
            }

        } catch ( ConfirmacionFallida e){
            System.out.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar datos. Operación cancelada.");
            read.nextLine();
        }
    }
}