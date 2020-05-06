import java.util.ArrayList;

public class Assignment {
    private String name, assignmentContent, assignmentAnswer;
    Course course;
    Doctor doctor;
    ArrayList<AssignmentSolution> solutions;
    Assignment() {}
    Assignment(Doctor doctor, Course course, String name, String assignmentContent) {
        this.doctor = doctor;
        this.course = course;
        this.name = name;
        this.assignmentContent = assignmentContent;
    }
    public void viewAssignment() {
        if(name == null) {
            System.out.println("There is no Assignment created yet :(");
            return;
        }
        System.out.println("Assignment Name: " + name);
        System.out.println("Assignment Doctor name: " + doctor.username);
        System.out.println("Assignment Content: " + assignmentContent);
        if(solutions == null) {
            System.out.println("There is no answers submitted yet :(");
            return;
        }
        System.out.println("Submitted answers");
        for(AssignmentSolution solution: solutions) {
            System.out.println("Student name " + solution.student.username);
            System.out.println("Student answer " + solution.answer);
        }
    }
    public boolean isStudentSubmited(Student student) {
        for(AssignmentSolution solution: solutions) {
            if(solution.student.username.equals(student.username))
                return true;
        }
        return false;
    }
}
