import java.sql.*;

public class AddStudent {

    public static void main(String[] args) {

        try {

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/attendance_db",
                    "root",
                    "Pinky@786");

            String query = "INSERT INTO students (name, roll_number, course, attendance_percentage) VALUES (?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, "Rahul");
            ps.setString(2, "104");
            ps.setString(3, "CSE");
            ps.setDouble(4, 88.5);

            ps.executeUpdate();

            System.out.println("Student Added Successfully!");

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}