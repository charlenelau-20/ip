package chitchat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chitchat.exception.ChitChatException;

/**
 * Represents a task of type "chitchat.task.Event".
 */
public class Event extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Initializes an chitchat.task.Event task object with a description, start time ('from'), and end time ('to').
     *
     * @param description Description of task.
     * @param from Start time of task.
     * @param to End time of task.
     * @throws ChitChatException If 'from' or 'to' are not in the correct format.
     */
    public Event(String description, String from, String to) throws ChitChatException {
        super(description);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new ChitChatException("Invalid format! "
                    + "Please use: event <event name> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>.");
        }
    }

    /**
     * Initializes an chitchat.task.Event task object with a description, start time, end time, and completion status.
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
