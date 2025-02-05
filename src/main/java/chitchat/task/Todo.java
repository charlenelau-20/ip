package chitchat.task;

/**
 * Represents a task of type "Todo".
 */
public class Todo extends Task {

    /**
     * Initializes a Todo task object with a description of the task.
     *
     * @param description Description of task.
     */
    public Todo(String description) {
        super(description);
    }

    /**
     * Initializes a Todo task object with a description and completion status of the task.
     *
     * @param description Description of task.
     * @param isDone Completion status of task.
     */
    public Todo(String description, boolean isDone) {
        super(description, isDone);
    }

    @Override
    public String toFileFormat() {
        return "T | " + super.toFileFormat();
    }
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

