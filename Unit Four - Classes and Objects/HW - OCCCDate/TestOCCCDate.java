// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 4 Homework
// TestOCCCDate Program

import java.util.Scanner;

public class TestOCCCDate {
  public static void main(String [] args) {
    String userInput = "";
    int day = 0, month = 0, year = 0;
    Scanner s = new Scanner(System.in);
    
    System.out.println("Welcome to the TestOCCCDate program!");
    System.out.println();
  
    // Enter today's date, then create and display the corresponding OCCCDate. 
    // Set the format of this OCCCDate to numeric, US and display the name of the day of the week
 
    System.out.print("Please enter today's date in format mm/dd/yyyy : ");
    userInput = s.nextLine();
    System.out.println();
    
    month = Integer.parseInt(userInput.substring(0,2));
    day = Integer.parseInt(userInput.substring(3,5));
    year = Integer.parseInt(userInput.substring(6));

    OCCCDate d1 = new OCCCDate(day, month, year);
    d1.setDateFormat(OCCCDate.FORMAT_US);
    d1.setStyleFormat(OCCCDate.STYLE_NUMBERS);
    d1.setDayName(OCCCDate.SHOW_DAY_NAME);
    
    System.out.println("Today's date: " + d1.toString());
    System.out.println();

    // Enter and display an OCCCDate created from February 29, 2022 (should display as March 1, 2022). 
    // Set the format of this OCCCDate to numeric, European and display the name of the day of the week  

    OCCCDate d2 = new OCCCDate(29, 2, 2022);
    d2.setDateFormat(OCCCDate.FORMAT_EURO);
    d2.setStyleFormat(OCCCDate.STYLE_NUMBERS);
    d2.setDayName(OCCCDate.SHOW_DAY_NAME);
    
    System.out.println("OCCCDate created from February 29, 2022: " + d2.toString());
    System.out.println();

   // Enter and display an OCCCDate created from January 365, 2022 (should display as December 31). 
   // Format: names, US and display the name of the day of the week

    OCCCDate d3 = new OCCCDate(365, 1, 2022);
    d3.setDateFormat(OCCCDate.FORMAT_US);
    d3.setStyleFormat(OCCCDate.STYLE_NAMES);
    d3.setDayName(OCCCDate.SHOW_DAY_NAME);
    
    System.out.println("OCCCDate created from January 365, 2022: " + d3.toString());
    System.out.println();
 
   // James T. Kirk will be born on March 22, 2233. Enter and create that OCCCDate; format as names, European. 
   // Display the name of the day of the week. 

    OCCCDate d4 = new OCCCDate(22, 3, 2233);
    d4.setDateFormat(OCCCDate.FORMAT_EURO);
    d4.setStyleFormat(OCCCDate.STYLE_NAMES);
    d4.setDayName(OCCCDate.SHOW_DAY_NAME);
    
    System.out.println("OCCCDate created for James T. Kirk's birthday: " + d4.toString());
 
    // Exit program
    System.out.println();
    System.out.println("Goodbye!");
    System.exit(0);
  }
}
