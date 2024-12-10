import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class PerezAriadnaEvent {
    private String title;
    private LocalDate date;
    private Priority priority;
    private ArrayList<PerezAriadnaEventTask> tasks;

    // Enum para la prioridad HIGH,MEDIUM,LOW
    public enum Priority {
        HIGH, MEDIUM, LOW
    }

    // Constructor
    public PerezAriadnaEvent(String title, LocalDate date, Priority priority) {
        this.title = title;
        this.date = date;
        this.priority = priority;
        this.tasks = new ArrayList<>();
    }

    public static PerezAriadnaEvent createEvent() {
        Scanner input = new Scanner(System.in);

        System.out.print("Ingresa el título del evento: ");
        String title = input.nextLine();

        System.out.print("Ingresa la fecha del evento (yyyy-MM-dd): ");
        LocalDate date = null;
        while (date == null) {
            try {
                date = LocalDate.parse(input.nextLine());
            } catch (Exception e) {
                System.out.print("Formato de fecha inválido. Intentalo de nuevo (yyyy-MM-dd): ");
            }
        }

        System.out.print("Ingresa la prioridad del evento (HIGH, MEDIUM, LOW): ");
        Priority priority = null;
        while (priority == null) {
            try {
                priority = Priority.valueOf(input.nextLine().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.print("Prioridad inválida. Intentalo de nuevo (HIGH, MEDIUM, LOW): ");
            }
        }

        PerezAriadnaEvent newEvent = new PerezAriadnaEvent (title, date, priority);
        System.out.println("\nEvento creado: " + newEvent);

        // Agrega las tareas al Evento
        System.out.println("¿Deseas agregar tareas al evento? (s/n)");
        if (input.nextLine().equalsIgnoreCase("s")) {
            boolean addingTasks = true;
            while (addingTasks) {
                System.out.print("Ingresa la descripción de la tarea: ");
                String taskDescription = input.nextLine();
                PerezAriadnaEventTask task = new PerezAriadnaEventTask(taskDescription);
                newEvent.addTask(task);

                System.out.println("¿Agregar otra tarea? (s/n)");
                addingTasks = input.nextLine().equalsIgnoreCase("s");
            }
        }

        return newEvent;
    }

    // Metodo añadir tarea
    public void addTask(PerezAriadnaEventTask task) {
        tasks.add(task);
    }
    // Devuelve las tareas
    public ArrayList<PerezAriadnaEventTask> getTasks() {
        return tasks;
    }

    // Getter
    public String getTitle() {
        return title;
    }

    // toString
    @Override
    public String toString() {
        int totalTasks = tasks.size();
        int completedTasks = (int) tasks.stream().filter(PerezAriadnaEventTask::isCompleted).count();

        return "Evento: " + title +
                "\nFecha: " + date +
                "\nPrioridad: " + priority +
                "\nTareas: " + completedTasks + "/" + totalTasks + " completadas";
    }
}


