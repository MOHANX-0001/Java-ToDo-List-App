import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {

    private String description;
    private boolean completed;
    private String priority;
    private String category;
    private LocalDateTime createdAt;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public Task(String description, boolean completed, String priority, String category, LocalDateTime createdAt) {

        this.description = description;
        this.completed = completed;
        this.priority = priority;
        this.category = category;
        this.createdAt = createdAt;
    }


    public String getDescription() {
        return description;
    }


    public boolean isCompleted() {
        return completed;
    }


    public String getPriority() {
        return priority;
    }


    public String getCategory() {
        return category;
    }


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }


    public void setDescription(String description) {
        this.description = description;
    }


    public void setCompleted(boolean completed) {
        this.completed = completed;
    }


    public String toFileString() {

        return completed + "," + priority + "," + category + "," + description + "," + createdAt.format(formatter);
    }


    @Override
    public String toString() {

        String status = completed ? "[x]" : "[ ]";

        return status + " " + description
                + " | Priority: " + priority
                + " | Category: " + category
                + " | Created: " + createdAt.format(formatter);
    }
}
