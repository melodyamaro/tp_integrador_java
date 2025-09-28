package operaciones;

import modelo.Tarea;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Function;

/**
 * Clase que contiene operaciones funcionales para el manejo de tareas
 */
public class OperacionesTareas {
    
    /**
     * Predicate para filtrar tareas completadas
     */
    public static final Predicate<Tarea> TAREA_COMPLETADA = tarea -> tarea.isCompletada();
    
    /**
     * Predicate para filtrar tareas pendientes
     */
    public static final Predicate<Tarea> TAREA_PENDIENTE = tarea -> !tarea.isCompletada();
    
    /**
     * Predicate para filtrar tareas por ID
     */
    public static Predicate<Tarea> filtrarPorId(int id) {
        return tarea -> tarea.getId() == id;
    }
    
    /**
     * Predicate para filtrar tareas que contengan texto en la descripción
     */
    public static Predicate<Tarea> filtrarPorDescripcion(String texto) {
        return tarea -> tarea.getDescripcion().toLowerCase()
                .contains(texto.toLowerCase());
    }
    
    /**
     * Consumer para marcar una tarea como completada
     */
    public static final Consumer<Tarea> MARCAR_COMPLETADA = tarea -> {
        tarea.setCompletada(true);
        System.out.println("Tarea '" + tarea.getDescripcion() + "' marcada como completada");
    };
    
    /**
     * Consumer para marcar una tarea como pendiente
     */
    public static final Consumer<Tarea> MARCAR_PENDIENTE = tarea -> {
        tarea.setCompletada(false);
        System.out.println("Tarea '" + tarea.getDescripcion() + "' marcada como pendiente");
    };
    
    /**
     * Consumer para imprimir información de una tarea
     */
    public static final Consumer<Tarea> IMPRIMIR_TAREA = tarea -> {
        String estado = tarea.isCompletada() ? "✓ Completada" : "○ Pendiente";
        System.out.printf("[%d] %s - %s%n", tarea.getId(), estado, tarea.getDescripcion());
    };
    
    /**
     * Function para convertir una tarea a formato de línea para archivo de texto
     */
    public static final Function<Tarea, String> A_FORMATO_TEXTO = tarea -> 
        String.format("%d|%s|%s", 
            tarea.getId(), 
            tarea.getDescripcion(), 
            tarea.isCompletada() ? "COMPLETADA" : "PENDIENTE");
    
    /**
     * Function para obtener el estado de una tarea como string
     */
    public static final Function<Tarea, String> OBTENER_ESTADO = tarea -> 
        tarea.isCompletada() ? "Completada" : "Pendiente";
    
    /**
     * Consumer para mostrar estadísticas de una tarea
     */
    public static final Consumer<Tarea> MOSTRAR_ESTADISTICAS = tarea -> {
        System.out.println("=== Estadísticas de la Tarea ===");
        System.out.println("ID: " + tarea.getId());
        System.out.println("Descripción: " + tarea.getDescripcion());
        System.out.println("Estado: " + OBTENER_ESTADO.apply(tarea));
        System.out.println("Longitud de descripción: " + tarea.getDescripcion().length() + " caracteres");
        System.out.println("================================");
    };
}
