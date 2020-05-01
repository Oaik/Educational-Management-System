import java.util.ArrayList;
import java.util.Scanner;

public class Printing {

    Scanner in = new Scanner(System.in);
    boolean validate(int l, int r, int val) {
        if(val >= l && val <= r)
            return true;
        System.out.println("Please Enter valid Integer between " + l + ' ' + r);
        return false;
    }
    public static void mainMenuPrompt() {
        System.out.println("1-Login");
        System.out.println("2-Register");
        System.out.println("3-exit");
    }
    void menuStudentOrDoctor() {
        System.out.println("1-Student");
        System.out.println("2-Doctor");
        System.out.println("3-back");
    }
    void studentMenu() {
        System.out.println("1-Register in course");
        System.out.println("2-View all registered courses");
        System.out.println("3-Logout");
    }
    void prompt(String name) {
        System.out.println("Enter Your " + name);
    }
    String enterUsername() {
        prompt("Username");
        return in.next();
    }
    String enterPassword() {
        prompt("Password");
        return in.next();
    }
    public void mainMenu() {
        while(true) {
            mainMenuPrompt();
            int x = in.nextInt();
            while (!validate(1, 3, x)) {
                mainMenuPrompt();
                x = in.nextInt();
            }
            if(x == 3) return;
            menuStudentOrDoctor();
            int choice = in.nextInt();
            while (!validate(1, 3, choice)) {
                menuStudentOrDoctor();
                choice = in.nextInt();
            }
            if(choice == 3) continue;
            if (x == 1)
                login(choice);
            else
                register(choice);
        }
    }
    boolean validateUser(String username, String password, boolean isStudent) {
        if(isStudent) {
            for(Student student: Student.allStudent) {
                if(student.validate(username, password)){
                    Student.currentStudent = student;
                    return true;
                }
            }
            return false;
        } else {
            for(Doctor doctor : Doctor.allDoctor) {
                if(doctor.validate(username, password)){
                    Doctor.currentDoctor = doctor;
                    return true;
                }
            }
            return false;
        }
    }
    public void login(int choice) {
        String username = enterUsername();
        String password = enterPassword();
        while(!validateUser(username, password, choice == 1)) {
            System.out.println("Please Enter Correct Username and Password");
            username = enterUsername();
            password = enterPassword();
        }
        if(choice == 1)
            studentFlow();
        else
            doctorFlow();
    }
    public void register(int choice) {
        String username = enterUsername();
        if(choice == 1) {
            while(!Student.validateUsername(username)) {
                System.out.println("Username Already registered");
                username = enterUsername();
            }

        } else {
            while(!Doctor.validateUsername(username)) {
                System.out.println("Username Already registered");
                username = enterUsername();
            }
        }
        String password = enterPassword();
        if(choice == 1) {
            Student.currentStudent = new Student(username, password);
            Student.allStudent.add(Student.currentStudent);
            studentFlow();
        } else {
            Doctor.currentDoctor = new Doctor();
            Doctor.allDoctor.add(Doctor.currentDoctor);
            doctorFlow();
        }
    }
    public void viewCourses() {
        Student.currentStudent.viewCourses();
    }
    public void doctorFlow() {

    }
    public void studentFlow() {
        while(true) {
            studentMenu();
            int choice = in.nextInt();
            while (!validate(1, 3, choice))
                choice = in.nextInt();
            if (choice == 3)
                return;
            if (choice == 1) {
                ArrayList<Course> unregisterCourses = Student.currentStudent.viewUnregisteredCourses();
                Course.viewCourses(unregisterCourses);
                int x = in.nextInt();
                while(!validate(1, unregisterCourses.size() + 1, x)) {
                    x = in.nextInt();
                }
                if(x == unregisterCourses.size() + 1)
                    continue;
                --x;
                Student.currentStudent.courses.add(unregisterCourses.get(x));
                System.out.println("Course Registred Successfuly :)");
            } else
                viewCourses();
        }
    }
}
