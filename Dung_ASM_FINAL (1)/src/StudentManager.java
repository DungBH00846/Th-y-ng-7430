import java.util.Scanner;
import java.util.Random;

public class StudentManager {
    private StudentQueue studentQueue = new StudentQueue(100000);
    private Scanner scanner = new Scanner(System.in);

    public void run() {
        int choice;
        do {
            System.out.println("\n--- Student Management System ---");
            System.out.println("0. Exit");
            System.out.println("1. Generate Random Students");
            System.out.println("2. Display All Students");
            System.out.println("3. Add Student");
            System.out.println("4. Edit Student");
            System.out.println("5. Delete Student");
            System.out.println("6. Sort Students By Marks");
            System.out.println("    1. Bubble Sort");
            System.out.println("    2. Quick Sort");
            System.out.println("7. Search Student By ID");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            switch (choice) {
                case 1 -> generateRandomStudents();
                case 2 -> displayAllStudents();
                case 3 -> addStudent();
                case 4 -> editStudent();
                case 5 -> deleteStudent();
                case 6 -> sortStudents();
                case 7 -> searchStudentById();
                case 0 -> System.out.println("Exiting program.");
                default -> System.out.println("Invalid choice. Try again!");
            }
        } while (choice != 0);
    }

    private void generateRandomStudents() {
        Random random = new Random();
        System.out.print("Enter the number of students to generate (1–100000): ");
        int count = scanner.nextInt();
        if (count < 1 || count > 100000) {
            System.out.println("Error: Number of students must be between 1 and 100000.");
            return;
        }
        for (int i = 0; i < count; i++) {
            int id = i + 1;
            String name = "Student" + id;
            double marks = random.nextDouble() * 10; // Random marks between 0 and 10
            studentQueue.enqueue(new Student(id, name, marks));
        }
        System.out.println(count + " random students generated.");
    }

    private void displayAllStudents() {
        if (studentQueue.isEmpty()) {
            System.out.println("No students to display!");
            return;
        }
        for (Student student : studentQueue.getAllStudents()) {
            System.out.println(student);
        }
    }

    private void addStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        System.out.print("Enter Student Name: ");
        scanner.nextLine(); // Clear buffer
        String name = scanner.nextLine();
        System.out.print("Enter Student Marks: ");
        double marks = scanner.nextDouble();
        studentQueue.enqueue(new Student(id, name, marks));
        System.out.println("Student added successfully.");
    }

    private void editStudent() {
        System.out.print("Enter Student ID to edit: ");
        int id = scanner.nextInt();
        Student[] students = studentQueue.getAllStudents();
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.print("Enter New Name: ");
                scanner.nextLine(); // Clear buffer
                student.setName(scanner.nextLine());
                System.out.print("Enter New Marks: ");
                student.setMarks(scanner.nextDouble());
                System.out.println("Student updated successfully.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        Student[] students = studentQueue.getAllStudents();
        studentQueue = new StudentQueue(100000); // Create a new queue
        boolean found = false;
        for (Student student : students) {
            if (student.getId() != id) {
                studentQueue.enqueue(student);
            } else {
                found = true;
            }
        }
        if (found) {
            System.out.println("Student deleted successfully.");
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private void sortStudents() {
        System.out.println("1. Bubble Sort");
        System.out.println("2. Quick Sort");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();
        Student[] students = studentQueue.getAllStudents();
        long startTime = System.nanoTime(); // Start time measurement

        if (choice == 1) {
            bubbleSort(students);
            System.out.println("Students sorted using Bubble Sort.");
        } else if (choice == 2) {
            quickSort(students, 0, students.length - 1);
            System.out.println("Students sorted using Quick Sort.");
        } else {
            System.out.println("Invalid choice.");
            return;
        }

        long endTime = System.nanoTime(); // End time measurement

        // Update queue with sorted students
        studentQueue = new StudentQueue(100000);
        for (Student student : students) {
            studentQueue.enqueue(student);
        }

        displayAllStudents();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds.");
    }

    private void quickSort(Student[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private int partition(Student[] arr, int low, int high) {
        Student pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].getMarks() <= pivot.getMarks()) {
                i++;
                Student temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Student temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    private void bubbleSort(Student[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (arr[j].getMarks() > arr[j + 1].getMarks()) {
                    Student temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    private void searchStudentById() {
        System.out.print("Enter Student ID to search: ");
        int id = scanner.nextInt();
        Student[] students = studentQueue.getAllStudents();
        for (Student student : students) {
            if (student.getId() == id) {
                System.out.println(student);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public static void main(String[] args) {
        new StudentManager().run();
    }
}
