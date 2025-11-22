public class Student {
    private int rollNo;
    private String name;
    private String email;
    private String course;
    private double marks;
    private char grade;

    public Student(int rollNo, String name, String email, String course, double marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.email = email;
        this.course = course;
        this.marks = marks;
        calculateGrade();
    }

    public int getRollNo() { return rollNo; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getCourse() { return course; }
    public double getMarks() { return marks; }
    public char getGrade() { return grade; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setCourse(String course) { this.course = course; }
    public void setMarks(double marks) { this.marks = marks; calculateGrade(); }

    private void calculateGrade() {
        if (marks >= 85) grade = 'A';
        else if (marks >= 70) grade = 'B';
        else if (marks >= 50) grade = 'C';
        else grade = 'D';
    }

    // Convert to CSV line: rollNo|name|email|course|marks
    public String toCSV() {
        // Replace '|' in fields if any (simple sanitization)
        String safeName = name.replace("|", " ");
        String safeEmail = email.replace("|", " ");
        String safeCourse = course.replace("|", " ");
        return rollNo + "|" + safeName + "|" + safeEmail + "|" + safeCourse + "|" + marks;
    }

    public static Student fromCSV(String line) throws NumberFormatException {
        String[] parts = line.split("\\|");
        if (parts.length < 5) throw new NumberFormatException("Invalid record: " + line);
        int r = Integer.parseInt(parts[0].trim());
        String n = parts[1].trim();
        String e = parts[2].trim();
        String c = parts[3].trim();
        double m = Double.parseDouble(parts[4].trim());
        return new Student(r, n, e, c, m);
    }

    public void display() {
        System.out.println("Roll No: " + rollNo);
        System.out.println("Name   : " + name);
        System.out.println("Email  : " + email);
        System.out.println("Course : " + course);
        System.out.println("Marks  : " + marks);
        System.out.println("Grade  : " + grade);
    }
}
