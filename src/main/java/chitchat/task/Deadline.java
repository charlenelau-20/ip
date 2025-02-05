package chitchat.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import chitchat.exception.ChitChatException;

/**
 * Represents a task of type "Deadline".
 */
public class Deadline extends Task {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy, h:mm a");

    private LocalDateTime by;

    /**
     * Initializes a Deadline task object with a description and a specified deadline ('by').
     *
     * @param description Description of task.
     * @param by Deadline of task.
     * @throws ChitChatException If 'by' is not in the correct format.
     */
    public Deadline(String description, String by) throws ChitChatException {
        super(description);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
        } catch (DateTimeParseException e) {
            throw new ChitChatException("Invalid format! Please use: deadline <task> /by <yyyy-MM-dd HHmm>.");
        }
    }

    /**
     * Initializes a Deadline task object with a description, a specified deadline ('by'),
     * and completion status.
     *
     * @param description Description of task.
     * @param by Deadline of task.
     * @param isDone Completion status of task.
     */
    public Deadline(String description, LocalDateTime by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    @Override
    public String toFileFormat() {
        return "D | " + super.toFileFormat() + " | " + by.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(FORMATTER) + ")";
    }
}

