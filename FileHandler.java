import java.io.*;
import java.util.*;

public class FileHandler {

    static final String FILE_NAME = "students.txt";

    public static void saveToFile(ArrayList<Student> students) {

        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {

            for (Student s : students) {
                pw.println(s.toFileString());
            }

        } catch (IOException e) {
            System.out.println("Error saving file.");
        }
    }

    public static ArrayList<Student> loadFromFile() {

        ArrayList<Student> students = new ArrayList<>();

        try (Scanner fileScanner = new Scanner(new File(FILE_NAME))) {

            while (fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String[] data = line.split(",");

                students.add(new Student(
                        data[0],
                        Integer.parseInt(data[1]),
                        Integer.parseInt(data[2]),
                        Integer.parseInt(data[3])
                ));
            }

        } catch (Exception e) {
            System.out.println("No previous data found.");
        }

        return students;
    }
}