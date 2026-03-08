import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class TaskManager {


    private ArrayList<Task> tasks = new ArrayList<>();

    private final String FILE_NAME = "tasks.txt";

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");


    public TaskManager() {

        loadTasks();
    }


    public void addTask(String desc, String priority, String category) {

        tasks.add(new Task(desc, false, priority, category, LocalDateTime.now()));

        saveTasks();
    }


    public void viewTasks() {

        if (tasks.isEmpty()) {

            System.out.println("No tasks found.");
            return;
        }


        for (int i = 0; i < tasks.size(); i++) {

            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }


    public void markComplete(int index) {

        if (isValidIndex(index)) {

            tasks.get(index).setCompleted(true);
            saveTasks();
        }
    }


    public void deleteTask(int index) {

        if (isValidIndex(index)) {

            tasks.remove(index);
            saveTasks();
        }
    }


    public void editDescription(int index, String newDesc) {

        if (isValidIndex(index)) {

            tasks.get(index).setDescription(newDesc);
            saveTasks();
        }
    }


    public void searchTask(String keyword) {

        boolean found = false;


        for (int i = 0; i < tasks.size(); i++) {

            if (tasks.get(i).getDescription().toLowerCase().contains(keyword.toLowerCase())) {

                System.out.println((i + 1) + ". " + tasks.get(i));
                found = true;
            }
        }


        if (!found) {

            System.out.println("No matching tasks found.");
        }
    }


    public void filterByCategory(String category) {

        boolean found = false;


        for (Task t : tasks) {

            if (t.getCategory().equalsIgnoreCase(category)) {

                System.out.println(t);
                found = true;
            }
        }


        if (!found) {

            System.out.println("No tasks in this category.");
        }
    }


    public void showStats() {

        int completed = 0;


        for (Task t : tasks) {

            if (t.isCompleted()) {

                completed++;
            }
        }


        int total = tasks.size();
        int pending = total - completed;


        System.out.println("\n===== TASK STATISTICS =====");
        System.out.println("Total Tasks: " + total);
        System.out.println("Completed: " + completed);
        System.out.println("Pending: " + pending);
    }


    private boolean isValidIndex(int index) {

        if (index >= 0 && index < tasks.size()) {

            return true;
        }


        System.out.println("Invalid task number.");
        return false;
    }


    public void saveTasks() {

        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {


            for (Task t : tasks) {

                writer.println(t.toFileString());
            }


        } catch (IOException e) {

            System.out.println("Error saving tasks.");
        }
    }


    private void loadTasks() {


        File file = new File(FILE_NAME);


        if (!file.exists()) {

            return;
        }


        try (Scanner scanner = new Scanner(file)) {


            while (scanner.hasNextLine()) {


                String line = scanner.nextLine();


                String[] parts = line.split(",", 5);


                if (parts.length == 5) {


                    boolean completed = Boolean.parseBoolean(parts[0]);

                    String priority = parts[1];

                    String category = parts[2];

                    String desc = parts[3];

                    LocalDateTime time = LocalDateTime.parse(parts[4], formatter);


                    tasks.add(new Task(desc, completed, priority, category, time));
                }
            }


        } catch (Exception e) {

            System.out.println("Error loading tasks.");
        }
    }
}
