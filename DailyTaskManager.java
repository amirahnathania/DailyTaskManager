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
    private Stack<String> completedTasks = new Stack<>();

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

    public void updateTask(String oldTask, String newTask) {
        TaskNode temp = head;
        while (temp != null) {
            if (temp.task.equals(oldTask)) {
                temp.task = newTask;
                System.out.println("✅ Task updated!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("⚠ Task not found.");
    }

    public void markTaskAsCompleted(String task) {
        TaskNode temp = head;
        while (temp != null) {
            if (temp.task.equals(task)) {
                completedTasks.push(task);
                removeTask(task);
                System.out.println("🎉 Task marked as completed!");
                return;
            }
            temp = temp.next;
        }
        System.out.println("⚠ Task not found.");
    }

    public void undoCompletedTask() {
        if (!completedTasks.isEmpty()) {
            addTask(completedTasks.pop());
            System.out.println("⏪ Task undone!");
        } else {
            System.out.println("⚠ No completed tasks to undo.");
        }
    }

    public void displayTasks() {
        if (head == null) {
            System.out.println("⚠ No tasks in the list.");
            return;
        }
        System.out.println("📋 **Task List (Linked List)**");
        TaskNode temp = head;
        int num = 1;
        while (temp != null) {
            System.out.println(num++ + ". " + temp.task);
            temp = temp.next;
        }
    }
}

// Kelas utama
public class DailyTaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[5];
        Stack<String> completedTasksArray = new Stack<>();
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
                if (mainChoice == 1) { // Array Menu
                    System.out.println("\n===== 📌 ARRAY TASK MENU 📌 =====");
                    System.out.println("1. View tasks");
                    System.out.println("2. Add task");
                    System.out.println("3. Update task");
                    System.out.println("4. Mark task as complete");
                    System.out.println("5. Undo completed task");
                    System.out.println("6. Remove task");
                    System.out.println("7. Return to Main Menu");
                    System.out.print("💡 Choose an option: ");
                    int arrayChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (arrayChoice == 7) break;

                    switch (arrayChoice) {
                        case 1: // View all tasks
                            System.out.println("📋 Task List:");
                            boolean hasTasks = false;
                            for (int i = 0; i < tasks.length; i++) {
                                if (tasks[i] != null) {
                                    System.out.println((i + 1) + ". " + tasks[i]);
                                    hasTasks = true;
                                }
                            }
                            if (!hasTasks) {
                                System.out.println("⚠ No tasks available.");
                            }
                            break;

                        case 2: // Add task
                            System.out.print("➕ Enter new task: ");
                            String newTask = scanner.nextLine();
                            boolean added = false;
                            for (int i = 0; i < tasks.length; i++) {
                                if (tasks[i] == null) {
                                    tasks[i] = newTask;
                                    System.out.println("✅ Task added!");
                                    added = true;
                                    break;
                                }
                            }
                            if (!added) {
                                System.out.println("⚠ Task list is full!");
                            }
                            break;

                        case 3: // Update task
                            System.out.print("✏ Enter task number to update: ");
                            int taskIndex = scanner.nextInt();
                            scanner.nextLine();
                            if (taskIndex > 0 && taskIndex <= tasks.length && tasks[taskIndex - 1] != null) {
                                System.out.print("📝 Enter new task: ");
                                tasks[taskIndex - 1] = scanner.nextLine();
                                System.out.println("✅ Task updated!");
                            } else {
                                System.out.println("⚠ Invalid task number.");
                            }
                            break;

                        case 4: // Mark task as completed
                            System.out.print("✅ Enter task number to complete: ");
                            int index = scanner.nextInt();
                            scanner.nextLine();
                            if (index > 0 && index <= tasks.length && tasks[index - 1] != null) {
                                completedTasksArray.push(tasks[index - 1]);
                                tasks[index - 1] = null;
                                System.out.println("🎉 Task marked as completed!");
                            } else {
                                System.out.println("⚠ Invalid task number.");
                            }
                            break;

                        case 5: // Undo completed task
                            if (!completedTasksArray.isEmpty()) {
                                String undoneTask = completedTasksArray.pop();
                                for (int i = 0; i < tasks.length; i++) {
                                    if (tasks[i] == null) {
                                        tasks[i] = undoneTask;
                                        System.out.println("⏪ Task undone: " + undoneTask);
                                        break;
                                    }
                                }
                            } else {
                                System.out.println("⚠ No completed tasks to undo.");
                            }
                            break;
                    }
                } else if (mainChoice == 2) { // Linked List Menu
                    taskList.displayTasks();
                }
            }
        }
    }
}
