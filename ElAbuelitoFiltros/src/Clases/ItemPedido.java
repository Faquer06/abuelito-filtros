package Clases;
import Clases.Filtro.Filtro;

public class ItemPedido {
    private Filtro filtro;
    private int cantidad;

    public ItemPedido(Filtro filtro, int cantidad) {
        this.filtro = filtro;
        this.cantidad = cantidad;
    }

    public Filtro getFiltro() { return filtro; }
    public int getCantidad() { return cantidad; }
    public double getSubtotal() { return filtro.getPrecio() * cantidad; }
}
