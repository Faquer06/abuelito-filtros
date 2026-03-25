package Clases;

public class Cliente {
    private String nombre;
    private String apellido;
    private String email;
    private int dni;
    private String telefono;
    private String direccion;

    public Cliente(String nombre, String apellido, String email, int dni, String telefono, String direccion) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public void mostrarCliente() {
        System.out.println("------------------------------------------");
        System.out.println("--- CLIENTE: " + nombre + " " + apellido + " ---");
        System.out.println("  DNI:       " + this.dni);
        System.out.println("  Email:     " + this.email);
        System.out.println("  Teléfono:  " + this.telefono);
        System.out.println("  Dirección: " + this.direccion);
        System.out.println("------------------------------------------");
    }
    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public int getDni() {
        return dni;
    }
}