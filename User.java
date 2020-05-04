import java.util.ArrayList;

public class User {
    public String username, id, password;
    ArrayList<Course> courses = new ArrayList<Course>();
    User() {}
    User(String username, String passowrd) {
        this.username = username;
        this.password = passowrd;
    }
    User(String username, String passowrd, ArrayList<Course> courses) {
        this(username, passowrd);
        this.courses = courses;
    }
    private String getUserName() {
        return this.username;
    }
    private String getPassword() {
        return this.password;
    }
    public static ArrayList<User> initRandom(int num, String pre) {
        ArrayList<User> users = new ArrayList<User>();
        for (int i = 1; i <= num; ++i) {
            String name = pre + Integer.toString(i);
            User a = new User(name, name);
            users.add(a);
        }
        return users;
    }
    public void viewCourses() {
        for (int i = 0; i < courses.size();++i) {
            System.out.println(courses.get(i).name);
        }
    }
    public boolean validate(String username, String password) {
        return this.username.equals(username) && this.password.equals(password);
    }
    public static boolean validateUsername(String username, ArrayList<User> allUsers) {
        for(User user: allUsers) {
            if(user.username.equals(username))
                return false;
        }
        return true;
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
