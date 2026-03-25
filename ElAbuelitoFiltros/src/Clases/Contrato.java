package Clases;
import java.util.Calendar;

public class Contrato {
    private static int proximoId = 1;
    private int idContrato;
    private int añoInicio;
    private int añoFin;
    private String tipoContrato;
    private double salarioAcordado;
    private String jornada;
    private String puesto;

    public Contrato(String tipoContrato, double salarioAcordado, String jornada, String puesto, int añoFin) {
        this.idContrato = proximoId++;
        this.añoInicio = Calendar.getInstance().get(Calendar.YEAR);
        this.añoFin = añoFin;
        this.tipoContrato = tipoContrato;
        this.salarioAcordado = salarioAcordado;
        this.jornada = jornada;
        this.puesto = puesto;
    }

    public void mostrarContrato() {
        System.out.println("------------------------------------------");
        System.out.println("--- CONTRATO Nro: " + this.idContrato + " ---");
        System.out.println("  Puesto:    " + this.puesto);
        System.out.println("  Tipo:      " + this.tipoContrato);
        System.out.println("  Jornada:   " + this.jornada);
        System.out.println("  Salario:   $" + this.salarioAcordado);
        System.out.println("  Año Inicio: " + this.añoInicio);
        System.out.println("  Año Fin:    " + this.añoFin);
        System.out.println("------------------------------------------");
    }
}