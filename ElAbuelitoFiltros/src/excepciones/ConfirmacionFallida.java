package excepciones;

public class ConfirmacionFallida extends RuntimeException {
    public ConfirmacionFallida(String message) {
        super(message);
    }
}
