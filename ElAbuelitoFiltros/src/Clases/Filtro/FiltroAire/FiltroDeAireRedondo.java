package Clases.Filtro.FiltroAire;

public class FiltroDeAireRedondo extends FiltroDeAire {
    private String dimensiones;

    public FiltroDeAireRedondo(String codigo, String descripcion, String marca, double precio, int stock, String tipoAire, String dimensiones) {
        super(codigo, descripcion, marca, precio, stock, tipoAire);
        this.dimensiones = dimensiones;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("------------------------------------------");
        System.out.println("---------- FILTRO DE AIRE REDONDO --------");
        System.out.println("  Código:       " + getCodigo());
        System.out.println("  Marca:        " + getMarca());
        System.out.println("  Descripción:  " + getDescripcion());
        System.out.println("  Dimensiones:  " + this.dimensiones);
        System.out.println("  Precio:      $" + getPrecio());
        System.out.println("  Stock:        " + getStock() + " unidades");
        System.out.println("------------------------------------------");
    }
}
