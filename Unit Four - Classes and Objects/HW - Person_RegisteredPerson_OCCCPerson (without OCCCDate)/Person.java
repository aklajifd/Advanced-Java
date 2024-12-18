// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 4 Homework
// Person Program (without OCCCDate)

public class Person {
  private String firstName;
  private String lastName;

  // General constructor
  public Person (String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // Copy constructor
  public Person (Person p) {
    firstName = p.firstName;
    lastName = p.lastName;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public String toString() { 
    return lastName + ", " + firstName;                           
  }                            

  public boolean equals(Person p) {
    return firstName.equalsIgnoreCase(p.firstName) && lastName.equalsIgnoreCase(p.lastName);
  }
  
  public void eat() {
    System.out.println( getClass().getName() + " " + toString() + " is eating!" );
  } 

  public void sleep() {
    System.out.println( getClass().getName() + " " + toString() + " is sleeping!" );
  }

  public void play() {
    System.out.println( getClass().getName() + " " + toString() + " is playing!" );
  }
 
  public void run() {
    System.out.println( getClass().getName() + " " + toString() + " is running!" );
  }
}