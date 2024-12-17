public class StudentQueue {
    private Student[] students;
    private int front, rear;
    private int size;

    public StudentQueue(int capacity) {
        students = new Student[capacity];
        front = rear = -1;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == students.length;
    }

    public void enqueue(Student student) {
        if (isFull()) {
            System.out.println("Queue is full, cannot add more students.");
            return;
        }
        if (front == -1) {
            front = 0;
        }
        rear = (rear + 1) % students.length;
        students[rear] = student;
        size++;
    }

    public Student dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return null;
        }
        Student student = students[front];
        front = (front + 1) % students.length;
        size--;
        return student;
    }

    public Student[] getAllStudents() {
        Student[] allStudents = new Student[size];
        int index = 0;
        for (int i = front; i != rear; i = (i + 1) % students.length) {
            allStudents[index++] = students[i];
        }
        allStudents[index] = students[rear];
        return allStudents;
    }

    public void clear() {
        front = rear = -1;
        size = 0;
    }
}
