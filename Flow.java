import javax.print.Doc;
import java.util.ArrayList;
import java.util.Scanner;

public class Flow {
    public static Scanner in = new Scanner(System.in);
    public static void login(String username, String password, boolean isStudent) {
        if(isStudent)
            Student.currentStudent = (Student)User.login(username, password, isStudent); // login
        else
            Doctor.currentDoctor = (Doctor)User.login(username, password, isStudent); // login
    }
    public static void login(int choice) {
        String username = Printing.prompt("Username");
        String password = Printing.prompt("Password");
        while(!User.validateUser(username, password, choice == 1)) {
            System.out.println("Please Enter Correct Username and Password");
            username = Printing.prompt("Username");
            password = Printing.prompt("Password");
        }
        Flow.login(username, password, choice == 1);
        if(choice == 1)
            Flow.studentFlow();
        else
            Flow.doctorFlow();
    }
    public static void register(int choice) {
        String username = Printing.prompt("Username");
        while(!User.registerUserValidator(username, choice))
            username = Printing.prompt("Username");
        String password = Printing.prompt("Password");
        User.register(username, password, choice == 1);
        if(choice == 1)
            Flow.studentFlow();
        else
            Flow.doctorFlow();
    }
    public static void reisterInUnregistredCourse() {
        ArrayList<Course> unregisterCourses = Student.currentStudent.viewUnregisteredCourses();
        Course.viewCourses(unregisterCourses);
        int x = in.nextInt();
        while(!Printing.validate(1, unregisterCourses.size() + 1, x))
            x = in.nextInt();
        if(x == unregisterCourses.size() + 1)
            return;
        --x;
        Student.currentStudent.courses.add(unregisterCourses.get(x));
        unregisterCourses.get(x).students.add(Student.currentStudent);
        System.out.println("Course Registred Successfuly :)");
    }
    public static void createCourse() {
        String courseName = Printing.prompt("Course Name");
        while(!Course.validateName(courseName)) {
            System.out.println("This course is already created, please enter another name");
            courseName = Printing.prompt("Course Name");
        }
        Course currentCourse = new Course(courseName, Doctor.currentDoctor);
        Course.allCourses.add(currentCourse);
        Doctor.currentDoctor.courses.add(currentCourse);
    }
    public static void studentFlow() {
        while(true) {
            Printing.studentMenu();
            int choice = in.nextInt();
            while (!Printing.validate(1, 3, choice))
                choice = in.nextInt();
            if (choice == 3)
                break;
            if (choice == 1)
                reisterInUnregistredCourse();
            else {
                int courseNum = Printing.viewAndSelectCourse(Student.currentStudent.courses);
                if(courseNum == -1)
                    continue;
                Printing.studentAssignmentMenu();
                int x = in.nextInt();
                while(!Printing.validate(1, 4, x)) {
                    Printing.studentAssignmentMenu();
                    x = in.nextInt();
                }
                if(x == 4)
                    continue;
                if(x == 1) {
                    Student.currentStudent.printUnsubmittedAssignmentsInCourse(Student.currentStudent.courses.get(courseNum));
                } else if(x == 2) {
                    ArrayList<Assignment> assignments = Student.currentStudent.printUnsubmittedAssignmentsInCourse(Student.currentStudent.courses.get(courseNum));
                    if(assignments.isEmpty())
                        continue;
                    System.out.println(assignments.size() + 1 + "-back");
                    int assignmentNum = in.nextInt();
                    while(!Printing.validate(1, assignments.size() + 1, assignmentNum)) {
                        Printing.studentAssignmentMenu();
                        assignmentNum = in.nextInt();
                    }
                    if(assignmentNum == assignments.size() + 1)
                        continue;
                    --assignmentNum;
                    String answer = Printing.prompt("Enter Your Answer");
                    AssignmentSolution assignmentSolution = new AssignmentSolution(Student.currentStudent, answer, assignments.get(assignmentNum));
                    assignments.get(assignmentNum).solutions.add(assignmentSolution);
                    Student.currentStudent.assignmentsSolutions.add(assignmentSolution);
                } else {
                    Student.currentStudent.printAssignmentsInCourse(Student.currentStudent.courses.get(courseNum));
                }
            }
        }
    }
    public static void doctorFlow() {
        while(true) {
            Printing.doctorMenu();
            int choice = in.nextInt();
            while (!Printing.validate(1, 3, choice))
                choice = in.nextInt();
            if (choice == 3)
                return;
            if (choice == 1) {
                Flow.createCourse();
            } else {
                int courseNum = Printing.viewAndSelectCourse(Doctor.currentDoctor.courses);
                if(courseNum == -1)
                    continue;
                Printing.doctorAssignmentMenu();
                int x = in.nextInt();
                while(!Printing.validate(1, 3, x)) {
                    Printing.doctorAssignmentMenu();
                    x = in.nextInt();
                }
                if(x == 3)
                    continue;
                if(x == 1) {
                    Doctor.currentDoctor.courses.get(courseNum).viewAllAssignment();
                } else {
                    String assignmentName = Printing.prompt("Enter Assignment name");
                    String assignmentContent = Printing.prompt("Enter Assignment content");
                    Assignment assignment = new Assignment(Doctor.currentDoctor, Doctor.currentDoctor.courses.get(courseNum), assignmentName, assignmentContent);
                    Doctor.currentDoctor.courses.get(courseNum).assignments.add(assignment);
                }
            }
        }
    }
}
