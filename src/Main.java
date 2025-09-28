//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        System.out.println("=== SISTEMA DE GESTIÓN DE TAREAS ===");
        System.out.println("Bienvenido al sistema de gestión de tareas");
        System.out.println("Las tareas se guardan automáticamente en formato JSON");
        System.out.println("=====================================\n");
        
        AppTareas app = new AppTareas();
        app.iniciar();
        
        System.out.println("\n¡Gracias por usar el sistema de gestión de tareas!");
    }
}