import java.awt.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentPanel extends JPanel {

    JTable table;
    JTextField searchField;

    public StudentPanel() {

        setLayout(new BorderLayout());
        setBackground(Color.WHITE);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);

        searchField = new JTextField(15);
        JButton searchBtn = new JButton("Search");

        searchBtn.setBackground(new Color(0,153,76));
        searchBtn.setForeground(Color.WHITE);

        topPanel.add(new JLabel("Search Roll No: "));
        topPanel.add(searchField);
        topPanel.add(searchBtn);

        add(topPanel,BorderLayout.NORTH);

        table = new JTable();
        JScrollPane sp = new JScrollPane(table);

        add(sp,BorderLayout.CENTER);

        loadTable();

        searchBtn.addActionListener(e -> searchStudent());
    }

    void loadTable() {
        try {
            Connection con = DBHelper.getConnection();
            PreparedStatement ps = con.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID","Name","Roll","Course","Attendance %"},0);

            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("course"),
                        rs.getDouble("attendance_percentage")
                });
            }

            table.setModel(model);
            con.close();

        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }

    void searchStudent(){
        try{
            Connection con = DBHelper.getConnection();
            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM students WHERE roll_number=?");

            ps.setString(1,searchField.getText());
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID","Name","Roll","Course","Attendance %"},0);

            while(rs.next()){
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("course"),
                        rs.getDouble("attendance_percentage")
                });
            }

            table.setModel(model);
            con.close();

        } catch(Exception e){
            JOptionPane.showMessageDialog(this,e.getMessage());
        }
    }
}