// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 8 Homework
// Final Project - Person

import java.io.Serializable;

public class Person implements Serializable, Comparable<Person> {
  private String firstName;
  private String lastName;
  private OCCCDate dob;

  // General constructor
  public Person (String firstName, String lastName) {
    this.firstName = firstName;
    this.lastName = lastName;
  }

  // Constructor with DOB attribute 
  public Person (String firstName, String lastName, OCCCDate dob) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.dob = dob;
  }

  // Copy constructor
  public Person (Person p) {
    firstName = p.firstName;
    lastName = p.lastName;
    this.dob = p.dob;
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

  public OCCCDate getDOB() {
    return dob;
  }

  @Override
  public String toString() { 
    return lastName + ", " + firstName + " (" + dob.toString() + ")";                           
  }                            

  public boolean equals(Person p) {
    return firstName.equalsIgnoreCase(p.firstName) && lastName.equalsIgnoreCase(p.lastName) && dob.equals(p.dob);
  }

  @Override
  public int compareTo(Person p) {
    return dob.compareTo(p.dob);
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