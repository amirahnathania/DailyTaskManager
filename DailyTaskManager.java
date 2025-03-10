import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

// ANSI Escape Codes untuk warna terminal
class Colors {
    public static final String RESET = "\u001B[0m";
    public static final String[] TEXT_COLORS = {
        "\u001B[31m", "\u001B[32m", "\u001B[33m",
        "\u001B[34m", "\u001B[35m", "\u001B[36m"
    };

    public static String getRandomColor() {
        Random random = new Random();
        return TEXT_COLORS[random.nextInt(TEXT_COLORS.length)];
    }
}

// Node untuk Linked List
class TaskNode {
    String task;
    TaskNode next;

    public TaskNode(String task) {
        this.task = task;
        this.next = null;
    }
}

// Linked List untuk daftar tugas
class TaskList {
    private TaskNode head;

    public void addTask(String task) {
        TaskNode newNode = new TaskNode(task);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
        System.out.println(Colors.getRandomColor() + "✅ Task added successfully!" + Colors.RESET);
    }

    public void removeTask(String task) {
        if (head == null) {
            System.out.println("⚠ No tasks available.");
            return;
        }
        if (head.task.equals(task)) {
            head = head.next;
            System.out.println("🗑 Task removed!");
            return;
        }
        TaskNode temp = head;
        while (temp.next != null && !temp.next.task.equals(task)) {
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("⚠ Task not found.");
        } else {
            temp.next = temp.next.next;
            System.out.println("🗑 Task removed!");
        }
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("⚠ No tasks in the list.");
            return;
        }
        System.out.println("📋 Task List 📋");
        TaskNode temp = head;
        int num = 1;
        while (temp != null) {
            System.out.println(num++ + ". " + temp.task);
            temp = temp.next;
        }
    }
}

public class DailyTaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[5];
        boolean[] completed = new boolean[5];
        TaskList taskList = new TaskList();

        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║" + Colors.getRandomColor() + " 🚀 WELCOME TO FUNFACTS DAILY TASK MANAGER 🚀 " + Colors.RESET + "║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println("📅 Manage your tasks efficiently and stay organized!\n");

        while (true) {
            System.out.println("\n===== 📌 MAIN MENU 📌 =====");
            System.out.println("1. Array Task Manager");
            System.out.println("2. Linked List Task Manager");
            System.out.println("3. Exit");
            System.out.print("💡 Choose an option: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine();

            if (mainChoice == 3) {
                System.out.println("🚀 Exiting... Goodbye!");
                break;
            }

            while (true) {
                if (mainChoice == 1) { // Array Task Manager
                    System.out.println("\n===== 📌 ARRAY TASK MENU 📌 =====");
                    System.out.println("1. View tasks");
                    System.out.println("1. Add new task");
                    System.out.println("2. Update task");
                    System.out.println("3. Complete task");
                    System.out.println("4. Undo task complete");
                    System.out.println("5. Back to main menu");
                    System.out.print("💡 Choose an option: ");
                    int arrayChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (arrayChoice == 5) break;

                    switch (arrayChoice) {
                        case 1: // View tasks
                            System.out.println("📋 Task List:");
                            boolean hasTasks = false;
                            for (int i = 0; i < tasks.length; i++) {
                                if (tasks[i] != null) {
                                    String status = completed[i] ? "(Completed)" : "";
                                    System.out.println((i + 1) + ". " + tasks[i] + " " + status);
                                    hasTasks = true;
                                }
                            }
                            if (!hasTasks) System.out.println("⚠ No tasks available.");
                            break;

                        case 2: // Add task
                            boolean added = false;
                            for (int i = 0; i < tasks.length; i++) {
                                if (tasks[i] == null) {
                                    System.out.print("➕ Enter new task: ");
                                    tasks[i] = scanner.nextLine();
                                    System.out.println("✅ Task added!");
                                    added = true;
                                    break;
                                }
                            }
                            if (!added) System.out.println("⚠ Task list is full (5 tasks max)!");
                            break;

                        case 3: // Update task
                            System.out.print("✏ Enter task number to update: ");
                            int updateIndex = scanner.nextInt() - 1;
                            scanner.nextLine();
                            if (updateIndex >= 0 && updateIndex < tasks.length && tasks[updateIndex] != null) {
                                System.out.print("✏ Enter new task: ");
                                tasks[updateIndex] = scanner.nextLine();
                                System.out.println("✅ Task updated!");
                            } else {
                                System.out.println("⚠ Invalid task number.");
                            }
                            break;

                        case 4: // Complete task
                            System.out.print("✔ Enter task number to complete: ");
                            int completeIndex = scanner.nextInt() - 1;
                            if (completeIndex >= 0 && completeIndex < tasks.length && tasks[completeIndex] != null) {
                                completed[completeIndex] = true;
                                System.out.println("✅ Task marked as complete!");
                            } else {
                                System.out.println("⚠ Invalid task number.");
                            }
                            break;

                        case 5: // Undo complete
                            System.out.print("⏪ Enter task number to undo complete: ");
                            int undoIndex = scanner.nextInt() - 1;
                            if (undoIndex >= 0 && undoIndex < tasks.length && completed[undoIndex]) {
                                completed[undoIndex] = false;
                                System.out.println("⏪ Task completion undone!");
                            } else {
                                System.out.println("⚠ Invalid task number.");
                            }
                            break;
                    }
                } else if (mainChoice == 2) { // Linked List Task Manager
                    System.out.println("\n===== 📌 LINKED LIST TASK MENU 📌 =====");
                    System.out.println("1. Add task");
                    System.out.println("2. Remove task");
                    System.out.println("3. View tasks");
                    System.out.println("4. Back to main menu");
                    System.out.print("💡 Choose an option: ");
                    int linkedListChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (linkedListChoice == 4) break;

                    switch (linkedListChoice) {
                        case 1:
                            System.out.print("➕ Enter new task: ");
                            taskList.addTask(scanner.nextLine());
                            break;
                        case 2:
                            System.out.print("🗑 Enter task to remove: ");
                            taskList.removeTask(scanner.nextLine());
                            break;
                        case 3:
                            taskList.displayTasks();
                            break;
                    }
                }
            }
        }
    }
}
