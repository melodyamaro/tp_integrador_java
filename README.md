# Sistema de Gestión de Tareas

Un sistema completo de gestión de tareas desarrollado en Java que implementa persistencia de datos, validación de entradas, interfaces funcionales y manejo robusto de excepciones.

##  Características

- **CRUD completo**: Crear, leer, actualizar y eliminar tareas
- **Persistencia de datos**: Guardado automático en archivo de texto
- **Validación robusta**: Validación de entradas con excepciones personalizadas
- **Interfaces funcionales**: Uso de Predicate, Consumer y Function
- **Manejo de errores**: Excepciones personalizadas y manejo de errores
- **Fechas automáticas**: Registro de fechas de creación y completado
- **Interfaz de consola**: Menú interactivo fácil de usar

## Funcionalidades

### Menú Principal
1. **Crear tarea** - Agregar nuevas tareas con validación
2. **Eliminar tarea** - Remover tareas por ID
3. **Marcar como completada** - Cambiar estado de tareas pendientes
4. **Mostrar todas las tareas** - Listar todas las tareas
5. **Mostrar tareas completadas** - Filtrar tareas completadas
6. **Mostrar tareas pendientes** - Filtrar tareas pendientes
7. **Salir** - Guardar y cerrar la aplicación

### Validaciones Implementadas
-  Validación de IDs (números positivos)
- Validación de descripciones (no vacías, máximo 150 caracteres)
- Validación de opciones del menú
- Validación de entrada numérica
- Verificación de existencia de tareas
- Manejo de archivos de persistencia

##  Arquitectura del Proyecto

```
src/
├── modelo/
│   └── Tarea.java                 # Clase principal de tareas
├── excepciones/
│   ├── TareaException.java        # Excepción base
│   ├── TareaNoEncontradaException.java
│   ├── ValidacionException.java
│   └── PersistenciaException.java
├── validacion/
│   └── ValidadorEntradas.java     # Validaciones de entrada
├── persistencia/
│   └── GestorPersistencia.java    # Manejo de archivos
├── operaciones/
│   └── OperacionesTareas.java     # Interfaces funcionales
├── AppTareas.java                 # Lógica principal de la aplicación
└── Main.java                      # Punto de entrada
```

## Tecnologías Utilizadas

- **Java 11+**
- **Interfaces funcionales**: Predicate, Consumer, Function
- **Streams API**: Para filtrado y procesamiento de datos
- **Manejo de archivos**: I/O para persistencia
- **Excepciones personalizadas**: Manejo robusto de errores

## Instalación y Ejecución

### Requisitos
- Java 11 o superior
- Terminal/Consola

### Pasos para ejecutar

1. **Clonar o descargar el proyecto**
```bash
git clone <url-del-repositorio>
cd "Tp integrador"
```

2. **Compilar el proyecto**
```bash
javac -d out/production/Tp\ integrador -cp src src/*.java src/*/*.java src/*/*/*.java
```

3. **Ejecutar la aplicación**
```bash
java -cp out/production/Tp\ integrador Main
```

### Ejecución con Gradle (opcional)
```bash
./gradlew run
```

## Formato de Persistencia

Las tareas se guardan en el archivo `tareas.txt` con el siguiente formato:

```
ID|Descripción|Estado|FechaCreación|FechaCompletada
```

**Ejemplo:**
```
1|Estudiar Java|false|28/09/2024 16:30|No completada
2|Hacer ejercicio|true|28/09/2024 16:31|28/09/2024 17:00
3|Comprar comida|false|28/09/2024 16:32|No completada
```

## 🔧 Interfaces Funcionales Implementadas

### Predicates (Filtros)
- `TAREA_COMPLETADA` - Filtra tareas completadas
- `TAREA_PENDIENTE` - Filtra tareas pendientes
- `filtrarPorId(int id)` - Filtra por ID específico
- `filtrarPorDescripcion(String texto)` - Filtra por texto en descripción

### Consumers (Acciones)
- `MARCAR_COMPLETADA` - Marca tarea como completada
- `MARCAR_PENDIENTE` - Marca tarea como pendiente
- `IMPRIMIR_TAREA` - Imprime información de la tarea
- `MOSTRAR_ESTADISTICAS` - Muestra estadísticas detalladas

### Functions (Transformaciones)
- `A_FORMATO_TEXTO` - Convierte tarea a formato de archivo
- `OBTENER_ESTADO` - Obtiene estado como string

## Manejo de Excepciones

### Excepciones Personalizadas
- **`TareaException`** - Excepción base para errores de tareas
- **`TareaNoEncontradaException`** - Tarea no existe
- **`ValidacionException`** - Error de validación de entrada
- **`PersistenciaException`** - Error de archivo/persistencia

### Ejemplos de Manejo
```java
try {
    int id = ValidadorEntradas.validarYParsearEntero(entrada);
    ValidadorEntradas.validarId(id);

} catch (ValidacionException e) {
    System.out.println("Error: " + e.getMessage());
} catch (TareaNoEncontradaException e) {
    System.out.println("Error: " + e.getMessage());
}
```

## Ejemplos de Uso

### Crear una tarea
```
=== CREAR NUEVA TAREA ===
Ingrese la descripción de la tarea: Estudiar para el examen
✓ Tarea creada exitosamente con ID: 1
```

### Marcar como completada
```
=== MARCAR TAREA COMO COMPLETADA ===
=== TAREAS PENDIENTES ===
[1] ○ Pendiente - Estudiar para el examen
Ingrese el ID de la tarea a marcar como completada: 1
Tarea 'Estudiar para el examen' marcada como completada
```

### Mostrar tareas
```
=== TODAS LAS TAREAS ===
[1] ✓ Completada - Estudiar para el examen
[2] ○ Pendiente - Hacer ejercicio
```

##  Casos de Prueba

### Validaciones
-  Descripción vacía → Error de validación
-  ID negativo → Error de validación
-  ID inexistente → Tarea no encontrada
-  Opción de menú inválida → Error de validación

### Persistencia
-  Carga automática al iniciar
-  Guardado automático al salir
-  Manejo de archivos corruptos
-  Archivo inexistente (crea lista vacía)

## Flujo de la Aplicación

1. **Inicio**: Carga tareas desde archivo
2. **Menú**: Muestra opciones disponibles
3. **Validación**: Valida entrada del usuario
4. **Procesamiento**: Ejecuta operación solicitada
5. **Persistencia**: Guarda cambios al salir

## Estructura de Datos

### Clase Tarea
```java
public class Tarea {
    private int id;
    private String descripcion;
    private boolean completada;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaCompletada;
}
```

### Almacenamiento
- **Estructura**: ArrayList<Tarea>
- **Búsqueda**: Streams con Predicates
- **Orden**: Mantiene orden de inserción

## Características Avanzadas

- **Fechas automáticas**: Registro de creación y completado
- **IDs únicos**: Generación automática de IDs
- **Filtrado dinámico**: Uso de streams para consultas
- **Persistencia robusta**: Manejo de errores de archivo
- **Interfaz clara**: Mensajes informativos y confirmaciones


