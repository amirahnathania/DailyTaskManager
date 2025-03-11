import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

// Kelas Colors untuk mendefinisikan kode warna ANSI di terminal
class Colors {
    public static final String RESET = "\u001B[0m"; // Mengatur ulang warna teks
    public static final String[] TEXT_COLORS = { // Array warna untuk teks output
        "\u001B[31m", "\u001B[32m", "\u001B[33m", // Merah, Hijau, Kuning
        "\u001B[34m", "\u001B[35m", "\u001B[36m" // Biru, Ungu, Cyan
    };

    // Method untuk mengambil warna secara acak dari Array TEXT_COLORS
    public static String getRandomColor() {
        Random random = new Random(); // Membuat objek Random 
        return TEXT_COLORS[random.nextInt(TEXT_COLORS.length)]; // Mengembalikan warna acak
    }
}

// Kelas TaskNode untuk merepresentasikan elemen dalam Linked List
class TaskNode {
    String task; // Menyimpan teks tugas
    TaskNode next; // Menyimpan referensi ke node berikutnya dalam linked list

    // Konstruktor untuk menginisialisasi node dengan tugas tertentu
    public TaskNode(String task) {
        this.task = task; // Menyimpan teks tugas
        this.next = null; // Awalnya node tidak memiliki referensi ke node lain
    }
}

// Kelas TaskList untuk mengelola daftar tugas menggunakan Linked List
class TaskList {
    private TaskNode head; // Node pertama (head) dalam Linked List

    // Method untuk menambahkan tugas ke dalam daftar (Linked List)
    public void addTask(String task) {
        TaskNode newNode = new TaskNode(task); // Membuat node baru
        if (head == null) {
            head = newNode; // Jika daftar kosong, jadikan node ini sebagai head
        } else {
            TaskNode temp = head;
            while (temp.next != null) { // Menelusuri sampai akhir daftar
                temp = temp.next;
            }
            temp.next = newNode; // Menambahkan node di akhir daftar 
        }
        System.out.println(Colors.getRandomColor() + "✅ Task added successfully!" + Colors.RESET);
    }

    // Method untuk menghapus tugas dari daftar (Linked List)
    public void removeTask(String task) {
        if (head == null) {
            System.out.println("⚠ No tasks available."); // Jika daftar kosong
            return;
        }
        if (head.task.equals(task)) { // Jika tugas ada di head
            head = head.next; // Hapus tugas dengan mengubah head ke node berikutnya
            System.out.println("🗑 Task removed!");
            return;
        }
        TaskNode temp = head;
        while (temp.next != null && !temp.next.task.equals(task)) { // Menelusuri daftar
            temp = temp.next;
        }
        if (temp.next == null) {
            System.out.println("⚠ Task not found."); // Jika tugas tidak ditemukan
        } else {
            temp.next = temp.next.next; // Menghapus tugas dari daftar
            System.out.println("🗑 Task removed!");
        }
    }

    // Method untuk menampilkan daftar tugas yang ada
    public void displayTasks() {
        if (head == null) {
            System.out.println("⚠ No tasks in the list."); // Jika daftar kosong
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

// Kelas utama untuk program Daily Task Manager
public class DailyTaskManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] tasks = new String[5]; // Array untuk menyimpan tugas (maksimal 5 Tugas)
        boolean[] completed = new boolean[5]; // Menyimpan status penyelesaian tugas
        TaskList taskList = new TaskList(); // Objek Linked List Task manager

        // Menampilkan banner aplikasi saat pertama kali dijalankan
        System.out.println("╔══════════════════════════════════════════════╗");
        System.out.println("║" + Colors.getRandomColor() + " 🚀 WELCOME TO FUNFACTS DAILY TASK MANAGER 🚀 " + Colors.RESET + "║");
        System.out.println("╚══════════════════════════════════════════════╝");
        System.out.println("📅 Manage your tasks efficiently and stay organized!\n");

        while (true) { // Loop utama
            // Menampilkan menu utama
            System.out.println(Colors.getRandomColor() + "\n===== 📌 MAIN MENU 📌 =====" + Colors.RESET);
            System.out.println("1. Array Task Manager");
            System.out.println("2. Linked List Task Manager");
            System.out.println("3. Exit");
            System.out.print("💡 Choose an option: ");
            int mainChoice = scanner.nextInt();
            scanner.nextLine(); // Membersihkan buffer input

            if (mainChoice == 3) { // Jika pengguna memilih leluar dari program
                System.out.println("\n🎉🎈 Thank you for using FunFacts Daily Task Manager! 🎈🎉");
                System.out.println("┌( ಠ_ಠ)┘  Goodbye! Stay productive!  └(ಠ_ಠ )┐");
                System.out.println("🌟 May your tasks be easy and your breaks be long! 🌟");
                System.out.println("\uD83D\uDE80 🚀 Exiting... Have a great day! 🚀 \uD83D\uDE80");
                break;
            }

            while (true) {
                if (mainChoice == 1) { // Menu tugas menggunakan Array
                    System.out.println(Colors.getRandomColor() + "\n===== 📌 ARRAY TASK MENU 📌 =====" + Colors.RESET);
                    System.out.println("1. View tasks");
                    System.out.println("2. Add new task");
                    System.out.println("3. Update task");
                    System.out.println("4. Complete task");
                    System.out.println("5. Undo task complete");
                    System.out.println("6. Back to main menu");
                    System.out.print("💡 Choose an option: ");
                    int arrayChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (arrayChoice == 6) { // Kembali ke menu utama
                        break;
                    }

                    switch (arrayChoice) {
                        case 1: // Menampilkan daftar tugas
                            System.out.println(Colors.getRandomColor() + "📋 Task List 📋:" + Colors.RESET);
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

                        case 2: // Menambahkan tugas baru
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

                        case 6: // back to main menu
                            break;
                    }
                } else if (mainChoice == 2) { // Mengelola tugas menggunakan Linked List 
                    System.out.println(Colors.getRandomColor() + "\n===== 📌 LINKED LIST TASK MENU 📌 =====" + Colors.RESET);
                    System.out.println("1. Add task");
                    System.out.println("2. Remove task");
                    System.out.println("3. View tasks");
                    System.out.println("4. Back to main menu");
                    System.out.print("💡 Choose an option: ");
                    int linkedListChoice = scanner.nextInt();
                    scanner.nextLine();

                    if (linkedListChoice == 4) break; // Kembali ke menu utama

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
