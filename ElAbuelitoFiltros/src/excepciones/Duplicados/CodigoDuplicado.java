package excepciones.Duplicados;

public class CodigoDuplicado extends RuntimeException {
    public CodigoDuplicado(String message) {
        super(message);
    }
}
