/**
 * Represents a task of type "Deadline".
 */
public class Deadline extends Task {

    protected String by;

    /**
     * Initializes a Deadline task object with a description and a specified deadline ('by').
     *
     * @param description Description of task.
     * @param by Deadline of task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Initializes a Deadline task object with a description, a specified deadline ('by'), and completion status.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     * @param isDone Completion status of task.
     */
    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}

