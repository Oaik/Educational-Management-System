import javax.print.Doc;
import java.util.ArrayList;
import java.util.Scanner;
/*
TODO: Upcasting student to user
will make (register, validateUser) Easier 
 */
public class Printing {
    public static Scanner in = new Scanner(System.in);
    public static boolean validate(int l, int r, int val) {
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
    public static void menuStudentOrDoctor() {
        System.out.println("1-Student");
        System.out.println("2-Doctor");
        System.out.println("3-back");
    }
    public static void studentMenu() {
        System.out.println("1-Register in course");
        System.out.println("2-View all registered courses");
        System.out.println("3-Logout");
    }
    public static String prompt(String name) {
        System.out.println("Enter Your " + name);
        return in.next();
    }
    public static void mainMenu() {
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
                Flow.login(choice);
            else
                Flow.register(choice);
        }
    }
}
