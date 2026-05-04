import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class Dashboard {

    public Dashboard(String role) {

        JFrame frame = new JFrame(role + " Dashboard");
        frame.setSize(750,550);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        JLabel title = new JLabel(role + " PANEL", JLabel.CENTER);
        title.setBounds(200,10,300,30);
        title.setFont(new Font("Segoe UI",Font.BOLD,20));
        frame.add(title);

        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID","Name","Roll","Course","Attendance %"},0);

        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30,60,680,400);
        frame.add(sp);

        // Load data
        DBHelper.loadTable(table, frame);

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0, 480, 750, 20);
        frame.add(footer);

        frame.setVisible(true);
    }
}