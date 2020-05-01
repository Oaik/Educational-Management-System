import javax.print.Doc;
import java.util.ArrayList;

public class Doctor {
    String username, id, password;
    public static Doctor currentDoctor;
    public static ArrayList<Doctor> allDoctor;
    ArrayList<Course> courses = new ArrayList<Course>();
    Doctor() {}
    Doctor(String username, String passowrd) {
        this.username = username;
        this.password = passowrd;
    }
    public static ArrayList<Doctor> initRandom(int num) {
        ArrayList<Doctor> doctors = new ArrayList<Doctor>();
        for (int i = 1; i <= num; ++i) {
            String name = "Doc00" + Integer.toString(i);
            Doctor a = new Doctor(name, name);
            doctors.add(a);
        }
        allDoctor = doctors;
        return doctors;
    }
    public void viewCourses() {
        for (int i = 0; i < courses.size();++i) {
            System.out.println(courses.get(i).name);
        }
    }
    public static boolean validateUsername(String username) {
        for(Student student: Student.allStudent) {
            if(student.username == username)
                return false;
        }
        return true;
    }
    public boolean validate(String username, String password) {
        return this.username == username && this.password == password;
    }
}
