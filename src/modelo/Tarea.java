package modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Tarea {
    private int id;
    private String descripcion;
    private boolean completada;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCompletada;

    public Tarea(int id, String descripcion, boolean completada) {
        this.id = id;
        this.descripcion = descripcion;
        this.completada = completada;
        this.fechaCreacion = LocalDateTime.now();
        this.fechaCompletada = completada ? LocalDateTime.now() : null;
    }

    public Tarea() {
        this.fechaCreacion = LocalDateTime.now();
    }

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
        if (completada && this.fechaCompletada == null) {
            this.fechaCompletada = LocalDateTime.now();
        } else if (!completada) {
            this.fechaCompletada = null;
        }
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDateTime getFechaCompletada() {
        return fechaCompletada;
    }

    public void setFechaCompletada(LocalDateTime fechaCompletada) {
        this.fechaCompletada = fechaCompletada;
    }

    //Obtiene la fecha de creación formateada
    public String getFechaCreacionFormateada() {
        return fechaCreacion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
    }

    //Obtiene la fecha de completado formateada
    public String getFechaCompletadaFormateada() {
        return fechaCompletada != null ? 
            fechaCompletada.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) : 
            "No completada";
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", completada=" + completada +
                ", fechaCreacion=" + getFechaCreacionFormateada() +
                ", fechaCompletada=" + getFechaCompletadaFormateada() +
                '}';
    }
}
