import java.util.ArrayList;

public class Assignment {
    public String name, assignmentContent;
    Course course;
    Doctor doctor;
    ArrayList<AssignmentSolution> solutions = new ArrayList<>();
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
    }
    public void viewAssignmentWithSolution() {
        viewAssignment();
        viewSolution();
    }
    public void viewSolution() {
        if(solutions == null) {
            System.out.println("There is no answers submitted yet :(");
            return;
        }
        System.out.println("Submitted answers");
        for(AssignmentSolution solution: solutions)
            solution.printAsiggnmentSolutionDetials();
    }
    public boolean isStudentSubmited(Student student) {
        for(AssignmentSolution solution: solutions) {
            if(solution.student.username.equals(student.username))
                return true;
        }
        return false;
    }
}
