import java.util.ArrayList;

public class Student {
    String username, id, password;
    public static Student currentStudent;
    public static ArrayList<Student> allStudent;
    ArrayList<Course> courses = new ArrayList<Course>();
    Student() {}
    Student(String username, String passowrd) {
        this.username = username;
        this.password = passowrd;
    }
    public static ArrayList<Student> initRandom(int num) {
        ArrayList<Student> students = new ArrayList<Student>();
        for (int i = 1; i <= num; ++i) {
            String name = "Stu00" + Integer.toString(i);
            Student a = new Student(name, name);
            students.add(a);
        }
        allStudent = students;
        return students;
    }
    public void viewCourses() {
        for (int i = 0; i < courses.size();++i) {
            System.out.println(courses.get(i).name);
        }
    }
    public ArrayList<Course> viewUnregisteredCourses() {
        ArrayList<Course> unregisterdCourses = new ArrayList<Course>();
        int idx = 1;
        for (int i = 0; i < Course.allCourses.size(); ++i) {
            boolean can = true;
            for (int j = 0; j < courses.size(); ++j) {
                if(courses.get(j).cmp(Course.allCourses.get(i))) {
                    can = false;
                    break;
                }
            }
            if(can)
                unregisterdCourses.add(Course.allCourses.get(i));
        }
        return unregisterdCourses;
    }
    public static boolean validateUsername(String username) {
        for(Student student: Student.allStudent) {
            if(student.username.equals(username))
                return false;
        }
        return true;
    }
    public boolean validate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
}
