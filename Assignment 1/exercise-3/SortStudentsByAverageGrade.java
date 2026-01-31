public class SortStudentsByAverageGrade implements java.util.Comparator<Student> {
    @Override
    public int compare(Student s1, Student s2) {
        return Double.compare(s2.getAverageGrade(), s1.getAverageGrade());
    }

}
