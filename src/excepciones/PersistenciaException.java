package excepciones;

/**
 * Excepción lanzada cuando hay errores en operaciones de persistencia
 */
public class PersistenciaException extends TareaException {
    public PersistenciaException(String mensaje) {
        super("Error de persistencia: " + mensaje);
    }
    
    public PersistenciaException(String mensaje, Throwable causa) {
        super("Error de persistencia: " + mensaje, causa);
    }
}
