// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 4 Homework
// TestPerson Program (without OCCCDate)

import java.util.Scanner;

public class TestPerson {
  public static void main(String [] args) {
    String firstName = "", lastName = "", govID = "", studentID = "";
    Scanner s = new Scanner(System.in);
    
    System.out.println("Welcome to the TestPerson program!");
    System.out.println();

    // Prompt the user for the data for a Person (first and last name)
    System.out.print("Enter the first name of a Person: ");
    firstName = s.nextLine();
     
    System.out.print("Enter the last name of a Person: ");
    lastName = s.nextLine();
 
    // Create and display that person (using toString)
    Person p = new Person(firstName, lastName);
    System.out.println("Person created! Person info: " + p.toString());
    System.out.println();

    // Prompt the user for the data for a RegisteredPerson (first name, last name, and government ID)
    System.out.print("Enter the first name of a RegisteredPerson: ");
    firstName = s.nextLine();
     
    System.out.print("Enter the last name of a RegisteredPerson: ");
    lastName = s.nextLine();

    System.out.print("Enter the government ID of a RegisteredPerson: ");
    govID = s.nextLine();

    // Create and display that RegisteredPerson (using toString)
    RegisteredPerson rp = new RegisteredPerson(firstName, lastName, govID);
    System.out.println("RegisteredPerson created! RegisteredPerson info: " + rp.toString());
    System.out.println();

    // Prompt the user for the data for an OCCCPerson (first name, last name, government ID, and student ID)
    System.out.print("Enter the first name of an OCCCPerson: ");
    firstName = s.nextLine();
     
    System.out.print("Enter the last name of an OCCCPerson: ");
    lastName = s.nextLine();

    System.out.print("Enter the government ID of an OCCCPerson: ");
    govID = s.nextLine();

    System.out.print("Enter the student ID of an OCCCPerson: ");
    studentID = s.nextLine();

    // Create and display that OCCCPerson (using toString)
    OCCCPerson occcp = new OCCCPerson(firstName, lastName, govID, studentID);
    System.out.println("OCCCPerson created! OCCCPerson info: " + occcp.toString());
    System.out.println();

    // Prompt the user for a government ID, then create a new RegisteredPerson using that ID and your existing Person
    System.out.print("Enter a government ID: ");
    govID = s.nextLine();
    RegisteredPerson rp2 = new RegisteredPerson(p, govID);

    // Display that RegisteredPerson (using toString)
    System.out.println("RegisteredPerson created! RegisteredPerson info: " + rp2.toString());
    System.out.println();

    // Prompt the user for a student ID, then create a new OCCCPerson using that ID and the newly-created RegisteredPerson
    System.out.print("Enter a student ID: ");
    studentID = s.nextLine();

    OCCCPerson occcp2 = new OCCCPerson(rp2, studentID);

    // Display that OCCCPerson (using toString)
    System.out.println("OCCCPerson created! OCCCPerson info: " + occcp2.toString());

    // Exit program
    System.out.println();
    System.out.println("Goodbye!");
    System.exit(0);
  }
}