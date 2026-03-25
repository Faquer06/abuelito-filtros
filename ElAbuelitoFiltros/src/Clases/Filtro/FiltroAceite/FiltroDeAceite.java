package Clases.Filtro.FiltroAceite;
import Clases.Filtro.Filtro;

public abstract class FiltroDeAceite extends Filtro {
    private String dimensiones;

    public FiltroDeAceite(String codigo, String descripcion, String marca, double precio, int stock, String dimensiones) {
        super(codigo, descripcion, marca, precio, stock);
        this.dimensiones = dimensiones;
    }

    public String getDimensiones() {
        return dimensiones;
    }
}