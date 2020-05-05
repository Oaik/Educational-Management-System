import java.util.ArrayList;

public class Student extends User {
    Student() {}
    Student(String username, String password) {
        super(username, password);
    }
    Student(String username, String password, ArrayList<Course> courses) { super(username, password, courses); }
    public static Student currentStudent;
    public static ArrayList<Student> allStudent;
    public static void printAll() {
        for (int i = 0; i < allStudent.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ": ");
            User.print(allStudent.get(i));
            System.out.println();
        }
    }
    public static ArrayList<Student> initRandom(int num) {
        ArrayList<User> students = initRandom(num, "stu");
        ArrayList<Student> temp = new ArrayList<Student>();
        for(User tt: students)
            temp.add(new Student(tt.username, tt.password, tt.courses));
        allStudent = temp;
        return allStudent;
    }
    public static boolean validateUsername(String username) {
        ArrayList<User> temp = new ArrayList<User>();
        for(Student student: allStudent) temp.add(student);
        return User.validateUsername(username, temp);
    }
    public static Student validateStudent(String username, String password) {
        for(Student student: allStudent) {
            if(student.username.equals(username) && student.password.equals(password))
                return student;
        }
        return null;
    }
}
