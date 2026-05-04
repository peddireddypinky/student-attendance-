import java.awt.*;
import javax.swing.*;

public class DashboardUI {

    JFrame frame;

    public DashboardUI() {

        frame = new JFrame("Student Attendance System");
        frame.setSize(1000,700);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Layout
        frame.setLayout(new BorderLayout());

        // Background color
        frame.getContentPane().setBackground(new Color(230,240,255));

        // ===== TOP HEADER =====
        JPanel header = new JPanel();
        header.setBackground(new Color(0,102,204));
        header.setPreferredSize(new Dimension(1000,70));

        JLabel title = new JLabel("Student Attendance Management System");
        title.setFont(new Font("Segoe UI",Font.BOLD,24));
        title.setForeground(Color.WHITE);

        header.add(title);

        frame.add(header,BorderLayout.NORTH);

        // ===== CENTER PANEL =====
        JPanel centerPanel = new JPanel();
        centerPanel.setBackground(new Color(230,240,255));
        centerPanel.setLayout(new BorderLayout());

        // Student Panel (main system UI)
        centerPanel.add(new StudentPanel(),BorderLayout.CENTER);

        frame.add(centerPanel,BorderLayout.CENTER);

        // ===== FOOTER =====
        JPanel footer = new JPanel();
        footer.setPreferredSize(new Dimension(1000,40));
        footer.setBackground(new Color(200,220,240));

        JLabel footText = new JLabel("Student Attendance System - Java + MySQL Project | Developed by Pinky");
        footText.setForeground(new Color(100, 100, 100));
        footer.add(footText);

        frame.add(footer,BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new DashboardUI();
    }
}