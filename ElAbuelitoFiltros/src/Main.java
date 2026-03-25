import Clases.Empresa;
// Importamos las clases del UI
import Gestion.*;
import Validadores.ValidadorMaestro;
import excepciones.OpcInvalida;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Scanner read = new Scanner(System.in);
        int opc = 0;

        Empresa miEmpresa = new Empresa(
                "Abuelito Filtros S.A.",
                "Av. Siempreviva 742",
                "20165586556"
        );

        // Creamos las instancias de los distintos gestores del menu.
        GestionFiltros gestorFiltros = new GestionFiltros(read);
        GestionEmpleados gestorEmpleados = new GestionEmpleados(read);
        GestionPedidos gestorPedidos = new GestionPedidos(read);
        GestionClientes gestorClientes = new GestionClientes(read);

        do {
            System.out.println("""
                 ===================================================
                 |                  ABUELITO FILTROS               |
                 ===================================================
                 | (1): Mostrar Info de Empresa.                   |
                 | (2): Gestion de Filtros.                        |
                 | (3): Gestion de Clientes.                       |
                 | (4): Gestion de Empleados.                      |
                 | (5): Realizar Pedidos.                          |
                 | (6): Salir.                                     |
                 ===================================================""");
            System.out.print("->");
            // VALIDACIÓN DE ERRORES

            try {
                opc = read.nextInt();
                read.nextLine();

                ValidadorMaestro.validarRango(opc, 1, 6);

                switch (opc) {
                    case 1:
                        Thread.sleep(1500);
                        miEmpresa.mostrarInfo();
                        break;
                    case 2:
                        System.out.println("... (Llamando a Gestion de Filtros) ...");
                        Thread.sleep(1500);
                        gestorFiltros.gestionar(miEmpresa);
                        break;
                    case 3:
                        System.out.println("... (Llamando a Gestion de Clientes) ...");
                        Thread.sleep(1500);
                        gestorClientes.gestionar(miEmpresa);
                        break;
                    case 4:
                        System.out.println("... (Llamando a Gestion de Empleados) ...");
                        Thread.sleep(1500);
                        gestorEmpleados.gestionar(miEmpresa);
                        break;
                    case 5:
                        System.out.println("... (Llamando a Gestion de Pedidos) ...");
                        Thread.sleep(1500);
                        gestorPedidos.gestionar(miEmpresa);
                        break;

                    case 6:
                        System.out.println("...");
                        Thread.sleep(1500);
                        System.out.println("Ha salido de Abuelito Filtros");
                        System.out.println("Gracias por venir.");
                        Thread.sleep(1500);
                        break;
                }

            }catch (OpcInvalida e) {
                System.out.println("Error: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                read.nextLine();
            }

        } while (opc != 6);
    }
}