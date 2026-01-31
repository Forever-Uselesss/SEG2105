public class Student implements Comparable<Student> {
    private String studentId;
    private String firstName;
    private String lastName;
    private double averageGrade;

    public Student(String studentId, String firstName, String lastName, double averageGrade) {
        this.studentId = studentId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.averageGrade = averageGrade;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId='" + studentId + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", averageGrade=" + averageGrade +
                '}';
    }

    @Override
    public int compareTo(Student other) {
        return this.getStudentId().compareTo(other.getStudentId());
    }
}