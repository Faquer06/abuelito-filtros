package Clases;
import Clases.Filtro.Filtro;
import Clases.Filtro.FiltroAceite.FiltroDeAceiteUEco;
import Clases.Filtro.FiltroAceite.FiltroDeAceiteUSellada;
import Clases.Filtro.FiltroAire.FiltroDeAirePanel;
import Clases.Filtro.FiltroAire.FiltroDeAireRedondo;
import Clases.Filtro.FiltroDeCombustible;
import Clases.Filtro.FiltroDeHabitaculo;
import Validadores.ValidadorMaestro;
import excepciones.ValorNegativo;

import java.util.*;

public class Empresa {
    private String nombre;
    private String direccion;
    private String CUIT;
    private ArrayList<Trabajador> trabajadores;
    private ArrayList<Cliente> clientes;
    private Stack<Cliente> papeleraCliente ;
    private ArrayList<Filtro> filtros;
    private ArrayList<Pedido> pedidos;

    public Empresa(String nombre, String direccion, String CUIT) throws InterruptedException {
        this.nombre = nombre;
        this.direccion = direccion;
        this.CUIT = CUIT;
        this.trabajadores = new ArrayList<>();
        this.clientes = new ArrayList<>();
        this.papeleraCliente = new Stack<>();
        this.filtros = new ArrayList<>();
        this.pedidos = new ArrayList<>();
        this.cargarInventarioInicial();
    }

    public void agregarFiltro(Filtro filtroNuevo) {
        this.filtros.add(filtroNuevo);
        System.out.println("Filtro " + filtroNuevo.getCodigo() + " agregado al inventario.");
    }

    public void agregarCliente(Cliente clienteNuevo) {
        this.clientes.add(clienteNuevo);

        System.out.println("Cliente " + clienteNuevo.getNombre() + " agregado.");
    }

    public void agregarTrabajador(Trabajador trabajadorNuevo) {
        this.trabajadores.add(trabajadorNuevo);
        System.out.println("Trabajador " + trabajadorNuevo.getNombre() + " registrado.");
    }

    public void mostrarInfo() {
        System.out.println("==============================================");
        System.out.println("======= EMPRESA: " + this.nombre + " =======");
        System.out.println("  CUIT: " + this.CUIT);
        System.out.println("  Dirección: " + this.direccion);
        System.out.println("---------------- Estadísticas ----------------");
        System.out.println("  Productos en Inventario: " + this.filtros.size());
        System.out.println("  Clientes Registrados: " + this.clientes.size());
        System.out.println("  Empleados: " + this.trabajadores.size());
        System.out.println("  Pedidos Realizados: " + this.pedidos.size());
        System.out.println("==============================================");
    }

    private void cargarInventarioInicial() throws InterruptedException {
        System.out.println("Cargando inventario inicial de filtros...");
        Thread.sleep(1500);
        Filtro f1 = new FiltroDeAceiteUEco("AC-101", "Filtro Aceite Corsa", "Bosch", 15.50, 50, "76x120mm", "Carbon Activado");
        this.agregarFiltro(f1);
        Thread.sleep(300);
        Filtro f2 = new FiltroDeAceiteUSellada("AC-102", "Filtro Aceite Gol", "Mann", 18.00, 40, "80x100mm", "M20x1.5");
        this.agregarFiltro(f2);
        Thread.sleep(300);
        Filtro f3 = new FiltroDeAirePanel("BD-201", "Filtro Aire Clio", "Fram", 16.99, 35, "Trapezoidal", "49x150x289mm");
        this.agregarFiltro(f3);
        Thread.sleep(300);
        Filtro f4 = new FiltroDeAireRedondo("R2-D2", "Filtro Aire Gol", "Fram", 14.5, 66, "Cilindrico", "ø35mm Ø70mm h:200mm");
        this.agregarFiltro(f4);
        Thread.sleep(300);
        Filtro f5 = new FiltroDeCombustible("CE-432", "Filtro Combustible Vento", "Bosch", 21.00, 25, "10x15cm", "Inyección");
        this.agregarFiltro(f5);
        Thread.sleep(300);
        Filtro f6 = new FiltroDeHabitaculo("DF-528", "Filtro Habitáculo Taos", "Mann-Filter", 19.91, 20, "25x30cm", "Papel");
        this.agregarFiltro(f6);
        Thread.sleep(300);
    }

    public void consultarInventario() {
        System.out.println("===================================================");
        System.out.println("========= CONSULTA DE INVENTARIO COMPLETO =========");
        if (this.filtros.isEmpty()) {
            System.out.println("No hay productos en el inventario.");
            System.out.println("=====================================");
            return;
        }
        System.out.println("Total de productos en inventario: " + this.filtros.size());
        System.out.println("---------------------------------------");
        for (Filtro f : this.filtros) {
            f.mostrarInfo();
        }
        System.out.println("===================================================");
    }

    public Cliente buscarClientePorDni(int dni) {
        for (Cliente c : this.clientes) {
            if (c.getDni() == dni) {
                return c;
            }
        }
        return null;
    }

    public Trabajador buscarTrabajadorPorDni(int dni) {
        for (Trabajador t : this.trabajadores) {
            if (t.getDNI() == dni) {
                return t;
            }
        }
        return null;
    }

    public Filtro buscarFiltroPorCodigo(String codigo) {
        for (Filtro f : this.filtros) {
            if (f.getCodigo().equalsIgnoreCase(codigo)) {
                return f;
            }
        }
        return null;
    }

    public void registrarNuevoPedido(Pedido nuevoPedido) {
        this.pedidos.add(nuevoPedido); // Simplemente lo agrega a la lista
        System.out.println("Pedido #" + nuevoPedido.getIdPedido() + " registrado.");
    }

    public void procesarFiltrosRecursivo(Pedido nuevoPedido, Empresa miEmpresa, Scanner read) {
        try {
            System.out.print("Ingrese el código del filtro (o 'FIN'): ");
            String codigo = read.nextLine();

            if (codigo.equalsIgnoreCase("FIN")) return;

            Filtro filtro = miEmpresa.buscarFiltroPorCodigo(codigo);
            if (filtro == null) {
                System.out.println("Código inválido.");
                return;
            } else {
                System.out.print("Ingrese la cantidad deseada: ");
                int cantidad = read.nextInt();
                read.nextLine();
                ValidadorMaestro.validarNumeroNoNegativo(cantidad);

                if (filtro.getStock() >= cantidad) {
                    nuevoPedido.agregarFiltro(filtro, cantidad);
                    System.out.println("Añadido correctamente.");
                } else {
                    System.out.println("Stock insuficiente. Solo hay " + filtro.getStock() + " unidades.");
                }
            }
            // Llamada recursiva
            procesarFiltrosRecursivo(nuevoPedido, miEmpresa, read);
        }catch (ValorNegativo e){
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void eliminarCliente(Cliente cliente) {
        clientes.remove(cliente);
    }

    public ArrayList<Trabajador> getTrabajadores() {
        return trabajadores;
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public Stack<Cliente> getPapeleraCliente() {
        return papeleraCliente;
    }

    public ArrayList<Filtro> getFiltros() {
        return filtros;
    }

    public ArrayList<Pedido> getPedidos() {
        return pedidos;
    }

}