import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {

    // Load all students from file
    public static List<Student> loadStudents(String filename) {
        List<Student> list = new ArrayList<>();
        File f = new File(filename);
        if (!f.exists()) return list; // empty list if no file

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;
                try {
                    Student s = Student.fromCSV(line);
                    list.add(s);
                } catch (NumberFormatException ex) {
                    System.out.println("Skipping invalid line in file: " + line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return list;
    }

    // Save all students to file (overwrites)
    public static void saveStudents(String filename, List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (Student s : students) {
                bw.write(s.toCSV());
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    // Demo: read nth record (0-based) using RandomAccessFile by scanning lines
    // (RandomAccessFile is used to show random access; this simple method seeks
    // by reading lines sequentially and returns the line if found.)
    public static String readRecordAt(String filename, int index) {
        File f = new File(filename);
        if (!f.exists()) return null;
        try (RandomAccessFile raf = new RandomAccessFile(f, "r")) {
            raf.seek(0);
            String line;
            int i = 0;
            while ((line = raf.readLine()) != null) {
                if (i == index) return line;
                i++;
            }
        } catch (IOException e) {
            System.out.println("RandomAccessFile error: " + e.getMessage());
        }
        return null;
    }

    // Append a single CSV line to file
    public static void appendStudent(String filename, Student s) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(s.toCSV());
            bw.newLine();
        } catch (IOException e) {
            System.out.println("Error appending to file: " + e.getMessage());
        }
    }
}
