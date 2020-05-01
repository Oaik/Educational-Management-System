import java.util.ArrayList;

public class User {
    String username, id, password;
    ArrayList<Course> courses = new ArrayList<Course>();
    public boolean validate(String username, String password) {
        return this.username == username && this.password == password;
    }
}
