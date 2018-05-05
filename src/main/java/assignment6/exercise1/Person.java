package assignment6.exercise1;

import java.util.Date;

public class Person {

  private final String lastName;
  private final String firstName;
  private final Date birthdate;

  public Person(String lastName, String firstName, Date birthdate) {

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

  public Date getBirthdate() {
    return birthdate;
  }

}
