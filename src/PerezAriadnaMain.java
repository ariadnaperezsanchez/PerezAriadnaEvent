import java.util.ArrayList;
import java.util.Scanner;

public class PerezAriadnaMain {
    private static ArrayList<PerezAriadnaEvent> events = new ArrayList<>(); // Lista de eventos

    public static void main(String[] args) { // Menú de opciones
        Scanner input = new Scanner(System.in);
        int menu = 0;

        do {
            System.out.println("\nBienvenido a Event Planner. Selecciona una opción:");
            System.out.println("[1] Añadir Evento");
            System.out.println("[2] Borrar Evento");
            System.out.println("[3] Lista Eventos");
            System.out.println("[4] Marcar/desmarcar tarea de un evento como completada");
            System.out.println("[5] Salir");

            if (input.hasNextInt()) {
                menu = input.nextInt();
                input.nextLine();

                switch (menu) {
                    case 1:
                        addEvent(input);
                        break;
                    case 2:
                        deleteEvent(input);
                        break;
                    case 3:
                        listEvents();
                        break;
                    case 4:
                        toggleTaskCompletion(input);
                        break;
                    case 5:
                        System.out.println("Saliendo...");
                        break;
                    default:
                        System.out.println("Opción inválida.");
                }
            } else {
                System.out.println("Opción inválida.");
                input.nextLine(); // Limpiar la entrada no válida
            }
        } while (menu != 5);

        input.close();
    }

    // Método para añadir Evento
    private static void addEvent(Scanner input) {
        PerezAriadnaEvent newEvent = PerezAriadnaEvent.createEvent();
        events.add(newEvent);
        System.out.println("Evento añadido exitosamente.");
    }

    // Método para borrar Evento
    private static void deleteEvent(Scanner input) {
        if (events.isEmpty()) {
            System.out.println("No hay eventos para borrar.");
            return;
        }

        System.out.println("Selecciona el número del evento a borrar:");
        listEvents();
        int index = getIndexFromUser(input, events.size());

        // Eliminar Evento
        if (index != -1) {
            PerezAriadnaEvent removedEvent = events.remove(index);
            System.out.println("Evento eliminado: " + removedEvent.getTitle());
        }
    }

    // Método para listar los eventos
    private static void listEvents() {
        if (events.isEmpty()) {
            System.out.println("No hay eventos para mostrar.");
            return;
        }

        for (int i = 0; i < events.size(); i++) {
            System.out.println("[" + (i + 1) + "] " + events.get(i));
        }
    }

    // Método para marcar/desmarcar tarea como completada
    private static void toggleTaskCompletion(Scanner input) {
        if (events.isEmpty()) {
            System.out.println("No hay eventos disponibles.");
            return;
        }

        System.out.println("Selecciona el número del evento:");
        listEvents();
        int eventIndex = getIndexFromUser(input, events.size());

        if (eventIndex != -1) {
            PerezAriadnaEvent selectedEvent = events.get(eventIndex);

            if (selectedEvent.getTasks().isEmpty()) {
                System.out.println("El evento no tiene tareas.");
                return;
            }

            System.out.println("Selecciona el número de la tarea:");
            for (int i = 0; i < selectedEvent.getTasks().size(); i++) {
                System.out.println("[" + (i + 1) + "] " + selectedEvent.getTasks().get(i));
            }

            int taskIndex = getIndexFromUser(input, selectedEvent.getTasks().size());
            if (taskIndex != -1) {
                PerezAriadnaEventTask task = selectedEvent.getTasks().get(taskIndex);
                task.completeTask();
                System.out.println("Tarea actualizada: " + task);
            }
        }
    }

    // Método para obtener el índice de una selección del usuario
    private static int getIndexFromUser(Scanner input, int size) {
        int index = -1; // -1 significa que no hay una selección válida

        if (input.hasNextInt()) {
            index = input.nextInt() - 1; // Restar 1 para obtener el índice correcto
            input.nextLine();

            if (index < 0 || index >= size) {
                System.out.println("Índice fuera de rango.");
                index = -1;
            }
        } else {
            System.out.println("Entrada no válida.");
            input.nextLine();
        }

        return index;
    }
}
