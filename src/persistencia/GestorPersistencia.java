package persistencia;

import excepciones.PersistenciaException;
import modelo.Tarea;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase para manejar la persistencia de tareas en formato de texto
 */
public class GestorPersistencia {
    private static final String ARCHIVO_TAREAS = "tareas.txt";

    /**
     * Guarda la lista de tareas en un archivo de texto
     * @param tareas Lista de tareas a guardar
     * @throws PersistenciaException Si ocurre un error al escribir el archivo
     */
    public void guardarTareas(List<Tarea> tareas) throws PersistenciaException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_TAREAS))) {
            for (Tarea tarea : tareas) {
                writer.println(tarea.getId() + "|" + 
                             tarea.getDescripcion() + "|" + 
                             tarea.isCompletada() + "|" +
                             tarea.getFechaCreacionFormateada() + "|" +
                             tarea.getFechaCompletadaFormateada());
            }
        } catch (IOException e) {
            throw new PersistenciaException("No se pudo guardar las tareas en el archivo", e);
        }
    }

    /**
     * Carga la lista de tareas desde un archivo de texto
     * @return Lista de tareas cargadas
     * @throws PersistenciaException Si ocurre un error al leer el archivo
     */
    public List<Tarea> cargarTareas() throws PersistenciaException {
        File archivo = new File(ARCHIVO_TAREAS);
        List<Tarea> tareas = new ArrayList<>();
        
        // Si el archivo no existe, retornar lista vacía
        if (!archivo.exists()) {
            return tareas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    String[] partes = linea.split("\\|");
                    if (partes.length >= 3) {
                        int id = Integer.parseInt(partes[0]);
                        String descripcion = partes[1];
                        boolean completada = Boolean.parseBoolean(partes[2]);
                        
                        Tarea tarea = new Tarea(id, descripcion, completada);
                        tareas.add(tarea);
                    }
                }
            }
        } catch (IOException | NumberFormatException e) {
            throw new PersistenciaException("No se pudo cargar las tareas desde el archivo", e);
        }
        
        return tareas;
    }

    /**
     * Verifica si existe el archivo de persistencia
     * @return true si el archivo existe, false en caso contrario
     */
    public boolean existeArchivo() {
        return new File(ARCHIVO_TAREAS).exists();
    }
}
