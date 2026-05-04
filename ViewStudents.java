import java.sql.*;

public class ViewStudents {

    public static void main(String[] args) {

        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/attendance_db",
                    "root",
                    "Pinky@786");

            System.out.println("Connected Successfully!");

            String query = "SELECT * FROM students";
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("roll_number") + " | " +
                        rs.getDouble("attendance_percentage")
                );
            }

            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}