package Clases.Filtro;

import java.util.Scanner;

public abstract class Filtro {
    private String marca;
    private String codigo;
    private double precio;
    private String descripcion;
    private int stock;

    public Filtro(String codigo, String descripcion, String marca, double precio, int stock) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.marca = marca;
        this.precio = precio;
        this.stock = stock;
    }

    public abstract void mostrarInfo();

    public String getMarca() { return marca; }


    public String getCodigo() { return codigo; }
    public void setCodigo(String codigo) { this.codigo = codigo; }

    public double getPrecio() { return precio; }
    public void setPrecio(double precio) { this.precio = precio; }

    public String getDescripcion() { return descripcion; }

    public int getStock() { return stock; }
    public void setStock(int stock) { this.stock = stock; }

}