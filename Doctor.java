import javax.print.Doc;
import java.util.ArrayList;

public class Doctor extends User {
    Doctor() {}
    Doctor(String username, String password) {
        super(username, password);
    }
    Doctor(String username, String password, ArrayList<Course> courses) {
        super(username, password, courses);
    }
    public static Doctor currentDoctor;
    public static ArrayList<Doctor> allDoctor = new ArrayList<>();
    public static void printAll() {
        for (int i = 0; i < allDoctor.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ": ");
            User.print(allDoctor.get(i));
            System.out.println();
        }
    }
    public static void initRandom(int num) {
        for (int i = 1; i <= num; ++i) {
            String name = "doc" + Integer.toString(i);
            Doctor current = new Doctor(name, name);
            Doctor.allDoctor.add(current);
        }
    }
    public static boolean validateUsername(String username) {
        ArrayList<User> temp = new ArrayList<User>();
        for(Doctor doctor: allDoctor) temp.add(doctor);
        return User.validateUsername(username, temp);
    }
    public static Doctor validateDoctor(String username, String password) {
        for(Doctor doctor: allDoctor) {
            if(doctor.username.equals(username) && doctor.password.equals(password))
                return doctor;
        }
        return null;
    }
}
