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
            Printing.studentMenu();
            int choice = in.nextInt();
            while (!Printing.validate(1, 3, choice))
                choice = in.nextInt();
            if (choice == 3)
                return;
            if (choice == 1)
                reisterInUnregistredCourse();
            else
                Doctor.currentDoctor.viewCourses();
        }
    }
}
