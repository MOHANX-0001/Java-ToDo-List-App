import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        TaskManager manager = new TaskManager();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n===== TO DO LIST MENU =====");
            System.out.println("1. View Tasks");
            System.out.println("2. Add Task");
            System.out.println("3. Mark Complete");
            System.out.println("4. Edit Task");
            System.out.println("5. Delete Task");
            System.out.println("6. Search Task");
            System.out.println("7. Show Statistics");
            System.out.println("8. Filter by Category");
            System.out.println("9. Exit");

            System.out.print("Choose an option: ");

            int choice = getSafeInt(sc);

            switch (choice) {

                case 1 -> manager.viewTasks();

                case 2 -> {

                    System.out.print("Enter task description: ");
                    String desc = sc.nextLine();

                    System.out.print("Enter priority (LOW/MEDIUM/HIGH): ");
                    String priority = sc.nextLine().toUpperCase();

                    System.out.print("Enter category (WORK/STUDY/PERSONAL/HEALTH): ");
                    String category = sc.nextLine().toUpperCase();

                    manager.addTask(desc, priority, category);
                }

                case 3 -> {

                    System.out.print("Task number to complete: ");
                    manager.markComplete(getSafeInt(sc) - 1);
                }

                case 4 -> {

                    System.out.print("Task number to edit: ");
                    int index = getSafeInt(sc) - 1;

                    System.out.print("Enter new description: ");
                    String newDesc = sc.nextLine();

                    manager.editDescription(index, newDesc);
                }

                case 5 -> {

                    System.out.print("Task number to delete: ");
                    manager.deleteTask(getSafeInt(sc) - 1);
                }

                case 6 -> {

                    System.out.print("Enter keyword to search: ");
                    String keyword = sc.nextLine();

                    manager.searchTask(keyword);
                }

                case 7 -> manager.showStats();

                case 8 -> {

                    System.out.print("Enter category to filter: ");
                    String category = sc.nextLine();

                    manager.filterByCategory(category);
                }

                case 9 -> {

                    manager.saveTasks();
                    System.out.println("Tasks saved. Goodbye!");
                    return;
                }

                default -> System.out.println("Invalid option.");
            }
        }
    }


    private static int getSafeInt(Scanner sc) {

        while (true) {

            try {

                int val = sc.nextInt();
                sc.nextLine();
                return val;

            } catch (InputMismatchException e) {

                System.out.print("Invalid input. Enter a number: ");
                sc.nextLine();
            }
        }
    }
}
