package Clases.Filtro.FiltroAire;
import Clases.Filtro.Filtro;

public abstract class FiltroDeAire extends Filtro {
    private String tipoAire;

    public FiltroDeAire(String codigo, String descripcion, String marca, double precio, int stock, String tipoAire) {
        super(codigo, descripcion, marca, precio, stock);
        this.tipoAire = tipoAire;
    }
}