public class PerezAriadnaEventTask {
    private String description;
    private boolean completed;

    // Constructor
    public PerezAriadnaEventTask(String description) {
        this.description = description;
        this.completed = false;
    }

    // Metodo para saber si la Tarea esta completada
    public boolean isCompleted() {
        return completed;
    }

    // Estado de la Tarea completada o pendiente
    @Override
    public String toString() {
        return description + (completed ? " (completada)" : " (pendiente)");
    }

    public void completeTask() {
        this.completed = !this.completed;  // Cambia el estado de la tarea
    }

}