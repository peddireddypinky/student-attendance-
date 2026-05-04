import java.io.FileWriter;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class StudentForm {

    public StudentForm() {

        JFrame frame = new JFrame("Student Attendance System");
        frame.setSize(700,600);
        frame.setLayout(null);

        // LABELS
        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(50,50,120,30);
        frame.add(nameLabel);

        JLabel rollLabel = new JLabel("Roll Number:");
        rollLabel.setBounds(50,100,120,30);
        frame.add(rollLabel);

        JLabel courseLabel = new JLabel("Course:");
        courseLabel.setBounds(50,150,120,30);
        frame.add(courseLabel);

        JLabel percentLabel = new JLabel("Attendance %:");
        percentLabel.setBounds(50,200,120,30);
        frame.add(percentLabel);

        // TEXTFIELDS
        JTextField nameField = new JTextField();
        nameField.setBounds(180,50,200,30);
        frame.add(nameField);

        JTextField rollField = new JTextField();
        rollField.setBounds(180,100,200,30);
        frame.add(rollField);

        JTextField courseField = new JTextField();
        courseField.setBounds(180,150,200,30);
        frame.add(courseField);

        JTextField percentField = new JTextField();
        percentField.setBounds(180,200,200,30);
        frame.add(percentField);

        // BUTTONS
        JButton addButton = new JButton("Add");
        addButton.setBounds(50,260,120,40);
        frame.add(addButton);

        JButton updateButton = new JButton("Update");
        updateButton.setBounds(190,260,120,40);
        frame.add(updateButton);

        JButton deleteButton = new JButton("Delete");
        deleteButton.setBounds(330,260,120,40);
        frame.add(deleteButton);

        JButton viewButton = new JButton("View All");
        viewButton.setBounds(470,260,120,40);
        frame.add(viewButton);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(400,100,120,30);
        frame.add(searchButton);

        JButton clearButton = new JButton("Clear");
        clearButton.setBounds(400,150,120,30);
        frame.add(clearButton);

        JButton logoutButton = new JButton("Logout");
        logoutButton.setBounds(400,50,120,30);
        frame.add(logoutButton);

        JButton lowAttendanceBtn = new JButton("Below 75%");
        lowAttendanceBtn.setBounds(50,310,150,30);
        frame.add(lowAttendanceBtn);

        JButton exportBtn = new JButton("Export Excel");
        exportBtn.setBounds(220,310,150,30);
        frame.add(exportBtn);

        JButton topBtn = new JButton("Top Attendance");
        topBtn.setBounds(400,310,150,30);
        frame.add(topBtn);

        // TABLE
        DefaultTableModel model = new DefaultTableModel(
                new String[]{"ID","Name","Roll","Course","Attendance %"},0);

        JTable table = new JTable(model);
        JScrollPane sp = new JScrollPane(table);
        sp.setBounds(30,350,620,180);
        frame.add(sp);

        // VIEW ALL
        viewButton.addActionListener(e -> DBHelper.loadTable(table,frame));

        // ADD
        addButton.addActionListener(e -> {
            try{

                double percent = Double.parseDouble(percentField.getText());

                Connection con = DBHelper.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "INSERT INTO students(name,roll_number,course,attendance_percentage) VALUES(?,?,?,?)");

                ps.setString(1,nameField.getText());
                ps.setString(2,rollField.getText());
                ps.setString(3,courseField.getText());
                ps.setDouble(4,percent);

                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame,"Student Added!");

                DBHelper.loadTable(table,frame);

                nameField.setText("");
                rollField.setText("");
                courseField.setText("");
                percentField.setText("");

                con.close();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });

        // UPDATE
        updateButton.addActionListener(e -> {
            try{

                Connection con = DBHelper.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "UPDATE students SET name=?,course=?,attendance_percentage=? WHERE roll_number=?");

                ps.setString(1,nameField.getText());
                ps.setString(2,courseField.getText());
                ps.setDouble(3,Double.parseDouble(percentField.getText()));
                ps.setString(4,rollField.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame,"Updated!");

                DBHelper.loadTable(table,frame);

                con.close();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });

        // DELETE
        deleteButton.addActionListener(e -> {
            try{

                Connection con = DBHelper.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "DELETE FROM students WHERE roll_number=?");

                ps.setString(1,rollField.getText());

                ps.executeUpdate();

                JOptionPane.showMessageDialog(frame,"Deleted!");

                DBHelper.loadTable(table,frame);

                con.close();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });

        // SEARCH
        searchButton.addActionListener(e -> {
            try{

                Connection con = DBHelper.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM students WHERE roll_number=?");

                ps.setString(1,rollField.getText());

                ResultSet rs = ps.executeQuery();

                model.setRowCount(0);

                while(rs.next()){
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("roll_number"),
                            rs.getString("course"),
                            rs.getDouble("attendance_percentage")
                    });
                }

                con.close();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });

        // BELOW 75%
        lowAttendanceBtn.addActionListener(e -> {
            try{

                Connection con = DBHelper.getConnection();

                PreparedStatement ps = con.prepareStatement(
                        "SELECT * FROM students WHERE attendance_percentage < 75");

                ResultSet rs = ps.executeQuery();

                model.setRowCount(0);

                while(rs.next()){
                    model.addRow(new Object[]{
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getString("roll_number"),
                            rs.getString("course"),
                            rs.getDouble("attendance_percentage")
                    });
                }

                con.close();

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });

        // EXPORT CSV
        exportBtn.addActionListener(e -> {
            try{

                FileWriter fw = new FileWriter("attendance_report.csv");

                for(int i=0;i<table.getRowCount();i++){
                    for(int j=0;j<table.getColumnCount();j++){
                        fw.write(table.getValueAt(i,j).toString()+",");
                    }
                    fw.write("\n");
                }

                fw.close();

                JOptionPane.showMessageDialog(frame,"Exported Successfully!");

            }catch(Exception ex){
                JOptionPane.showMessageDialog(frame,ex.getMessage());
            }
        });

        // CLEAR
        clearButton.addActionListener(e -> {
            nameField.setText("");
            rollField.setText("");
            courseField.setText("");
            percentField.setText("");
        });

        // LOGOUT
        logoutButton.addActionListener(e -> {
            frame.dispose();
            new LoginForm();
        });

        topBtn.addActionListener(e -> {
    try{

        Connection con = DBHelper.getConnection();

        PreparedStatement ps = con.prepareStatement(
        "SELECT * FROM students ORDER BY attendance_percentage DESC LIMIT 1");

        ResultSet rs = ps.executeQuery();

        if(rs.next()){

            String name = rs.getString("name");
            String roll = rs.getString("roll_number");
            String course = rs.getString("course");
            double attendance = rs.getDouble("attendance_percentage");

            JOptionPane.showMessageDialog(frame,
            "Top Student\n\nName: "+name+
            "\nRoll: "+roll+
            "\nCourse: "+course+
            "\nAttendance: "+attendance+"%");
        }

        con.close();

    }catch(Exception ex){
        JOptionPane.showMessageDialog(frame,ex.getMessage());
    }
});

        JLabel footer = new JLabel("Developed by Pinky", JLabel.CENTER);
        footer.setBounds(0,530,700,20);
        frame.add(footer);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}