package excepciones;

public class ValorNegativo extends RuntimeException {
    public ValorNegativo(String message) {
        super(message);
    }
}
