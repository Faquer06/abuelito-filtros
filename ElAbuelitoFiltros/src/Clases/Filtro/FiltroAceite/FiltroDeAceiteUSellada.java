package Clases.Filtro.FiltroAceite;

public class FiltroDeAceiteUSellada extends FiltroDeAceite {
    private String rosca;

    public FiltroDeAceiteUSellada(String codigo, String descripcion, String marca, double precio, int stock, String dimensiones, String rosca) {
        super(codigo, descripcion, marca, precio, stock, dimensiones);
        this.rosca = rosca;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("------------------------------------------");
        System.out.println("---- FILTRO DE ACEITE UNIDAD SELLADA -----");
        System.out.println("  Código:       " + getCodigo());
        System.out.println("  Marca:        " + getMarca());
        System.out.println("  Descripción:  " + getDescripcion());
        System.out.println("  Dimensiones:  " + getDimensiones());
        System.out.println("  Rosca:        " + this.rosca);
        System.out.println("  Precio:      $" + getPrecio());
        System.out.println("  Stock:        " + getStock() + " unidades");
        System.out.println("------------------------------------------");
    }
}