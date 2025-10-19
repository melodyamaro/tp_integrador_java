import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import excepciones.ValidacionException;
import modelo.Tarea;
import persistencia.GestorPersistencia;


//Tests para la clase AppTareas enfocados en el método agregarTarea
@ExtendWith(MockitoExtension.class)
@DisplayName("Tests para AppTareas - Método agregarTarea")
public class AppTareasTest {

    @Mock
    private GestorPersistencia mockGestorPersistencia;

    private AppTareas appTareas;

    @BeforeEach
    void setUp() {
        // Usar el constructor que permite inyectar dependencias para testing
        appTareas = new AppTareas(mockGestorPersistencia, false);
    }

    @Test
    @DisplayName("Debería agregar una tarea con descripción válida")
    void testAgregarTareaConDescripcionValida() throws ValidacionException {
        String descripcionValida = "Completar proyecto Java";
        int siguienteIdEsperado = 1;


        Tarea tareaCreada = appTareas.agregarTareaConDescripcion(descripcionValida);

        assertNotNull(tareaCreada, "La tarea creada no debería ser nula");
        assertEquals(siguienteIdEsperado, tareaCreada.getId(), "El ID de la tarea debería ser 1");
        assertEquals(descripcionValida, tareaCreada.getDescripcion(), "La descripción debería coincidir");
        assertFalse(tareaCreada.isCompletada(), "La tarea debería estar pendiente por defecto");

        List<Tarea> tareas = appTareas.getTareas();
        assertEquals(1, tareas.size(), "Debería haber exactamente 1 tarea en la lista");
        assertTrue(tareas.contains(tareaCreada), "La lista debería contener la tarea creada");
        

        assertEquals(2, appTareas.getSiguienteId(), "El siguiente ID debería ser 2");
    }

    @Test
    @DisplayName("Debería lanzar ValidacionException cuando la descripción es null")
    void testAgregarTareaConDescripcionNull() {

        String descripcionNull = null;

        ValidacionException exception = assertThrows(ValidacionException.class, 
            () -> appTareas.agregarTareaConDescripcion(descripcionNull),
            "Debería lanzar ValidacionException para descripción null");

        assertTrue(exception.getMessage().contains("La descripción no puede estar vacía"),
            "El mensaje de error debería indicar que la descripción no puede estar vacía");

        assertEquals(0, appTareas.getTareas().size(), "No debería haber tareas en la lista");
    }

    @Test
    @DisplayName("Debería lanzar ValidacionException cuando la descripción está vacía")
    void testAgregarTareaConDescripcionVacia() {

        String descripcionVacia = "";

        ValidacionException exception = assertThrows(ValidacionException.class, 
            () -> appTareas.agregarTareaConDescripcion(descripcionVacia),
            "Debería lanzar ValidacionException para descripción vacía");

        assertTrue(exception.getMessage().contains("La descripción no puede estar vacía"),
            "El mensaje de error debería indicar que la descripción no puede estar vacía");

        assertEquals(0, appTareas.getTareas().size(), "No debería haber tareas en la lista");
    }

    @Test
    @DisplayName("Debería lanzar ValidacionException cuando la descripción solo contiene espacios")
    void testAgregarTareaConDescripcionSoloEspacios() {

        String descripcionSoloEspacios = "   ";


        ValidacionException exception = assertThrows(ValidacionException.class, 
            () -> appTareas.agregarTareaConDescripcion(descripcionSoloEspacios),
            "Debería lanzar ValidacionException para descripción solo con espacios");

        assertTrue(exception.getMessage().contains("La descripción no puede estar vacía"),
            "El mensaje de error debería indicar que la descripción no puede estar vacía");
        

        assertEquals(0, appTareas.getTareas().size(), "No debería haber tareas en la lista");
    }

    @Test
    @DisplayName("Debería lanzar ValidacionException cuando la descripción es muy larga")
    void testAgregarTareaConDescripcionMuyLarga() {

        String descripcionMuyLarga = "a".repeat(151); // Más de 150 caracteres

        ValidacionException exception = assertThrows(ValidacionException.class, 
            () -> appTareas.agregarTareaConDescripcion(descripcionMuyLarga),
            "Debería lanzar ValidacionException para descripción muy larga");

        assertTrue(exception.getMessage().contains("La descripción no puede tener más de 150 caracteres"),
            "El mensaje de error debería indicar que la descripción es muy larga");

        assertEquals(0, appTareas.getTareas().size(), "No debería haber tareas en la lista");
    }

    @Test
    @DisplayName("Debería crear tarea con fecha de creación")
    void testTareaCreadaConFechaCreacion() throws ValidacionException {

        String descripcion = "Tarea con fecha";

        Tarea tareaCreada = appTareas.agregarTareaConDescripcion(descripcion);

        assertNotNull(tareaCreada.getFechaCreacion(), "La fecha de creación no debería ser nula");
        assertNotNull(tareaCreada.getFechaCreacionFormateada(), "La fecha formateada no debería ser nula");
        assertFalse(tareaCreada.getFechaCreacionFormateada().isEmpty(), 
            "La fecha formateada no debería estar vacía");
    }

    @Test
    @DisplayName("Debería mantener el estado de completada como false por defecto")
    void testTareaCreadaComoNoCompletada() throws ValidacionException {

        String descripcion = "Tarea pendiente";

        Tarea tareaCreada = appTareas.agregarTareaConDescripcion(descripcion);

        assertFalse(tareaCreada.isCompletada(), "La tarea debería estar pendiente por defecto");
        assertNull(tareaCreada.getFechaCompletada(), "La fecha de completado debería ser null");
        assertEquals("No completada", tareaCreada.getFechaCompletadaFormateada(),
            "La fecha de completado formateada debería indicar 'No completada'");
    }

}
