import java.util.ArrayList;

public abstract class User {
    public String username, ID, password;
    ArrayList<Course> courses = new ArrayList<Course>();
    User() {}
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }
    User(String username, String password, ArrayList<Course> courses) {
        this(username, password);
        this.courses = courses;
    }
    public static void print(User current) {
        System.out.println("Username: " + current.username);
        System.out.println("Password: " + current.password);
//        System.out.println("ID: " + current.id);
        System.out.println("Courses: " );
        for (int i = 0; i < current.courses.size(); i++)
            System.out.println( Integer.toString(i + 1) + ": " + current.courses.get(i).name);
    }
    private String getUserName() {
        return this.username;
    }
    private String getPassword() {
        return this.password;
    }
    public void viewCourses() {
        for (int i = 0; i < courses.size();++i) {
            System.out.println( i + 1 + "-" + courses.get(i).name);
        }
    }
    public static boolean validateUsername(String username, ArrayList<User> allUsers) {
        for(User user: allUsers) {
            if(user.username.equals(username))
                return false;
        }
        return true;
    }
    public static boolean validateUser(String username, String password, boolean isStudent) {
        if(isStudent) {
            Student current = Student.validateStudent(username, password);
            if(current == null)
                return false;
//            Student.currentStudent = current; // login
            return true;
        } else {
            Doctor current = Doctor.validateDoctor(username, password);
            if(current == null)
                return false;
//            Doctor.currentDoctor = current; // login
            return true;
        }
    }
    public static User login(String username, String password, boolean isStudent) {
        if(isStudent) {
            Student current = Student.validateStudent(username, password);
            assert(current != null);
            return current;
        } else {
            Doctor current = Doctor.validateDoctor(username, password);
            assert(current != null);
            return current;
        }
    }
    public static void register(String username, String password, boolean isStudent) {
        if(isStudent) {
            Student.currentStudent = new Student(username, password); // login
            Student.allStudent.add(Student.currentStudent);
        } else {
            Doctor.currentDoctor = new Doctor(username, password); // login
            Doctor.allDoctor.add(Doctor.currentDoctor);
//            doctorFlow();
        }
    }
    public static boolean registerUserValidator(String username, int choice) {
        if(choice == 1) {
            if (!Student.validateUsername(username)) {
                System.out.println("Username Already registered");
                return false;
            }
            return true;
        } else {
            if(!Doctor.validateUsername(username)) {
                System.out.println("Username Already registered");
                return false;
            }
            return true;
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
}
