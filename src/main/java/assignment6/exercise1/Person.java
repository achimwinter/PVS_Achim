package assignment6.exercise1;

import java.util.Date;

public class Person {

    private final String lastName;
    private final String firstName;
    private final String birthdate;

    public Person(String lastName, String firstName, String birthdate) {

        this.lastName = lastName;
        this.firstName = firstName;
        this.birthdate = birthdate;
    }


    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getBirthdate() {
        return birthdate;
    }


    @Override
    public String toString() {
        return "Lastname: " + this.lastName + ", First Name: " + this.firstName + ", Birthday: " + this.birthdate + "\n";
    }
}
