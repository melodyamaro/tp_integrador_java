package validacion;

import excepciones.ValidacionException;

/**
 * Clase para validar entradas del usuario
 */
public class ValidadorEntradas {
    
    //Valida que un ID sea válido (mayor que 0)
    public static void validarId(int id) throws ValidacionException {
        if (id <= 0) {
            throw new ValidacionException("El ID debe ser un número positivo mayor que 0");
        }
    }
    
    //Valida que una descripción no esté vacía o sea nula
    public static void validarDescripcion(String descripcion) throws ValidacionException {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new ValidacionException("La descripción no puede estar vacía");
        }
        
        if (descripcion.length() > 150) {
            throw new ValidacionException("La descripción no puede tener más de 150 caracteres");
        }
    }
    
    //Valida que una opción del menú sea válida
    public static void validarOpcionMenu(int opcion, int[] opcionesValidas) throws ValidacionException {
        boolean esValida = false;
        for (int opcionValida : opcionesValidas) {
            if (opcion == opcionValida) {
                esValida = true;
                break;
            }
        }
        
        if (!esValida) {
            throw new ValidacionException("Opción inválida. Las opciones válidas son: " + 
                java.util.Arrays.toString(opcionesValidas));
        }
    }
    
    //Valida que una cadena represente un número entero válido
    public static int validarYParsearEntero(String entrada) throws ValidacionException {
        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            throw new ValidacionException("Debe ingresar un número entero válido");
        }
    }
}
