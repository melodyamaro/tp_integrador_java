# 📋 Sistema de Gestión de Tareas

Un sistema completo de gestión de tareas desarrollado en **Java 11** que implementa persistencia de datos, validación de entradas, interfaces funcionales y manejo robusto de excepciones. Este proyecto integrador demuestra el uso de conceptos avanzados de Java incluyendo Streams API, interfaces funcionales, manejo de excepciones personalizadas y arquitectura modular.

## ✨ Características Principales

- **🔧 CRUD completo**: Crear, leer, actualizar y eliminar tareas
- **💾 Persistencia automática**: Guardado automático en archivo de texto
- **✅ Validación robusta**: Validación de entradas con excepciones personalizadas
- **🎯 Interfaces funcionales**: Uso extensivo de Predicate, Consumer y Function
- **⚠️ Manejo de errores**: Sistema completo de excepciones personalizadas
- **📅 Fechas automáticas**: Registro de fechas de creación y completado
- **🖥️ Interfaz de consola**: Menú interactivo intuitivo y fácil de usar
- **🧪 Testing**: Suite completa de pruebas unitarias con JUnit 5 y Mockito
- **📦 Maven**: Gestión de dependencias y construcción del proyecto

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

## 🛠️ Tecnologías y Dependencias

### Tecnologías Principales
- **Java 11** - Lenguaje de programación
- **Maven** - Gestión de dependencias y construcción
- **JUnit 5** - Framework de testing
- **Mockito** - Framework de mocking para pruebas

### Características de Java Utilizadas
- **Interfaces funcionales**: Predicate, Consumer, Function
- **Streams API**: Para filtrado y procesamiento de datos
- **LocalDateTime**: Manejo de fechas y horas
- **Manejo de archivos**: I/O para persistencia
- **Excepciones personalizadas**: Manejo robusto de errores
- **Lambdas**: Expresiones lambda para código más limpio

## 🚀 Instalación y Ejecución

### Requisitos del Sistema
- **Java 11** o superior
- **Maven 3.6+** (recomendado)
- Terminal/Consola

### Opción 1: Ejecución con Maven (Recomendado)

1. **Clonar el repositorio**
```bash
git clone <url-del-repositorio>
cd tp_integrador_java
```

2. **Compilar el proyecto**
```bash
mvn clean compile
```

3. **Ejecutar la aplicación**
```bash
mvn exec:java -Dexec.mainClass="Main"
```

4. **Ejecutar las pruebas**
```bash
mvn test
```

### Opción 2: Compilación Manual

1. **Compilar el proyecto**
```bash
javac -d target/classes -cp src src/main/java/*.java src/main/java/*/*.java src/main/java/*/*/*.java
```

2. **Ejecutar la aplicación**
```bash
java -cp target/classes Main
```

### Opción 3: Ejecutar JAR (después de compilar con Maven)
```bash
mvn clean package
java -jar target/tp-integrador-java-1.0.0.jar
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

## 🧪 Testing y Calidad del Código

### Suite de Pruebas
El proyecto incluye una suite completa de pruebas unitarias que cubre:

#### Pruebas de Validación
- ✅ Descripción vacía → Error de validación
- ✅ ID negativo → Error de validación  
- ✅ ID inexistente → Tarea no encontrada
- ✅ Opción de menú inválida → Error de validación
- ✅ Descripción muy larga (>150 caracteres) → Error de validación
- ✅ Descripción solo espacios → Error de validación

#### Pruebas de Funcionalidad
- ✅ Creación de tareas con descripción válida
- ✅ Generación automática de IDs únicos
- ✅ Fechas de creación automáticas
- ✅ Estado inicial de tareas (pendientes)
- ✅ Manejo de fechas de completado

#### Pruebas de Persistencia
- ✅ Carga automática al iniciar
- ✅ Guardado automático al salir
- ✅ Manejo de archivos corruptos
- ✅ Archivo inexistente (crea lista vacía)

### Ejecutar Pruebas
```bash
# Ejecutar todas las pruebas
mvn test

# Ejecutar pruebas con reporte detallado
mvn test -Dtest=AppTareasTest

# Generar reporte de cobertura (si está configurado)
mvn jacoco:report
```

## Flujo de la Aplicación

1. **Inicio**: Carga tareas desde archivo
2. **Menú**: Muestra opciones disponibles
3. **Validación**: Valida entrada del usuario
4. **Procesamiento**: Ejecuta operación solicitada
5. **Persistencia**: Guarda cambios al salir

## 📊 Estructura de Datos y Almacenamiento

### Clase Tarea
```java
public class Tarea {
    private int id;                    // ID único de la tarea
    private String descripcion;        // Descripción de la tarea (máx 150 chars)
    private boolean completada;        // Estado de la tarea
    private LocalDateTime fechaCreacion;      // Fecha de creación automática
    private LocalDateTime fechaCompletada;    // Fecha de completado (null si pendiente)
    
    // Métodos principales
    public String getFechaCreacionFormateada()    // Formato: dd/MM/yyyy HH:mm
    public String getFechaCompletadaFormateada()  // Formato: dd/MM/yyyy HH:mm o "No completada"
}
```

### Almacenamiento en Memoria
- **Estructura**: `ArrayList<Tarea>` - Lista dinámica de tareas
- **Búsqueda**: Streams API con Predicates para filtrado eficiente
- **Orden**: Mantiene orden de inserción (FIFO)
- **IDs**: Generación automática incremental

### Persistencia en Archivo
- **Archivo**: `tareas.txt` en la raíz del proyecto
- **Formato**: Separado por pipes (`|`) para fácil parsing
- **Codificación**: UTF-8 para soporte de caracteres especiales
- **Backup**: Carga automática al inicio, guardado al salir

## 🎯 Características Avanzadas

### Gestión Automática
- **🕒 Fechas automáticas**: Registro automático de creación y completado
- **🔢 IDs únicos**: Generación automática incremental de IDs
- **🔄 Persistencia robusta**: Manejo completo de errores de archivo
- **📝 Validación inteligente**: Validación en tiempo real con mensajes claros

### Optimizaciones de Rendimiento
- **⚡ Filtrado dinámico**: Uso de Streams para consultas eficientes
- **🎯 Búsqueda optimizada**: Predicates para filtros rápidos
- **💾 Carga lazy**: Solo carga datos cuando es necesario
- **🔄 Operaciones atómicas**: Transacciones seguras para persistencia

### Experiencia de Usuario
- **🖥️ Interfaz intuitiva**: Menú claro con opciones numeradas
- **✅ Confirmaciones**: Mensajes de éxito y error informativos
- **📊 Estadísticas**: Visualización clara del estado de las tareas
- **🎨 Formato visual**: Símbolos distintivos para tareas completadas/pendientes

## 📈 Estado del Proyecto

### Versión Actual
- **Versión**: 1.0.0
- **Estado**: ✅ Estable y funcional
- **Última actualización**: Octubre 2025

### Funcionalidades Implementadas
- ✅ CRUD completo de tareas
- ✅ Persistencia en archivo de texto
- ✅ Validación robusta de entradas
- ✅ Interfaces funcionales (Predicate, Consumer, Function)
- ✅ Manejo de excepciones personalizadas
- ✅ Suite completa de pruebas unitarias
- ✅ Arquitectura modular y escalable
- ✅ Documentación completa


