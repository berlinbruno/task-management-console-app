package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.db;
import dto.TaskDto;

public class TaskService {

    public Boolean addTask(TaskDto newTask) {

        String query = "INSERT INTO tasks (id, title, description, priority, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, newTask.getId());
            statement.setString(2, newTask.getTitle());
            statement.setString(3, newTask.getDescription());
            statement.setString(4, newTask.getPriority());
            statement.setString(5, newTask.getStatus());

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting new task: " + e.getMessage());
        }
        return false;
    }

    public Boolean editTaskById(String id, TaskDto updatedTask) {

        String query = "UPDATE tasks SET title = ?, description = ?, priority = ?, status = ? WHERE id = ?;";

        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, updatedTask.getTitle());
            statement.setString(2, updatedTask.getDescription());
            statement.setString(3, updatedTask.getPriority());
            statement.setString(4, updatedTask.getStatus());
            statement.setString(5, id);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error updating task: " + e.getMessage());
        }
        return false;

    }

    public Boolean removeTask(String id) {
        String query = "DELETE FROM tasks where id = ?";

        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);

            int rowsInserted = statement.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error deleting task: " + e.getMessage());
        }
        return false;
    }

    public TaskDto getTaskById(String id) {

        String query = "SELECT * FROM tasks where id = ?";

        try (Connection connection = db.getConnection();
                PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, id);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                TaskDto task = new TaskDto(resultSet.getString("id"), resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("priority"),
                        resultSet.getString("status"));

                return task;
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving task: " + e.getMessage());
        }
        return null;
    }

    public List<TaskDto> getAllTasks() {

        List<TaskDto> taskList = new ArrayList<>();
        String query = "SELECT * FROM tasks";

        try (Connection connection = db.getConnection();
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TaskDto task = new TaskDto(resultSet.getString("id"), resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("priority"),
                        resultSet.getString("status"));

                taskList.add(task);
            }

            return taskList;
        } catch (SQLException e) {
            System.err.println("Error retrieving tasks: " + e.getMessage());
        }
        return null;
    }

    public List<TaskDto> getTasksByPriority() {
        List<TaskDto> taskList = new ArrayList<>();

        String query = "SELECT * FROM tasks ORDER BY CASE WHEN priority = 'COMPLETE' THEN 1 WHEN priority = 'IN_PROGRESS' THEN 2 WHEN priority = 'PENDING' THEN 3 END";

        try (Connection connection = db.getConnection();
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TaskDto task = new TaskDto(resultSet.getString("id"), resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("priority").toUpperCase(),
                        resultSet.getString("status"));

                taskList.add(task);
            }

            return taskList;
        } catch (SQLException e) {
            System.err.println("Error retrieving tasks: " + e.getMessage());
        }
        return null;
    }

    public List<TaskDto> getTasksByStatus() {
        List<TaskDto> taskList = new ArrayList<>();
        String query = "SELECT * FROM tasks ORDER BY CASE WHEN status = 'HIGH' THEN 1 WHEN status = 'MEDIUM' THEN 2 WHEN status = 'LOW' THEN 3 END";

        try (Connection connection = db.getConnection();
                Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                TaskDto task = new TaskDto(resultSet.getString("id"), resultSet.getString("title"),
                        resultSet.getString("description"),
                        resultSet.getString("priority"),
                        resultSet.getString("status"));

                taskList.add(task);
            }

            return taskList;
        } catch (SQLException e) {
            System.err.println("Error retrieving tasks: " + e.getMessage());
        }
        return null;
    }

}
