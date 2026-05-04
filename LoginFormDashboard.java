import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class LoginFormDashboard {

    public LoginFormDashboard(String role) {

        JFrame frame = new JFrame(role + " Dashboard");
        frame.setSize(700,500);
        frame.setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID","Name","Roll","Course","Attendance %"},0);

        JTable table = new JTable(model);
        frame.add(new JScrollPane(table));

        DBHelper.loadTable(table, frame);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        frame.add(footer, java.awt.BorderLayout.SOUTH);

        frame.setVisible(true);
    }
}