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
                return;
            if (choice == 1)
                reisterInUnregistredCourse();
            else
                Student.currentStudent.viewCourses();
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
                Doctor.currentDoctor.viewCourses();
                System.out.println(Doctor.currentDoctor.courses.size() + 1 + "-Back");
                int courseNum = in.nextInt();
                while (!Printing.validate(1, Doctor.currentDoctor.courses.size() + 1, choice))
                    courseNum = in.nextInt();
                if(courseNum == Doctor.currentDoctor.courses.size() + 1)
                    return;
                --courseNum;
                Printing.doctorAssignmentMenu();
                int x = in.nextInt();
                if(x == 3)
                    return;
                if(x == 1) {
                    Course nw = Doctor.currentDoctor.courses.get(courseNum);
                    Assignment oo = nw.assignment;
                    Doctor.currentDoctor.courses.get(courseNum).assignment.viewAssignment();
                }
                else {
                    String assignmentName = Printing.prompt("Enter Assignment name");
                    String assignmentContent = Printing.prompt("Enter Assignment content");
                    Assignment assignment = new Assignment(Doctor.currentDoctor, Doctor.currentDoctor.courses.get(courseNum), assignmentName, assignmentContent);
                    Doctor.currentDoctor.courses.get(courseNum).assignment = assignment;
                }
            }
        }
    }
}
