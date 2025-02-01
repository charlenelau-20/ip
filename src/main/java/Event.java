/**
 * Represents a task of type "Event".
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Initializes an Event task object with a description, start time, and end time.
     *
     * @param description Description of task.
     * @param from Start time of task.
     * @param to End time of task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Initializes an Event task object with a description, start time, end time, and completion status.
     *
     * @param description Description of task.
     * @param from Start time of task.
     * @param to End time of task.
     * @param isDone Completion status of task.
     */
    public Event(String description, String from, String to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
