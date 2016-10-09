package services;

/**
 * Created by Miguel on 10/8/16.
 */
public class Passwords {

    String id;
    String pass;

    public Passwords(String id, String pass) {
        this.id = id;
        this.pass = pass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @Override
    public String toString() {
        return "Passwords{" +
                "id='" + id + '\'' +
                ", pass='" + pass + '\'' +
                '}';
    }
}
