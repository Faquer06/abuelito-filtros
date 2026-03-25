package Clases;

public class Trabajador {

    private String nombre;
    private String apellido;
    private int DNI;
    private Contrato contrato;

    public Trabajador(String nombre, String apellido, int DNI, Contrato contrato) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.DNI = DNI;
        this.contrato = contrato;
    }

    public void mostrarTrabajador() {
        System.out.println("=============================================================");
        System.out.println("=== TRABAJADOR: " + this.nombre + " " + this.apellido + " ===");
        System.out.println("  DNI: " + this.DNI);
        if (this.contrato != null) {
            this.contrato.mostrarContrato();
        } else {
            System.out.println("  (Este trabajador no tiene un contrato asignado)");
        }
        System.out.println("=============================================================");
    }

    public String getNombre() {
        return nombre;
    }

    public int getDNI() {
        return DNI;
    }
}