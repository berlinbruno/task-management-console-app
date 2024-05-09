package view;

import java.util.List;
import java.util.Scanner;

import controller.TaskController;
import dto.TaskDto;

public class TaskView {
    private Scanner scanner;
    private TaskController taskController;;

    public TaskView() {
        scanner = new Scanner(System.in);
        taskController = new TaskController();
    }

    public void showMenuAndGetChoice() {
        System.out.println("Task Manager Menu:");
        System.out.println("1. Add Task");
        System.out.println("2. Edit Task");
        System.out.println("3. Delete Task");
        System.out.println("4. View Task By Id");
        System.out.println("5. View All Tasks");
        System.out.println("6. View Tasks by Priority");
        System.out.println("7. View Tasks by Status");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        handleInput(choice);
    }

    public void showSecondaryMenu() {
        System.out.println("1. To Main Menu");
        System.out.println("2. Exit");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        handleSecondaryInput(choice);
    }

    public void handleInput(int choice) {
        List<TaskDto> taskList;
        TaskDto task;
        switch (choice) {
            case 1:
                displayMessage(taskController.addTask(handleTaskInput()));
                showMenuAndGetChoice();
                break;
            case 2:
                displayMessage(taskController.editTaskById(handleIdInput(), handleEditTaskInput()));
                showMenuAndGetChoice();
                break;
            case 3:
                displayMessage(taskController.removeTaskById(handleIdInput()));
                showMenuAndGetChoice();
                break;
            case 4:
                task = taskController.getTaskById(handleIdInput());
                displayTask(task);
                showSecondaryMenu();
                break;
            case 5:
                taskList = taskController.getAllTasks();
                displayTaskList(taskList);
                showSecondaryMenu();
                break;
            case 6:
                taskList = taskController.getTasksByPriority();
                displayTaskList(taskList);
                showSecondaryMenu();
                break;
            case 7:
                taskList = taskController.getTasksByStatus();
                displayTaskList(taskList);
                showSecondaryMenu();
                break;
            case 8:
                break;
            default:
                System.err.println("Enter a valid Choice");
                showMenuAndGetChoice();
        }
    }

    public void handleSecondaryInput(int choice) {
        switch (choice) {
            case 1:
                showMenuAndGetChoice();
                break;
            case 2:
                break;
            default:
                showMenuAndGetChoice();
                break;
        }
    }

    private String handleIdInput() {
        displayMessage("Enter the task id:");
        String id = scanner.nextLine();
        return id;
    }

    private TaskDto handleTaskInput() {
        displayMessage("Enter the task title:");
        String title = scanner.nextLine();

        displayMessage("Enter the task description:");
        String description = scanner.nextLine();

        displayMessage("Enter the task priority (1 - LOW | 2 - MEDIUM | 3 - HIGH):");
        String priority = scanner.next();

        scanner.nextLine();

        displayMessage("Enter the task status (1 - Pending | 2 - In Progress | 3 - Finished):");
        String status = scanner.next();

        TaskDto newTask = new TaskDto(title, description, priority, status);
        return newTask;
    }

    private TaskDto handleEditTaskInput() {
        displayMessage("Enter the task title:");
        String title = scanner.next();
        displayMessage("Enter the task description:");
        String description = scanner.next();
        displayMessage("Enter the task priority(1 - LOW | 2 - for MEDIUM | 3 - HIGH):");
        String priority = scanner.next();
        displayMessage("Enter the task status(1 - for Pending | 2 - for In Progress | 3 - for Finished):");
        String status = scanner.next();

        TaskDto newTask = new TaskDto(title, description, priority, status);
        return newTask;

    }

    private void displayMessage(String message) {
        System.out.println(message);

    }

    private void displayTask(TaskDto task) {

        if (task != null) {

            System.out.println(task.toString());
        } else {
            System.out.println("No Record Found");
        }

    }

    private void displayTaskList(List<TaskDto> taskList) {
        if (taskList != null) {

            for (TaskDto task : taskList) {
                System.out.println(task.toString());
            }
        } else {
            System.out.println("No Record Found");
        }
    }

}
