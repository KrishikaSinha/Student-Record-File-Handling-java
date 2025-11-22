import java.util.*;

public class StudentManager {
    private List<Student> students = new ArrayList<>();

    public StudentManager() {}

    public void loadFromFile(String filename) {
        students = FileUtil.loadStudents(filename);
        System.out.println("Loaded " + students.size() + " students from file.");
    }

    public void saveToFile(String filename) {
        FileUtil.saveStudents(filename, students);
        System.out.println("Saved " + students.size() + " students to file.");
    }

    public boolean addStudent(Student s) {
        // Prevent duplicate roll numbers
        for (Student st : students) {
            if (st.getRollNo() == s.getRollNo()) {
                return false;
            }
        }
        students.add(s);
        return true;
    }

    public void viewAll() {
        if (students.isEmpty()) {
            System.out.println("No records to display.");
            return;
        }
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            s.display();
            System.out.println("--------------------------");
        }
    }

    public List<Student> searchByName(String name) {
        List<Student> found = new ArrayList<>();
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name)) found.add(s);
        }
        return found;
    }

    public boolean deleteByName(String name) {
        boolean removed = false;
        Iterator<Student> it = students.iterator();
        while (it.hasNext()) {
            Student s = it.next();
            if (s.getName().equalsIgnoreCase(name)) {
                it.remove();
                removed = true;
            }
        }
        return removed;
    }

    public void sortByMarksDescending() {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
    }

    public void sortByNameAscending() {
        students.sort(Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public List<Student> getStudents() {
        return students;
    }

    // Simple helper to show RandomAccessFile demo for nth record
    public void showRecordAt(String filename, int index) {
        String line = FileUtil.readRecordAt(filename, index);
        if (line == null) {
            System.out.println("No record found at index " + index);
        } else {
            try {
                Student s = Student.fromCSV(line);
                System.out.println("Record at index " + index + ":");
                s.display();
            } catch (NumberFormatException ex) {
                System.out.println("Invalid record at index " + index + ": " + line);
            }
        }
    }
}
