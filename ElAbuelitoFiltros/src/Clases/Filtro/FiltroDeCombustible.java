package Clases.Filtro;

public class FiltroDeCombustible extends Filtro{
    String dimensiones;
    String tipoDeFiltrado;

    public FiltroDeCombustible(String codigo, String descripcion, String marca, double precio, int stock, String dimensiones, String tipoDeFiltrado) {
        super(codigo, descripcion, marca, precio, stock);
        this.dimensiones = dimensiones;
        this.tipoDeFiltrado = tipoDeFiltrado;
    }

    @Override
    public void mostrarInfo() {
        System.out.println("------------------------------------------");
        System.out.println("---------- FILTRO DE COMBUSTIBLE ---------");
        System.out.println("    Código:       " + getCodigo());
        System.out.println("    Marca:        " + getMarca());
        System.out.println("    Descripción:  " + getDescripcion());
        System.out.println("    Dimensiones:  " + this.dimensiones);
        System.out.println("    Precio:       $" + getPrecio());
        System.out.println("    Tipo de filtrado: " + getTipoDeFiltrado());
        System.out.println("    Stock:        " + getStock() + " unidades");
        System.out.println("------------------------------------------");
    }
    public String getTipoDeFiltrado() {
        return tipoDeFiltrado;
    }
}
