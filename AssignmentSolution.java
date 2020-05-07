public class AssignmentSolution extends Assignment{
    Student student;
    String answer;
//    int grade;
    // set grade
    AssignmentSolution(Student student) {
        this.student = student;
    }
    AssignmentSolution(Student student, String answer) {
        this.student = student;
        this.answer = answer;
    }
    AssignmentSolution(Student student, String answer, Assignment assignment) {
        super(assignment.doctor, assignment.course, assignment.name, assignment.assignmentContent);
        this.student = student;
        this.answer = answer;
    }

    public void printAsiggnmentSolutionDetials() {
        System.out.println("Student name " + student.username);
        System.out.println("Student answer " + answer);
    }
    public void printAsiggnmentSolution() {
        System.out.println("Assignment Name: " + name);
        System.out.println("Assignment Content: " + assignmentContent);
        printAsiggnmentSolutionDetials();
    }
}
