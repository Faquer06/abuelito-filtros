package excepciones.Duplicados;

public class dniDuplicado extends RuntimeException {
    public dniDuplicado(String message) {
        super(message);
    }
}
