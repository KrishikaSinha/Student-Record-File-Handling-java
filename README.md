# Student Management System – Java (File Handling & Collections)

## 📌 Overview

This project is a **Java-based Student Management System** that stores, updates, and manages student data using:

* File Handling (Text File Storage)
* Collections Framework (`ArrayList`)
* Sorting operations
* Searching and Deletion
* RandomAccessFile demo for record access
* Menu-based console UI

All data is stored in `students.txt`, and new student entries are appended immediately for persistence.

---

## 🧠 Key Concepts Demonstrated

### ✔ File Handling

* Read & write student data using text files
* Append new records while retaining old data
* Use of `BufferedReader`, `BufferedWriter`, and `FileWriter`

### ✔ Collections (ArrayList)

Stores all student objects dynamically and efficiently.

### ✔ Sorting

* Sort by Marks (Descending)
* Sort by Name (Ascending)

### ✔ Searching & Deletion

* Search students by name
* Delete all students matching a given name

### ✔ RandomAccessFile (Demo)

Read a specific record by index (line number).

---

## 📂 Project Structure

All logic is implemented in **one single file** for easy execution:

```
Main.java
students.txt (created automatically after adding data)
```

---

## ▶️ How to Run

### 1️⃣ Save the code as:

```
Main.java
```

### 2️⃣ Compile:

```
javac Main.java
```

### 3️⃣ Run:

```
java Main
```

A `students.txt` file will be created automatically after adding the first student.

---

## 🖥️ Menu Options

```
===== Student Menu =====
1. Add Student
2. View All Students
3. Search by Name
4. Delete by Name
5. Sort by Marks (descending)
6. Sort by Name (ascending)
7. Show Record by Index
8. Save and Exit
```

---

## 📸 Sample Input & Output

### Input:

```
Enter Roll No: 101
Enter Name: Karan
Enter Email: karan@mail.com
Enter Course: BCA
Enter Marks: 77.5
```

### Output:

```
Student added!
```

### After Viewing:

```
Roll No: 101
Name   : Karan
Email  : karan@mail.com
Course : BCA
Marks  : 77.5
Grade  : B
```

---

## 🎯 Learning Outcomes

After completing this project, you will:

* Perform file I/O in Java
* Implement dynamic data structures using ArrayList
* Apply sorting and searching on custom objects
* Understand real-world record processing
* Build robust menu-based applications


This project is free for academic and personal use.
