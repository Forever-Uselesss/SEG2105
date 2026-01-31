import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class StudentSortingDemo {
    public static void main(String[] args) {
        // Create a list of students
        List<Student> students = new ArrayList<>();
        students.add(new Student("S003", "Alice", "Johnson", 15.75));
        students.add(new Student("S001", "Bob", "Smith", 12.50));
        students.add(new Student("S004", "Charlie", "Brown", 18.25));
        students.add(new Student("S002", "Diana", "Miller", 16.00));
        // Print the original list
        System.out.println("Original list of students:");
        students.forEach(System.out::println);
        System.out.println();
        // Sort by natural order (student ID)
        Collections.sort(students);
        System.out.println("Students sorted by ID (natural order):");
        students.forEach(System.out::println);
        System.out.println();
        // Sort by last name
        Collections.sort(students, new SortStudentsByLastName());
        System.out.println("Students sorted by last name:");
        students.forEach(System.out::println);
        System.out.println();
        // Sort by average grade (descending)
        Collections.sort(students, new SortStudentsByAverageGrade());
        System.out.println("Students sorted by average grade (descending):");
        students.forEach(System.out::println);
    }
}