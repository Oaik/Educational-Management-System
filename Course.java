import javax.print.Doc;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Course {
    String name, ID;
    Doctor doctor;
    ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Course> allCourses = new ArrayList<Course>();
    public static void viewCourses(ArrayList<Course> courses) {
        for (int i = 0; i < courses.size();++i)
            System.out.println(Integer.toString(i + 1) + '-' + courses.get(i).name);
    }
    public static ArrayList<Course> initRandom(int coursesNum, int studentNum, int doctorNum) {
        ArrayList<Course> courses = new ArrayList<Course>();
        for (int i = 1; i <= coursesNum; ++i) {
            Course a = new Course();
            a.name = "CS10" + Integer.toString(i);
            a.ID = "00" + Integer.toString(i);
            courses.add(a);
        }
        ArrayList<Student> students = Student.initRandom(studentNum);
        for (Student currentStudent: students) {
            int rand = (int)(Math.random() * coursesNum);
            courses.get(rand).students.add(currentStudent);
            currentStudent.courses.add(courses.get(rand));
        }
        ArrayList<Doctor> doctors = Doctor.initRandom(doctorNum);
        for (Doctor currentDoctor: doctors) {
            int rand = (int)(Math.random() * coursesNum);
            courses.get(rand).doctor = currentDoctor;
            currentDoctor.courses.add(courses.get(rand));
        }
        allCourses = courses;
        return courses;
    }
    public boolean cmp(Course temp) {
        return name.equals(temp.name);
    }
}
