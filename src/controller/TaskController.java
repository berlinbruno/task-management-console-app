package controller;

import java.util.List;

import dto.TaskDto;
import service.TaskService;

public class TaskController {
    private TaskService taskService = new TaskService();

    public TaskDto getTaskById(String id) {
        return taskService.getTaskById(id);
    }

    public List<TaskDto> getAllTasks() {
        return taskService.getAllTasks();

    }

    public List<TaskDto> getTasksByPriority() {
        return taskService.getTasksByPriority();
    }

    public List<TaskDto> getTasksByStatus() {
        return taskService.getTasksByStatus();
    }

    public String addTask(TaskDto newTask) {
        if (taskService.addTask(newTask)) {
            return "Added Successfully";
        } else {
            return "Failed";
        }

    }

    public String editTaskById(String id, TaskDto updatedTask) {
        if (taskService.editTaskById(id, updatedTask)) {
            return "Updated Successfully";
        } else {
            return "Failed";
        }

    }

    public String removeTaskById(String id) {
        if (taskService.removeTask(id)) {
            return "Removed Successfully";
        } else {
            return "Failed";
        }
    }

}