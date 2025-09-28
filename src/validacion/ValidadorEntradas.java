package validacion;

import excepciones.ValidacionException;

/**
 * Clase para validar entradas del usuario
 */
public class ValidadorEntradas {
    
    /**
     * Valida que un ID sea válido (mayor que 0)
     * @param id ID a validar
     * @throws ValidacionException Si el ID no es válido
     */
    public static void validarId(int id) throws ValidacionException {
        if (id <= 0) {
            throw new ValidacionException("El ID debe ser un número positivo mayor que 0");
        }
    }
    
    /**
     * Valida que una descripción no esté vacía o sea nula
     * @param descripcion Descripción a validar
     * @throws ValidacionException Si la descripción no es válida
     */
    public static void validarDescripcion(String descripcion) throws ValidacionException {
        if (descripcion == null || descripcion.trim().isEmpty()) {
            throw new ValidacionException("La descripción no puede estar vacía");
        }
        
        if (descripcion.length() > 200) {
            throw new ValidacionException("La descripción no puede tener más de 200 caracteres");
        }
    }
    
    /**
     * Valida que una opción del menú sea válida
     * @param opcion Opción a validar
     * @param opcionesValidas Array de opciones válidas
     * @throws ValidacionException Si la opción no es válida
     */
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
    
    /**
     * Valida que una cadena represente un número entero válido
     * @param entrada Cadena a validar
     * @return El número entero parseado
     * @throws ValidacionException Si la entrada no es un número válido
     */
    public static int validarYParsearEntero(String entrada) throws ValidacionException {
        try {
            return Integer.parseInt(entrada.trim());
        } catch (NumberFormatException e) {
            throw new ValidacionException("Debe ingresar un número entero válido");
        }
    }
}
