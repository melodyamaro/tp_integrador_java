package operaciones;

import modelo.Tarea;
import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 * Clase que contiene operaciones funcionales para el manejo de tareas
 */
public class OperacionesTareas {
    
    //Predicate para filtrar tareas completadas
    public static final Predicate<Tarea> TAREA_COMPLETADA = tarea -> tarea.isCompletada();
    
    //Predicate para filtrar tareas pendientes
    public static final Predicate<Tarea> TAREA_PENDIENTE = tarea -> !tarea.isCompletada();
    
    //Predicate para filtrar tareas por ID
    public static Predicate<Tarea> filtrarPorId(int id) {
        return tarea -> tarea.getId() == id;
    }
    
    //Consumer para marcar una tarea como completada
    public static final Consumer<Tarea> MARCAR_COMPLETADA = tarea -> {
        tarea.setCompletada(true);
        System.out.println("Tarea '" + tarea.getDescripcion() + "' marcada como completada");
    };
    
    //Consumer para marcar una tarea como pendiente
    public static final Consumer<Tarea> MARCAR_PENDIENTE = tarea -> {
        tarea.setCompletada(false);
        System.out.println("Tarea '" + tarea.getDescripcion() + "' marcada como pendiente");
    };
    
    //Consumer para imprimir información de una tarea
    public static final Consumer<Tarea> IMPRIMIR_TAREA = tarea -> {
        String estado = tarea.isCompletada() ? "✓ Completada" : "○ Pendiente";
        System.out.printf("[%d] %s - %s%n", tarea.getId(), estado, tarea.getDescripcion());
    };
      
}
