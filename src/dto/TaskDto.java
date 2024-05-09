package dto;

import java.util.UUID;

import model.Priority;
import model.Status;

public class TaskDto {
    private String id;
    private String title;
    private String description;
    private String priority;
    private String status;

    public TaskDto(){
        
    }

    public TaskDto(String title, String description, String priority, String status) {
        this.id = UUID.randomUUID().toString();
        this.title = title;
        this.description = description;
        this.priority = setPriority(priority);
        this.status = setStatus(status);
        ;
    }

    public TaskDto(String id, String title, String description, String priority, String status) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPriority() {
        return priority;
    }

    public String setPriority(String priority) {
        switch (priority) {
            case "1":
                return priority = Priority.LOW.name();
            case "2":
                return priority = Priority.MEDIUM.name();
            case "3":
                return priority = Priority.HIGH.name();
            case "LOW":
                return priority = Priority.LOW.name();
            case "MEDIUM":
                return priority = Priority.MEDIUM.name();
            case "HIGH":
                return priority = Priority.HIGH.name();
            default:
                return priority = Priority.MEDIUM.name();
        }
    }

    public String getStatus() {
        return status;
    }

    public String setStatus(String status) {
        switch (status) {
            case "1":
                return status = Status.PENDING.name();
            case "2":
                return status = Status.IN_PROGRESS.name();

            case "3":
                return status = Status.COMPLETED.name();

            case "PENDING":
                return status = Status.PENDING.name();

            case "IN_PROGRESS":
                return status = Status.IN_PROGRESS.name();

            case "COMPLECTED":
                return status = Status.COMPLETED.name();

            default:
                return status = Status.PENDING.name();

        }
    }

    @Override
    public String toString() {
        return "Id = " + id + " | title = " + title + " | description = " + description + " | priority = " + priority
                + " | status = " + status;
    }
}
