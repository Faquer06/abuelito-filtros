package Gestion;
import Clases.Cliente;
import Clases.Empresa;
import Clases.Pedido;
import Validadores.ValidadorMaestro;
import excepciones.ConfirmacionFallida;
import excepciones.OpcInvalida;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionPedidos {
    private Scanner read;

    public GestionPedidos(Scanner read) {
        this.read = read;
    }

    public void gestionar(Empresa miEmpresa) {
        boolean volver = false;
        while (!volver) {
            System.out.println("""
                    ---------------------------------------------------
                    --------------- GESTION DE PEDIDOS ---------------
                    | (1): Armar Nuevo Pedido.                        |
                    | (2): Consultar Historial.                       |
                    | (3): Volver al Menú Principal.                  |
                    ---------------------------------------------------""");
            System.out.print("->");

            try {
                int opc = read.nextInt();
                read.nextLine();

                ValidadorMaestro.validarRango(opc, 1, 3);

                switch (opc) {
                    case 1:
                        miEmpresa.consultarInventario();
                        armarPedido(miEmpresa);
                        break;
                    case 2:
                        consultarHistorial(miEmpresa);
                        break;
                    case 3:
                        volver = true;
                        break;
                    default:
                        System.out.println("Opción no válida.");
                }

            } catch (OpcInvalida e) {
                System.out.println("Error: " + e.getMessage());

            } catch (InputMismatchException e) {
                System.out.println("Error: Ingrese un número válido.");
                read.nextLine();
            }
        }
    }

    private void armarPedido(Empresa miEmpresa) {
        System.out.println("--- Armar Nuevo Pedido ---");
        try {

            //Selecciona Cliente
            System.out.print("Ingrese el DNI del Cliente: ");
            int dni = read.nextInt();
            read.nextLine();
            Cliente cliente = miEmpresa.buscarClientePorDni(dni);

            if (cliente == null) {
                System.out.println("Error: No se encontró ningún cliente con el DNI: " + dni);
                return;
            }

            System.out.println("Cliente seleccionado: " + cliente.getNombre() + " " + cliente.getApellido());

            //Crea el Pedido
            String fechaHoy = new SimpleDateFormat("dd/MM/yyyy").format(new Date());
            Pedido nuevoPedido = new Pedido(fechaHoy, cliente);

            System.out.println("Pedido #" + nuevoPedido.getIdPedido() + " creado. Añada productos.");

            miEmpresa.procesarFiltrosRecursivo(nuevoPedido, miEmpresa, read);

            if (nuevoPedido.getItemsDelPedido().isEmpty()) {
                System.out.println("Pedido cancelado (no se agregaron productos).");
            } else {

                System.out.println("--- Resumen del Pedido ---");
                nuevoPedido.mostrarPedido();

                System.out.print("¿Desea confirmar y registrar este pedido? (S/N): ");
                String confirmacion = read.nextLine();
                ValidadorMaestro.validarConfirmacion(confirmacion);

                if (confirmacion.equalsIgnoreCase("S")) {
                    miEmpresa.registrarNuevoPedido(nuevoPedido);
                } else {
                    System.out.println("Pedido cancelado.");
                }
            }

        } catch ( ConfirmacionFallida e){
            System.out.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un DNI válido (números).");
            read.nextLine();
        }
    }

    private void consultarHistorial(Empresa miEmpresa) {
        System.out.println("\n--- Historial de Todos los Pedidos ---");

        ArrayList<Pedido> lista = miEmpresa.getPedidos();

        if (lista.isEmpty()) {
            System.out.println("No hay pedidos registrados.");
            System.out.println("------------------------------------");
            return;
        }

        System.out.println("Total de pedidos registrados: " + lista.size());
        for (Pedido p : lista) {
            p.mostrarPedido();
        }

    }



}
