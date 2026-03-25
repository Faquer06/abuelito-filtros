package Clases.Filtro.FiltroAceite;

public class FiltroDeAceiteUEco extends FiltroDeAceite {
    private String material;

    public FiltroDeAceiteUEco(String codigo, String descripcion, String marca, double precio, int stock, String dimensiones, String material) {
        super(codigo, descripcion, marca, precio, stock, dimensiones);
        this.material = material;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("------------------------------------------");
        System.out.println("------ FILTRO DE ACEITE UNIDAD ECO. ------");
        System.out.println("  Código:       " + getCodigo());
        System.out.println("  Marca:        " + getMarca());
        System.out.println("  Descripción:  " + getDescripcion());
        System.out.println("  Dimensiones:  " + getDimensiones());
        System.out.println("  Material:     " + this.material);
        System.out.println("  Precio:      $" + getPrecio());
        System.out.println("  Stock:        " + getStock() + " unidades");
        System.out.println("------------------------------------------");
    }
}