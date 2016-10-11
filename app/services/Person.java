package services;

import java.util.ArrayList;
import FrameworkAnotaciones.FixedWidthField;
/**
 * Created by Miguel on 10/8/16.
 */


public class Person {
    @FixedWidthField(width = 20) String firstName;
    @FixedWidthField(width = 20) String lastName;
    @FixedWidthField(width = 50) String email;
    @FixedWidthField(width = 16) String password;
    @FixedWidthField(width = 120) String totalPass;

    public Person(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        totalPass="";
    }

    public Person(){

    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTotalPass() {
        return totalPass;
    }

    public void setTotalPass(String totalPass) {
        this.totalPass = totalPass;
    }

    public void addPass(String a){
        this.totalPass=this.totalPass.trim()+" "+a;
    }

    @Override
    public String toString() {
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", totalPass=" + totalPass +
                '}';
    }
}
