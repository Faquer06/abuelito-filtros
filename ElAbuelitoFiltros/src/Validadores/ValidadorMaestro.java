package Validadores;
import Clases.Empresa;
import excepciones.*;
import excepciones.Duplicados.CodigoDuplicado;
import excepciones.Duplicados.dniDuplicado;

public class ValidadorMaestro {

    public static void validarNumeroNoNegativo(double valor) throws ValorNegativo {
        if (valor < 0) {
            throw new ValorNegativo("Ingrese un valor positivo.");
        }
    }

    public static void validarRango(double valor, int min, int max) throws OpcInvalida {
        if (valor < min || valor > max) {
            throw new OpcInvalida("Opción fuera de rango (" + min + " a " + max + ").");
        }
    }

    public static void validarConfirmacion(String respuesta) throws ConfirmacionFallida {
        if (respuesta == null || (!respuesta.equalsIgnoreCase("S") && !respuesta.equalsIgnoreCase("N"))) {
            throw new ConfirmacionFallida("Ingrese un valor de confirmacion.");
        }
    }

    public static void validarCodigoUnico(String codigo, Empresa miEmpresa) throws CodigoDuplicado {
        if (miEmpresa.buscarFiltroPorCodigo(codigo) != null) {
            throw new CodigoDuplicado("Ya existe un filtro con el código '" + codigo + "'.");
        }
    }

    public static void validarDniUnico(int DNI, Empresa miEmpresa) throws dniDuplicado {
        if (miEmpresa.buscarClientePorDni(DNI) != null) {
            throw new dniDuplicado("Ya existe un cliente con el DNI: '" + DNI + "'.");
        }
        if (miEmpresa.buscarTrabajadorPorDni(DNI) != null){
            throw new dniDuplicado("Ya existe un empleado con el DNI: '" + DNI + "'.");
        }
    }
}
