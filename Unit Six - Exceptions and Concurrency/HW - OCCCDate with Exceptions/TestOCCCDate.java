// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 6 Homework
// TestOCCCDate File

import java.util.Scanner;

public class TestOCCCDate {
  public static void main(String [] args) {
    String userInput;
    int day = 0, month = 0, year = 0;
    Scanner s = new Scanner(System.in);
    
    System.out.println("Welcome to the TestOCCCDate program!");
    System.out.println();
  
    // Prompt the user to enter dates, then create and display the corresponding OCCCDates. Throw exceptions as needed.
    // Set the output format to US style dates, show the name of month and display the day of the week
 
    System.out.println("Enter dates in format mm/dd/yyyy. Press \"Enter\" when done.\n");

    while(true) {
      // Note: Can comment out the try/catch block so thrown exception ends the program
      // Try/catch block recovers from the error and continues execution
      try {
        // System.out.println("Executing without try/catch block, thrown exception ends the program...");
        System.out.print("Please enter a date: ");
        userInput = s.nextLine();
        if (userInput == "") {
          break;
        }
        System.out.println();
    
        month = Integer.parseInt(userInput.substring(0,2));
        day = Integer.parseInt(userInput.substring(3,5));
        year = Integer.parseInt(userInput.substring(6));
           
        OCCCDate d = new OCCCDate(day, month, year);
        d.setDateFormat(OCCCDate.FORMAT_US);
        d.setStyleFormat(OCCCDate.STYLE_NAMES);
        d.setDayName(OCCCDate.SHOW_DAY_NAME);
    
        System.out.println("Entered date: " + d.toString());
        System.out.println();
      }
      catch (InvalidOCCCDateException e) { 
        System.out.println(e.toString());
        System.out.println();
      }
    }

    // Exit program
    System.out.println();
    System.out.println("Goodbye!");
    System.exit(0);
  }
}
