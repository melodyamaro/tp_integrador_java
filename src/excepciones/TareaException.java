package excepciones;

/**
 * Excepción base para errores relacionados con tareas
 */
public class TareaException extends Exception {
    public TareaException(String mensaje) {
        super(mensaje);
    }
    
    public TareaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
