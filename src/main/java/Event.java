import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task of type "Event".
 */
public class Event extends Task {
    private LocalDateTime from;
    private LocalDateTime to;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    /**
     * Initializes an Event task object with a description, start time, and end time.
     *
     * @param description Description of task.
     * @param from Start time of task.
     * @param to End time of task.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    /**
     * Initializes an Event task object with a description, start time, end time, and completion status.
     *
     * @param description Description of task.
     * @param from Start time of task.
     * @param to End time of task.
     * @param isDone Completion status of task.
     */
    public Event(String description, LocalDateTime from, LocalDateTime to, boolean isDone) {
        super(description, isDone);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + super.toFileFormat() + " | " + from.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
                + " | " + to.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(FORMATTER) + " to: " + to.format(FORMATTER) + ")";
    }
}
