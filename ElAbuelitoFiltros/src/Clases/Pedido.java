package Clases;
import Clases.Filtro.Filtro;
import java.util.ArrayList;

public class Pedido {
    private static int proximoId =1;
    private int idPedido;
    private String fechaPedido;
    private Cliente cliente;
    private ArrayList<ItemPedido> itemsDelPedido;


    public Pedido(String fechaPedido, Cliente cliente) {
        this.idPedido = proximoId;
        proximoId++;
        this.fechaPedido = fechaPedido;
        this.cliente = cliente;
        this.itemsDelPedido = new ArrayList<>();
    }

    public double calcularTotal() {
        double total = 0.0;
        for (ItemPedido item : itemsDelPedido) {
            total += item.getSubtotal();
        }
        return total;
    }

    public void agregarFiltro(Filtro filtro, int cantidad) {
        itemsDelPedido.add(new ItemPedido(filtro, cantidad));
        filtro.setStock(filtro.getStock() - cantidad);
        System.out.println("Añadido: " +filtro.getCodigo()+", "+ filtro.getDescripcion()+".");
    }

    public void mostrarPedido() {
        double total=0;

        System.out.println("=============================================================");
        System.out.println("============= PEDIDO Nro: " + this.idPedido + " =============");
        System.out.println("Fecha: " + this.fechaPedido);
        System.out.println("Cliente: " + this.cliente.getNombre() + " " + this.cliente.getApellido());
        System.out.println("DNI: " + this.cliente.getDni());
        System.out.println("--------------------------------------------------------------");
        System.out.println("Productos: " );

        if (this.itemsDelPedido.isEmpty()) {
            System.out.println("  (El pedido está vacío)");
        }
        else {
            for (ItemPedido item : itemsDelPedido) {
                Filtro filtro = item.getFiltro();
                int cantidad = item.getCantidad();

                System.out.println("Código: " + filtro.getCodigo());
                System.out.println("Descripción: " + filtro.getDescripcion());
                System.out.println("Cantidad pedida: " + cantidad);
                System.out.println("--------------------------------------------------------------");
            }
            System.out.println("==============================================================");
            System.out.println("Total a Pagar: $" + this.calcularTotal());
            System.out.println("==============================================================");
        }
    }

    public int getIdPedido() {
        return idPedido;
    }

    public ArrayList<ItemPedido> getItemsDelPedido() {
        return itemsDelPedido;
    }

    public Cliente getCliente() {
        return cliente;
    }
}
