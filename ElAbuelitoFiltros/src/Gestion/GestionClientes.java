package Gestion;
import Clases.Cliente;
import Clases.Empresa;
import Validadores.ValidadorMaestro;
import excepciones.ConfirmacionFallida;
import excepciones.OpcInvalida;
import excepciones.Duplicados.dniDuplicado;
import excepciones.ValorNegativo;

import java.util.*;

public class GestionClientes {

    private final Scanner read;

    public GestionClientes(Scanner read) {
        this.read = read;
    }

    public void gestionar(Empresa miEmpresa){
        boolean volver = false;
        while (!volver) {
            System.out.println("""
                ---------------------------------------------------
                ------------ MÓDULO GESTIÓN DE CLIENTES -----------
                |(1): Registrar Nuevo Cliente.                    |
                |(2): Consultar Lista de Clientes.                |
                |(3): Eliminar un cliente.                        |
                |(4): Ver los clientes en la papelera             |
                |(5): Reintegrar un cliente.                      |
                |(6): Salir.                                      |
                ---------------------------------------------------""");
            System.out.print("->");
            try {
                int opc = read.nextInt();
                read.nextLine();

                ValidadorMaestro.validarRango(opc, 1, 6);

                switch (opc) {
                    case 1:
                        registrarNuevoCliente(miEmpresa);
                        break;
                    case 2:
                        consultarClientes(miEmpresa);
                        break;
                    case 3:
                        eliminarClientes(miEmpresa);
                        break;
                    case 4:
                        verPapeleraClientes(miEmpresa);
                        break;
                    case 5:
                        reintegrarCliente(miEmpresa);
                        break;
                    case 6:
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

    public void registrarNuevoCliente(Empresa miEmpresa){
        System.out.println("------------------------------------------");
        System.out.println("--- Registro de Nuevo Cliente ---");
        try {
            System.out.print("Nombre: ");
            String nombre = read.nextLine();
            System.out.print("Apellido: ");
            String apellido = read.nextLine();
            System.out.print("Email: ");
            String email = read.nextLine();
            System.out.print("DNI: ");
            int dni = read.nextInt();
            read.nextLine();
            ValidadorMaestro.validarDniUnico(dni, miEmpresa);
            ValidadorMaestro.validarNumeroNoNegativo(dni);
            System.out.print("Telefono: ");
            String telefono = read.nextLine();
            System.out.print("Direccion: ");
            String direccion = read.nextLine();

            Cliente nuevoCliente = new Cliente(nombre, apellido, email, dni, telefono, direccion);
            miEmpresa.agregarCliente(nuevoCliente);

            System.out.println("\n¡Cliente registrado exitosamente!");
            nuevoCliente.mostrarCliente();

        }catch (ValorNegativo | dniDuplicado e){
            System.out.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar datos. Operación cancelada.");
            read.nextLine();
        }
    }

    public void consultarClientes(Empresa miEmpresa){
        System.out.println("\n--- Lista de Clientes Registrados ---");

        ArrayList<Cliente> lista = miEmpresa.getClientes();

        if (lista.isEmpty()) {
            System.out.println("No hay clientes registrados en el sistema.");
            System.out.println("------------------------------------------");
            return;
        }

        System.out.println("Total de Clientes: " + lista.size());

        for (Cliente t : lista) {
            t.mostrarCliente();
        }
    }

    public void eliminarClientes (Empresa miEmpresa){
        System.out.print("Ingrese el DNI del cliente a eliminar: ");
        String dni = read.nextLine();
        try {


            Cliente cliente = miEmpresa.buscarClientePorDni(Integer.parseInt(dni));
            if (cliente == null) {
                System.out.println("El cliente con el dni (" + dni + ") no fue encontrado");

            } else {
                System.out.println("Cliente encontrado!");
                cliente.mostrarCliente();
                System.out.print("¿Está seguro que desea eliminar este cliente? (S/N): ");
                String confirmacion = read.nextLine();
                ValidadorMaestro.validarConfirmacion(confirmacion);

                if (confirmacion.equalsIgnoreCase("S")) {

                    miEmpresa.getPapeleraCliente().push(cliente);
                    miEmpresa.eliminarCliente(cliente);
                    System.out.println(" Cliente eliminado y enviado a la papelera.");
                }
            }
        }catch ( ConfirmacionFallida e) {
            System.out.println("Error: " + e.getMessage());

        }catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un DNI válido (números).");
            read.nextLine();
        }
    }

    private void verPapeleraClientes(Empresa miEmpresa) {
        Stack<Cliente> papelera = miEmpresa.getPapeleraCliente();

        if (papelera.isEmpty()) {
            System.out.println(" La papelera está vacía.");
            return;
        }

        System.out.println(" Clientes en papelera :");

        for (Cliente c : papelera) {
            c.mostrarCliente();
        }

    }

    private void reintegrarCliente(Empresa miEmpresa) {
        Stack<Cliente> papelera = miEmpresa.getPapeleraCliente();
        try {


            if (papelera.isEmpty()) {
                System.out.println(" No hay clientes para reintegrar.");
                return;
            }

            System.out.print("Ingrese el DNI del cliente a reintegrar: ");
            int dni = read.nextInt();
            read.nextLine();

            Cliente clienteAReintegrar = null;

            for (Cliente c : papelera) {
                if (c.getDni() == dni) {
                    clienteAReintegrar = c;
                }
            }
            if (clienteAReintegrar == null) {
                System.out.println(" El cliente con el DNI (" + dni + ") no está en la papelera.");
                return;
            }
            System.out.println("");
            clienteAReintegrar.mostrarCliente();
            System.out.print("¿Está seguro que desea eliminar este cliente? (S/N): ");
            String confirmacion = read.nextLine();
            ValidadorMaestro.validarConfirmacion(confirmacion);

            if (confirmacion.equalsIgnoreCase("S")) {

                papelera.remove(clienteAReintegrar);
                miEmpresa.agregarCliente(clienteAReintegrar);
                clienteAReintegrar.mostrarCliente();
            }
        }catch ( ConfirmacionFallida e) {
            System.out.println("Error: " + e.getMessage());

        }catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un DNI válido (números).");
            read.nextLine();
        }
    }
}