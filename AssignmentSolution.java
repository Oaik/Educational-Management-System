public class AssignmentSolution {
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
    public void submit(String assignmentAnswer) {
        this.answer = assignmentAnswer;
    }
}
