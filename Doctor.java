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
    public static ArrayList<Doctor> allDoctor;
    public static void printAll() {
        for (int i = 0; i < allDoctor.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ": ");
            User.print(allDoctor.get(i));
            System.out.println();
        }
    }
    public static ArrayList<Doctor> initRandom(int num) {
        ArrayList<User> doctors = initRandom(num, "doc");
        ArrayList<Doctor> temp = new ArrayList<Doctor>();
        for(User tt: doctors)
            temp.add(new Doctor(tt.username, tt.password, tt.courses));
        allDoctor = temp;
        return allDoctor;
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
