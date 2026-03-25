package Gestion;
import Clases.Empresa;
import Clases.Trabajador;
import Clases.Contrato;
import Validadores.ValidadorMaestro;
import excepciones.ConfirmacionFallida;
import excepciones.OpcInvalida;
import excepciones.Duplicados.dniDuplicado;
import excepciones.ValorNegativo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.Scanner;

public class GestionEmpleados {

    private Scanner read;

    public GestionEmpleados(Scanner read) {
        this.read = read;
    }

    public void gestionar(Empresa miEmpresa) {
        boolean volver = false;
        while (!volver) {
            System.out.println("""
                    ------------------------------------------
                    ------- MÓDULO GESTIÓN DE EMPLEADOS ------
                    | (1): Registrar Nuevo Empleado.         |
                    | (2): Consultar Lista de Empleados.     |
                    | (3): Despedir Empleado.                |
                    | (4): Volver.
                    ------------------------------------------""");
            System.out.print("-> ");

            try {
                int opc = read.nextInt();
                read.nextLine();

                ValidadorMaestro.validarRango(opc, 1, 4);
                switch (opc) {
                    case 1:
                        registrarNuevoEmpleado(miEmpresa);
                        break;
                    case 2:
                        consultarEmpleados(miEmpresa);
                        break;
                    case 3:
                        despedirEmpleado(miEmpresa);
                        break;
                    case 4:
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

    private void registrarNuevoEmpleado(Empresa miEmpresa) {
        System.out.println("--- Registro de Nuevo Empleado ---");
        try {

            System.out.print("Nombre: ");
            String nombre = read.nextLine();
            System.out.print("Apellido: ");
            String apellido = read.nextLine();
            System.out.print("DNI: ");
            int dni = read.nextInt();
            read.nextLine();
            ValidadorMaestro.validarDniUnico(dni, miEmpresa);
            ValidadorMaestro.validarNumeroNoNegativo(dni);

            // --- Pedir datos del Contrato ---
            System.out.print("Puesto (ej: Vendedor): ");
            String puesto = read.nextLine();
            System.out.print("Jornada (ej: Full-time, Part-time): ");
            String jornada = read.nextLine();
            System.out.print("Tipo de Contrato (ej: Permanente, Temporal): ");
            String tipoContrato = read.nextLine();
            System.out.print("Salario Acordado (ej: 150000.0): ");
            double salario = read.nextDouble();
            ValidadorMaestro.validarNumeroNoNegativo(salario);

            // ---Pedir el año de finalización ---
            int añoActual = Calendar.getInstance().get(Calendar.YEAR);
            int añoFin = 0;
            int añoMax = añoActual+10;
            while (añoFin < añoActual || añoFin > añoMax) {
                System.out.print("Año de Finalización del Contrato (no menor a " + añoActual + ", ni mayor a 10 años ): ");
                añoFin = read.nextInt();
                read.nextLine();

                if (añoFin < añoActual) {
                    System.out.println("Error: El año de finalización no puede ser en el pasado.");
                }
                else if(añoFin>añoMax){
                    System.out.println("Error: El contrato no puede ser mayor a 10 años.");
                }
            }

            Contrato nuevoContrato = new Contrato(tipoContrato, salario, jornada, puesto, añoFin);
            Trabajador nuevoTrabajador = new Trabajador(nombre, apellido, dni, nuevoContrato);
            miEmpresa.agregarTrabajador(nuevoTrabajador);
            System.out.println("\n¡Trabajador registrado exitosamente!");
            nuevoTrabajador.mostrarTrabajador();

        }catch (ValorNegativo | dniDuplicado e){
            System.out.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Error al ingresar datos. Operación cancelada.");
            read.nextLine();
        }
    }

    private void consultarEmpleados(Empresa miEmpresa) {
        System.out.println("\n--- Lista de Empleados Registrados ---");

        ArrayList<Trabajador> lista = miEmpresa.getTrabajadores();

        if (lista.isEmpty()) {
            System.out.println("No hay empleados registrados en el sistema.");
            System.out.println("----------------------------------------");
            return;
        }

        System.out.println("Total de empleados: " + lista.size());

        for (Trabajador t : lista) {
            t.mostrarTrabajador();
        }
        System.out.println("----------------------------------------");
    }

    private void despedirEmpleado(Empresa miEmpresa) {
        System.out.println("--- Despedir Empleado ---");
        try {
            System.out.print("Ingrese el DNI del empleado a Despedir: ");
            int dni = read.nextInt();
            read.nextLine();

            Trabajador trabajadorADespedir = null;
            for (Trabajador t : miEmpresa.getTrabajadores()) {
                if (t.getDNI() == dni) {
                    trabajadorADespedir = t;
                    break;
                }
            }

            if (trabajadorADespedir == null) {
                System.out.println("Error: No se encontró ningún empleado con ese DNI.");
                return;
            }

            System.out.println("Empleado encontrado:");
            trabajadorADespedir.mostrarTrabajador();
            System.out.print("¿Está seguro que desea DESPEDIR este empleado? (S/N): ");
            String confirmacion = read.nextLine();
            ValidadorMaestro.validarConfirmacion(confirmacion);

            if (confirmacion.equalsIgnoreCase("S")) {

                miEmpresa.getTrabajadores().remove(trabajadorADespedir);
                System.out.println("Empleado DESPEDIDO correctamente.");
            }

        } catch ( ConfirmacionFallida e){
            System.out.println("Error: " + e.getMessage());

        } catch (InputMismatchException e) {
            System.out.println("Error: Ingrese un DNI válido (números).");
            read.nextLine();
        }
    }
}