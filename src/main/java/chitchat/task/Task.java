package chitchat.task;

/**
 * Represents the various tasks.
 * Each task consists of a description and a completion status.
 * Tasks can be marked as done or not done.
 */
public class Task {
    protected String description;
    protected boolean isDone;

    /**
     * Initializes a Task object with a description of the task.
     * The default completion status of a task is set to be not done.
     *
     * @param description Description of task.
     */
    public Task(String description) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = false;
    }

    /**
     * Initializes a Task object with a description and completion status of the task.
     *
     * @param description Description of task.
     * @param isDone Completion status of task.
     */
    public Task(String description, boolean isDone) {
        assert description != null : "Task description should not be null";
        this.description = description;
        this.isDone = isDone;
    }

    /**
     * Returns the completion status icon for a task.
     * Returns an "X" if the task is done and returns a " " (a space) if the task is not done.
     *
     * @return A string representing the completion status of the task.
     */
    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    /**
     * Marks a task as done.
     */
    public void setDone() {
        isDone = true;
    }

    /**
     * Marks a task as not done.
     */
    public void setNotDone() {
        isDone = false;
    }

    /**
     * Formats the task to be saved into a file.
     *
     * @return A string representing the task in file format.
     */
    public String toFileFormat() {
        assert description != null : "Task description should not be null";
        return (isDone ? 1 : 0) + " | " + description;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
