import java.util.*;
import java.util.stream.Collectors;
import excepciones.*;
import validacion.ValidadorEntradas;
import persistencia.GestorPersistencia;
import operaciones.OperacionesTareas;
import modelo.Tarea;

public class AppTareas {

    private Scanner sc;
    private List<Tarea> tareas;
    private GestorPersistencia gestorPersistencia;
    private int siguienteId;

    public AppTareas() {
        this.sc = new Scanner(System.in);
        this.tareas = new ArrayList<>();
        this.gestorPersistencia = new GestorPersistencia();
        this.siguienteId = 1;
        cargarTareasDesdeArchivo();
    }

    // Constructor para testing que permite inyectar dependencias y controlar si cargar automáticamente
    public AppTareas(GestorPersistencia gestorPersistencia, boolean cargarAutomaticamente) {
        this.sc = new Scanner(System.in);
        this.tareas = new ArrayList<>();
        this.gestorPersistencia = gestorPersistencia;
        this.siguienteId = 1;
        if (cargarAutomaticamente) {
            cargarTareasDesdeArchivo();
        }
    }

    public void iniciar() {
        int opcion = 0;
        int[] opcionesValidas = {1, 2, 3, 4, 5, 6, 7};

        do {
            try {
                mostrarMenu();
                String entrada = sc.nextLine();
                opcion = ValidadorEntradas.validarYParsearEntero(entrada);
                ValidadorEntradas.validarOpcionMenu(opcion, opcionesValidas);

                switch (opcion) {
                    case 1:
                        agregarTarea();
                        break;
                    case 2:
                        mostrarTodasLasTareas();
                        break;
                    case 3:
                        eliminarTarea();
                        break;
                    case 4:
                        marcarTareaCompletada();
                        break;
                    case 5:
                        mostrarTareasCompletadas();
                        break;
                    case 6:
                        mostrarTareasPendientes();
                        break;
                    case 7:
                        System.out.println("Saliendo del programa");
                        guardarTareasEnArchivo();
                        break;
                }
            } catch (TareaException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Error inesperado: " + e.getMessage());
            }
            
            if (opcion != 7) {
                System.out.println("\nPresione Enter para continuar...");
                sc.nextLine();
            }
        } while (opcion != 7);

        sc.close();
    }

    public void mostrarMenu() {
        System.out.println("\n=== MENÚ DE TAREAS ===");
        System.out.println("1. Agregar tarea");
        System.out.println("2. Mostrar todas las tareas");
        System.out.println("3. Eliminar tarea");
        System.out.println("4. Marcar tarea como completada");
        System.out.println("5. Mostrar tareas completadas");
        System.out.println("6. Mostrar tareas pendientes");
        System.out.println("7. Salir");
        System.out.print("Ingrese una opción: ");
    }

    public void agregarTarea() throws ValidacionException {
        System.out.println("\n=== CREAR NUEVA TAREA ===");
        System.out.print("Ingrese la descripción de la tarea: ");
        String descripcion = sc.nextLine();
        
        agregarTareaConDescripcion(descripcion);
    }


    public Tarea agregarTareaConDescripcion(String descripcion) throws ValidacionException {
        ValidadorEntradas.validarDescripcion(descripcion);
        
        Tarea nuevaTarea = new Tarea(siguienteId++, descripcion, false);
        tareas.add(nuevaTarea);
        
        System.out.println("✓ Tarea creada exitosamente con ID: " + nuevaTarea.getId());
        return nuevaTarea;
    }

    public void eliminarTarea() throws ValidacionException, TareaNoEncontradaException {
        System.out.println("\n=== ELIMINAR TAREA ===");
        mostrarTodasLasTareas();
        
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas para eliminar.");
            return;
        }
        
        System.out.print("Ingrese el ID de la tarea a eliminar: ");
        String entrada = sc.nextLine();
        int id = ValidadorEntradas.validarYParsearEntero(entrada);
        ValidadorEntradas.validarId(id);
        
        Tarea tareaAEliminar = buscarTareaPorId(id);
        if (tareaAEliminar == null) {
            throw new TareaNoEncontradaException(id);
        }
        
