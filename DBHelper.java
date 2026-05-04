import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class DBHelper {

    // ===== CONNECTION =====
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            String url = "jdbc:mysql://localhost:3306/attendance_db";
            String user = "root";
            String pass = "Pinky@786";

            return DriverManager.getConnection(url, user, pass);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "DB Connection Error: " + e.getMessage());
            return null;
        }
    }

    // ===== LOAD TABLE =====
    public static void loadTable(JTable table, JFrame frame) {
        try {
            Connection con = getConnection();
            if (con == null) return;

            PreparedStatement ps = con.prepareStatement("SELECT * FROM students");
            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID","Name","Roll","Course","Attendance %"},0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("course"),
                        rs.getDouble("attendance_percentage")
                });
            }

            table.setModel(model);

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, e.getMessage());
        }
    }

    // ===== INSERT =====
    public static void addStudent(String name, String roll, String course, double percent) {
        try {
            Connection con = getConnection();
            if (con == null) return;

            PreparedStatement ps = con.prepareStatement(
                    "INSERT INTO students(name, roll_number, course, attendance_percentage) VALUES(?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, roll);
            ps.setString(3, course);
            ps.setDouble(4, percent);

            ps.executeUpdate();

            JOptionPane.showMessageDialog(null, "Student Added!");

            ps.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // ===== UPDATE =====
    public static int updateStudent(String name, String course, double percent, String roll) {
        try {
            Connection con = getConnection();
            if (con == null) return 0;

            PreparedStatement ps = con.prepareStatement(
                    "UPDATE students SET name=?, course=?, attendance_percentage=? WHERE roll_number=?");

            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, percent);
            ps.setString(4, roll);

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            return rows;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }

    // ===== DELETE =====
    public static int deleteStudent(String roll) {
        try {
            Connection con = getConnection();
            if (con == null) return 0;

            PreparedStatement ps = con.prepareStatement(
                    "DELETE FROM students WHERE roll_number=?");

            ps.setString(1, roll);

            int rows = ps.executeUpdate();

            ps.close();
            con.close();

            return rows;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return 0;
        }
    }

    // ===== SEARCH =====
    public static void searchStudent(JTable table, String roll) {
        try {
            Connection con = getConnection();
            if (con == null) return;

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM students WHERE roll_number=?");

            ps.setString(1, roll);

            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = (DefaultTableModel) table.getModel();
            model.setRowCount(0);

            while (rs.next()) {
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("course"),
                        rs.getDouble("attendance_percentage")
                });
            }

            rs.close();
            ps.close();
            con.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    // ===== STUDENT VIEW BY ROLL =====
    public static boolean loadStudentByRoll(JTable table, String roll) {
        try {
            Connection con = getConnection();
            if (con == null) return false;

            PreparedStatement ps = con.prepareStatement(
                    "SELECT * FROM students WHERE roll_number=?");

            ps.setString(1, roll);

            ResultSet rs = ps.executeQuery();

            DefaultTableModel model = new DefaultTableModel(
                    new String[]{"ID","Name","Roll","Course","Attendance %"},0);

            boolean found = false;

            while (rs.next()) {
                found = true;
                model.addRow(new Object[]{
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("roll_number"),
                        rs.getString("course"),
                        rs.getDouble("attendance_percentage")
                });
            }

            table.setModel(model);

            rs.close();
            ps.close();
            con.close();

            return found;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
            return false;
        }
    }
}