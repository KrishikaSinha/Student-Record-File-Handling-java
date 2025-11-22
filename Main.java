import java.util.List;
import java.util.Scanner;

public class Main {
private static final String DATA_FILE = "students.txt";

public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    StudentManager manager = new StudentManager();

    // Load existing data
    manager.loadFromFile(DATA_FILE);

    int choice = 0;   // FIXED – initialized to prevent error

    do {
        System.out.println("\n===== Capstone Student Menu =====");
        System.out.println("1. Add Student");
        System.out.println("2. View All Students");
        System.out.println("3. Search by Name");
        System.out.println("4. Delete by Name");
        System.out.println("5. Sort by Marks (descending)");
        System.out.println("6. Sort by Name (ascending)");
        System.out.println("7. Show record at index (RandomAccessFile demo)");
        System.out.println("8. Save and Exit");
        System.out.print("Enter choice: ");

        if (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.nextLine();
            continue;
        }

        choice = sc.nextInt();
        sc.nextLine(); // consume newline

        switch (choice) {

            case 1:
                try {
                    System.out.print("Enter Roll No: ");
                    int roll = Integer.parseInt(sc.nextLine().trim());

                    System.out.print("Enter Name: ");
                    String name = sc.nextLine().trim();

                    System.out.print("Enter Email: ");
                    String email = sc.nextLine().trim();

                    System.out.print("Enter Course: ");
                    String course = sc.nextLine().trim();

                    System.out.print("Enter Marks: ");
                    double marks = Double.parseDouble(sc.nextLine().trim());

                    if (name.isEmpty() || email.isEmpty() || course.isEmpty()) {
                        System.out.println("Fields cannot be empty.");
                        break;
                    }
                    if (marks < 0 || marks > 100) {
                        System.out.println("Marks must be between 0 and 100.");
                        break;
                    }

                    Student s = new Student(roll, name, email, course, marks);
                    boolean added = manager.addStudent(s);
                    if (!added) {
                        System.out.println("Student with roll number " + roll + " already exists.");
                    } else {
                        FileUtil.appendStudent(DATA_FILE, s);
                        System.out.println("Student added and appended to file.");
                    }
                } catch (NumberFormatException ex) {
                    System.out.println("Invalid number format. Try again.");
                }
                break;

            case 2:
                manager.viewAll();
                break;

            case 3:
                System.out.print("Enter name to search: ");
                String nameQ = sc.nextLine().trim();
                List<Student> results = manager.searchByName(nameQ);
                if (results.isEmpty()) {
                    System.out.println("No students found with that name.");
                } else {
                    System.out.println("Found " + results.size() + " student(s):");
                    for (Student st : results) {
                        st.display();
                        System.out.println("-------------------");
                    }
                }
                break;

            case 4:
                System.out.print("Enter name to delete: ");
                String delName = sc.nextLine().trim();
                boolean removed = manager.deleteByName(delName);
                if (removed) {
                    manager.saveToFile(DATA_FILE);
                    System.out.println("Deleted and file updated.");
                } else {
                    System.out.println("No student found with that name.");
                }
                break;

            case 5:
                manager.sortByMarksDescending();
                System.out.println("Sorted by marks (descending). Use View All to see.");
                break;

            case 6:
                manager.sortByNameAscending();
                System.out.println("Sorted by name (A-Z). Use View All to see.");
                break;

            case 7:
                System.out.print("Enter index (0-based) to show record: ");
                if (!sc.hasNextInt()) {
                    System.out.println("Enter a valid integer index.");
                    sc.nextLine();
                    break;
                }
                int idx = sc.nextInt();
                sc.nextLine();
                manager.showRecordAt(DATA_FILE, idx);
                break;

            case 8:
                manager.saveToFile(DATA_FILE);
                System.out.println("Saved and exiting. Goodbye!");
                break;

            default:
                System.out.println("Invalid choice. Try again.");
        }

    } while (choice != 8);

    sc.close();
}

}
