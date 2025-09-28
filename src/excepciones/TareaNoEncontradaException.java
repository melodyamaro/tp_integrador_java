package excepciones;

/**
 * Excepción lanzada cuando no se encuentra una tarea con el ID especificado
 */
public class TareaNoEncontradaException extends TareaException {
    public TareaNoEncontradaException(int id) {
        super("No se encontró una tarea con el ID: " + id);
    }
}
