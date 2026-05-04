import java.sql.*;
import javax.swing.*;

public class StudentViewForm {

    JFrame frame;
    JTextField rollField;
    JLabel resultLabel;

    public StudentViewForm(String rollNumber) {

        frame = new JFrame("Student Attendance View");
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setBounds(50, 50, 100, 30);
        frame.add(rollLabel);

        rollField = new JTextField();
        rollField.setBounds(160, 50, 150, 30);
        rollField.setText(rollNumber); // auto fill from login
        rollField.setEditable(false);  // student cannot change
        frame.add(rollField);

        JButton searchButton = new JButton("View Attendance");
        searchButton.setBounds(110, 100, 160, 30);
        frame.add(searchButton);

        resultLabel = new JLabel("");
        resultLabel.setBounds(100, 150, 250, 30);
        frame.add(resultLabel);

        // Button Action
        searchButton.addActionListener(e -> {

            try {
                Connection con = DBHelper.getConnection();

                String query = "SELECT attendance_percentage FROM students WHERE roll_number=?";
                PreparedStatement ps = con.prepareStatement(query);

                ps.setString(1, rollNumber);

                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    double attendance = rs.getDouble("attendance_percentage");
                    resultLabel.setText("Attendance: " + attendance + "%");
                } else {
                    resultLabel.setText("Student not found");
                }

                rs.close();
                ps.close();
                con.close();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, ex.getMessage());
            }
        });

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0,230,400,20);
        frame.add(footer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}