        tareas.remove(tareaAEliminar);
        System.out.println("✓ Tarea eliminada exitosamente: " + tareaAEliminar.getDescripcion());
    }

    public void marcarTareaCompletada() throws ValidacionException, TareaNoEncontradaException {
        System.out.println("\n=== MARCAR TAREA COMO COMPLETADA ===");
        mostrarTareasPendientes();
        
        List<Tarea> tareasPendientes = tareas.stream()
                .filter(OperacionesTareas.TAREA_PENDIENTE)
                .collect(Collectors.toList());
        
        if (tareasPendientes.isEmpty()) {
            System.out.println("No hay tareas pendientes para marcar como completadas.");
            return;
        }
        
        System.out.print("Ingrese el ID de la tarea a marcar como completada: ");
        String entrada = sc.nextLine();
        int id = ValidadorEntradas.validarYParsearEntero(entrada);
        ValidadorEntradas.validarId(id);
        
        Tarea tarea = buscarTareaPorId(id);
        if (tarea == null) {
            throw new TareaNoEncontradaException(id);
        }
        
        if (tarea.isCompletada()) {
            System.out.println("Esta tarea ya está completada.");
            return;
        }
        
        OperacionesTareas.MARCAR_COMPLETADA.accept(tarea);
    }

    public void mostrarTodasLasTareas() {
        System.out.println("\n=== TODAS LAS TAREAS ===");
        if (tareas.isEmpty()) {
            System.out.println("No hay tareas registradas.");
            return;
        }
        
        tareas.forEach(OperacionesTareas.IMPRIMIR_TAREA);
    }

    public void mostrarTareasCompletadas() {
        System.out.println("\n=== TAREAS COMPLETADAS ===");
        List<Tarea> tareasCompletadas = tareas.stream()
                .filter(OperacionesTareas.TAREA_COMPLETADA)
                .collect(Collectors.toList());
        
        if (tareasCompletadas.isEmpty()) {
            System.out.println("No hay tareas completadas.");
            return;
        }
        
        tareasCompletadas.forEach(OperacionesTareas.IMPRIMIR_TAREA);
    }

    public void mostrarTareasPendientes() {
        System.out.println("\n=== TAREAS PENDIENTES ===");
        List<Tarea> tareasPendientes = tareas.stream()
                .filter(OperacionesTareas.TAREA_PENDIENTE)
                .collect(Collectors.toList());
        
        if (tareasPendientes.isEmpty()) {
            System.out.println("No hay tareas pendientes.");
            return;
        }
        
        tareasPendientes.forEach(OperacionesTareas.IMPRIMIR_TAREA);
    }

    public Tarea buscarTareaPorId(int id) {
        return tareas.stream()
                .filter(OperacionesTareas.filtrarPorId(id))
                .findFirst()
                .orElse(null);
    }

    public void cargarTareasDesdeArchivo() {
        try {
            List<Tarea> tareasCargadas = gestorPersistencia.cargarTareas();
            tareas.addAll(tareasCargadas);
            
            // Actualizar el siguiente ID basado en las tareas cargadas
            if (!tareas.isEmpty()) {
                siguienteId = tareas.stream()
                        .mapToInt(Tarea::getId)
                        .max()
                        .orElse(0) + 1;
            }
            
            if (!tareasCargadas.isEmpty()) {
                System.out.println("✓ Se cargaron " + tareasCargadas.size() + " tareas desde el archivo.");
            }
        } catch (PersistenciaException e) {
            System.out.println("Advertencia: " + e.getMessage());
            System.out.println("Se iniciará con una lista vacía de tareas.");
        }
    }

    public void guardarTareasEnArchivo() {
        try {
            gestorPersistencia.guardarTareas(tareas);
            System.out.println("✓ Tareas guardadas exitosamente en el archivo.");
        } catch (PersistenciaException e) {
            System.out.println("Error al guardar las tareas: " + e.getMessage());
        }
    }

   
    public List<Tarea> getTareas() {
        return tareas;
    }

    public GestorPersistencia getGestorPersistencia() {
        return gestorPersistencia;
    }

    public int getSiguienteId() {
        return siguienteId;
    }

    public void setSiguienteId(int siguienteId) {
        this.siguienteId = siguienteId;
    }
}
