import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Do you want the system to randomly generete (Courses-Students-Doctors) ?");
        System.out.println("[Yes] Enter 1");
        System.out.println("[No] Enter anything except 1");
        Scanner in = new Scanner(System.in);
        String choice = in.next();
        if(choice.equals("1")) {
            Course.initRandom(5, 5, 5);
            System.out.println("1-Students");
            Student.printAll();
            System.out.println("2-Doctors");
            Doctor.printAll();
            System.out.println("3-Courses");
            Course.printAll();
            System.out.println("Course, Doctors and Students are randomly generated :)");
        }
        Printing.mainMenu();
    }
}
