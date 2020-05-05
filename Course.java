import org.jetbrains.annotations.NotNull;

import javax.print.Doc;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class Course {
    String name, ID;
    Doctor doctor;
    Course() {}
    Course(String courseName, Doctor doctor) {
        this.name = courseName;
        this.doctor = doctor;
    }
    ArrayList<Student> students = new ArrayList<Student>();
    public static ArrayList<Course> allCourses = new ArrayList<Course>();
    public static Course currentCourse;
    public static void print(Course course) {
        System.out.println("Course name: " + course.name);
//        System.out.println("ID: " + course.ID);
        System.out.println("Doctor name: " + course.doctor.username);
        System.out.println("Students: ");
        for (int i = 0; i < course.students.size(); i++)
            System.out.println(Integer.toString(i + 1) + ": " + course.students.get(i).username);
    }
    public static void printAll() {
        for (int i = 0; i < allCourses.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ": ");
            Course.print(allCourses.get(i));
            System.out.println();
        }
    }
    public static void viewCourses(ArrayList<Course> courses) {
        for (int i = 0; i < courses.size();++i)
            System.out.println(Integer.toString(i + 1) + '-' + courses.get(i).name);
    }

    public static ArrayList<Course> initRandom(int coursesNum, int studentNum, int doctorNum) {
        ArrayList<Course> courses = new ArrayList<Course>();
        ArrayList<Student> students = Student.initRandom(studentNum);
        ArrayList<Doctor> doctors = Doctor.initRandom(doctorNum);
        for (int i = 1; i <= coursesNum; ++i) {
            Course course = new Course();
            course.name = "CS10" + Integer.toString(i);
            course.ID = "00" + Integer.toString(i);

            int rand = (int)(Math.random() * studentNum);
            course.students.add(students.get(rand));
            students.get(rand).courses.add(course);

            rand = (int)(Math.random() * doctorNum);
            course.doctor = (doctors.get(rand));
            doctors.get(rand).courses.add(course);

            courses.add(course);
        }
        allCourses = courses;
        return courses;
    }
    public static boolean validateName(String courseName) {
        for (Course course: allCourses) {
            if(course.name.equals(courseName))
                return false;
        }
        return true;
    }
    public boolean cmp(Course temp) {
        return name.equals(temp.name);
    }
}
