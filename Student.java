import java.lang.reflect.Array;
import java.util.ArrayList;

public class Student extends User {
    Student() {}
    Student(String username, String password) {
        super(username, password);
    }
    Student(String username, String password, ArrayList<Course> courses) { super(username, password, courses); }
    public static Student currentStudent;
    public static ArrayList<Student> allStudent = new ArrayList<>();;
    public ArrayList<AssignmentSolution> assignmentsSolutions = new ArrayList<>();
    public static void printAll() {
        for (int i = 0; i < allStudent.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ": ");
            User.print(allStudent.get(i));
            System.out.println();
        }
    }
    public static void initRandom(int num) {
        for (int i = 1; i <= num; ++i) {
            String name = "stu" + Integer.toString(i);
            Student current = new Student(name, name);
            Student.allStudent.add(current);
        }
    }
    public void printAssignmentsInCourse(Course course) {
        boolean found = false;
        for(int i = 0; i < assignmentsSolutions.size();++i) {
            System.out.println((i + 1) + "-");
            AssignmentSolution assignmentSolution = assignmentsSolutions.get(i);
            if(assignmentSolution.course.name.equals(course.name)) {
                assignmentSolution.printAsiggnmentSolution();
                found = true;
            }
        }
        if(!found) System.out.println("No Assignments found");
    }
    public ArrayList<Assignment> printUnsubmittedAssignmentsInCourse(Course course) {
        ArrayList<Assignment> assignments = new ArrayList<>();
        boolean found = false;
        for(int i = 0; i < course.assignments.size();++i) {
            System.out.println( i + 1 + "-");
            Assignment assignment = course.assignments.get(i);
            if(assignment.isStudentSubmited(Student.currentStudent) == false) {
                assignment.viewAssignment();
                assignments.add(assignment);
                found = true;
            }
        }
        if(!found) System.out.println("No Assignment Found");
        return assignments;
    }
    public static boolean validateUsername(String username) {
        ArrayList<User> temp = new ArrayList<User>();
        for(Student student: allStudent) temp.add(student);
        return User.validateUsername(username, temp);
    }
    public static Student validateStudent(String username, String password) {
        for(Student student: allStudent) {
            if(student.username.equals(username) && student.password.equals(password))
                return student;
        }
        return null;
    }
}
