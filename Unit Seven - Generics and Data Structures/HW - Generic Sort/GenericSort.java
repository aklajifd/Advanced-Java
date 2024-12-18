// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 7 Homework
// GenericSort Class

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GenericSort {
  public static void main(String [] args) {
    String fileName;
    Integer[] intArr = new Integer[20];
    Double[] doubleArr = new Double[20];
    String[] stringArr = new String[20];
    Person[] personArr = new Person[20];
    
    // Scanner object
    Scanner userInput = new Scanner(System.in);

    // Display a friendly greeting to the user
    System.out.println("Welcome to Generic Sorting!");

    // Check command line for parameters or prompt otherwise
    if (args.length == 0) {
      // Prompt the user for a file name
      System.out.print("Enter the file name: ");
      fileName = userInput.nextLine().trim();
    }
    else {
      fileName = args[0];
    }
		
    // Open file and read data
    try {
      Scanner s = new Scanner(new File(fileName));

      // Read 20 ints
      for (int i = 0; i < 20; i++) {
	intArr[i] = s.nextInt();
      }

      // Display the unsorted data
      System.out.println("Unsorted integers:");
      display(intArr);

      // Call to generic sorting method
      insertionSort(intArr);

      // Display the sorted data
      System.out.println("Sorted integers:");
      display(intArr);

      // Read 20 doubles
      for (int i = 0; i < 20; i++) {
	doubleArr[i] = s.nextDouble();
      }

      // Display the unsorted data
      System.out.println("Unsorted doubles:");
      display(doubleArr);

      // Call to generic sorting method
      insertionSort(doubleArr);

      // Display the sorted data
      System.out.println("Sorted doubles:");
      display(doubleArr);

      // Read 20 strings
      for (int i = 0; i < 20; i++) {
	stringArr[i] = s.next();
      }

      // Display the unsorted data
      System.out.println("Unsorted strings:");
      display(stringArr);

      // Call to generic sorting method
      insertionSort(stringArr);

      // Display the sorted data
      System.out.println("Sorted strings:");
      display(stringArr);

      // Read 20 Persons
      for (int i = 0; i < 20; i++) {
        // Read in Person data for type of Person, firstName, lastName, dob
        String personType = s.next();
        String firstName = s.next();
        String lastName = s.next();
        String dob = s.next();
        String govID, studentID;
        // Obtain day, month, year to create OCCCDate object
        int indexOfFirstSlash = dob.indexOf('/');
        int indexOfSecondSlash = dob.indexOf('/', indexOfFirstSlash + 1);
        int day = Integer.parseInt(dob.substring(0, indexOfFirstSlash));
        int month = Integer.parseInt(dob.substring(indexOfFirstSlash + 1, indexOfSecondSlash));
        int year = Integer.parseInt(dob.substring(indexOfSecondSlash + 1));
        OCCCDate d = new OCCCDate(day, month, year);
        d.setDayName(OCCCDate.HIDE_DAY_NAME);

        if (personType.equals("Person")) {
          Person p = new Person(firstName, lastName, d);
	  personArr[i] = p;
        }
	else if (personType.equals("RegisteredPerson")) {
          govID = s.next();  // Read in govID data 
          Person rp = new RegisteredPerson(firstName, lastName, d, govID);
          personArr[i] = rp;
        }
	else {
          // Must be OCCCPerson, read in govID and studentID
          govID = s.next();
          studentID = s.next();
          Person op = new OCCCPerson(firstName, lastName, d, govID, studentID);
          personArr[i] = op;
        }
      }

      // Display the unsorted data
      System.out.println("Unsorted Persons:");
      display(personArr);

      // Call to generic sorting method
      // I chose to sort Persons by the DOB
      insertionSort(personArr);

      // Display the sorted data
      System.out.println("Sorted Persons (sorted by DOB):");
      display(personArr);

      // Exit program
      System.out.println("Generic Sorting is complete, goodbye!");
      System.exit(0);
    }
    catch(FileNotFoundException e) {
      System.out.println(e.toString());
      System.exit(0);
    }
  }

  // Implementing a generic method for insertion sort
  public static <E extends Comparable<E>> void insertionSort(E[] list) {
    // Implemented the insertion sort method listed on pg. 889 of textbook, modified
    // to use methods found on pg. 759 to sort an array of comparable objects
    for (int i = 1; i < list.length; i++) {
      E currentValue = list[i];
      int k;
      for (k = i - 1; (k >= 0) && (list[k].compareTo(currentValue) > 0); k--) {
        list[k + 1] = list[k];
      }
      list[k + 1] = currentValue;
    }
  }

  // Implementing a generic method to display the arrays
  public static <E extends Comparable<E>> void display(E[] list) {
    for (int i = 0; i < list.length; i++) {
      System.out.print(list[i] + " ");
    }
    System.out.println();
    System.out.println();
  }
}