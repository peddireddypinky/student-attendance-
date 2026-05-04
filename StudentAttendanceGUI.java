import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

class StudentGUI {
    String name;
    int rollNo;
    int present = 0;
    int total = 0;

    StudentGUI(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    void markPresent() {
        present++;
        total++;
    }

    double getPercentage() {
        if (total == 0) return 0;
        return (present * 100.0) / total;
    }

    public String toString() {
        return "Name: " + name +
                ", Roll: " + rollNo +
                ", Attendance: " + String.format("%.2f", getPercentage()) + "%";
    }
}

public class StudentAttendanceGUI extends JFrame {

    ArrayList<StudentGUI> students = new ArrayList<>();

    JTextField nameField, rollField;
    JTextArea outputArea;

    public StudentAttendanceGUI() {

        setTitle("Student Attendance System");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        add(new JLabel("Name:"));
        nameField = new JTextField(10);
        add(nameField);

        add(new JLabel("Roll No:"));
        rollField = new JTextField(10);
        add(rollField);

        JButton addBtn = new JButton("Add Student");
        JButton presentBtn = new JButton("Mark Present");
        JButton viewBtn = new JButton("View All");

        add(addBtn);
        add(presentBtn);
        add(viewBtn);

        outputArea = new JTextArea(15, 40);
        add(new JScrollPane(outputArea));

        // Add Student Action
        addBtn.addActionListener(e -> {
            String name = nameField.getText();
            int roll = Integer.parseInt(rollField.getText());
            students.add(new StudentGUI(name, roll));
            outputArea.setText("Student Added Successfully!\n");
        });

        // Mark Present Action
        presentBtn.addActionListener(e -> {
            int roll = Integer.parseInt(rollField.getText());
            for (StudentGUI s : students) {
                if (s.rollNo == roll) {
                    s.markPresent();
                    outputArea.setText("Attendance Marked!\n");
                }
            }
        });

        // View All Action
        viewBtn.addActionListener(e -> {
            outputArea.setText("");
            for (StudentGUI s : students) {
                outputArea.append(s.toString() + "\n");
            }
        });

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        add(footer);

        setVisible(true);
    }

    public static void main(String[] args) {
        new StudentAttendanceGUI();
    }
}