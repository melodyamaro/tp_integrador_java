package excepciones;

/**
 * Excepción lanzada cuando hay errores de validación en los datos de entrada
 */
public class ValidacionException extends TareaException {
    public ValidacionException(String mensaje) {
        super("Error de validación: " + mensaje);
    }
}
