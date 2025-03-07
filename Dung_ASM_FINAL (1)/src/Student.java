public class Student {
    private int id;
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = roundToOneDecimal(marks);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMarks() {
        return marks;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMarks(double marks) {
        this.marks = roundToOneDecimal(marks);
    }

    private double roundToOneDecimal(double value) {
        return Math.round(value * 10) / 10.0;
    }

    public String getRank() {
        if (marks >= 0 && marks <= 5.0) {
            return "Fail";
        } else if (marks > 5.0 && marks <= 6.5) {
            return "Medium";
        } else if (marks > 6.5 && marks <= 7.5) {
            return "Good";
        } else if (marks > 7.5 && marks <= 9.0) {
            return "Very Good";
        } else if (marks > 9.0 && marks <= 10.0) {
            return "Excellent";
        } else {
            return "Invalid Marks";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks + ", Rank: " + getRank();
    }
}
