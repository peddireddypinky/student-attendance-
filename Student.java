public class Student {

    private String name;
    private int rollNo;
    private int present;
    private int absent;

    public Student(String name, int rollNo, int present, int absent) {
        this.name = name;
        this.rollNo = rollNo;
        this.present = present;
        this.absent = absent;
    }

    public void markPresent() {
        present++;
    }

    public void markAbsent() {
        absent++;
    }

    public double getPercentage() {
        int total = present + absent;
        return total == 0 ? 0 : (present * 100.0 / total);
    }

    public String toString() {
        return "Name: " + name +
                ", Roll No: " + rollNo +
                ", Attendance: " + String.format("%.2f", getPercentage()) + "%";
    }

    public String toFileString() {
        return name + "," + rollNo + "," + present + "," + absent;
    }

    // Getters
    public String getName() { return name; }
    public int getRollNo() { return rollNo; }
}