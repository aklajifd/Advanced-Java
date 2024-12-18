// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 3 Homework
// Palindromes Program

import java.util.Scanner;

public class Palindromes {
  public static void main(String [] args) {
    int numOfOrdinary = 0, numOfStrict = 0, numOfNon = 0;
    boolean firstTest = true;
    boolean secondTest = true;
    String userInput = "";
    Scanner s = new Scanner(System.in);
	
    // Display a friendly greeting to the user
    System.out.println("Hello and welcome to Palindromes!");
    System.out.println("Enter a string and I will tell you if the string is a palindrome or not.");

    // Continue processing entries until the empty string is entered
    do {
      // Creating a new StringBuilder
      StringBuilder firstCopy = new StringBuilder();
      StringBuilder secondCopy = new StringBuilder();
      System.out.println();
      System.out.print("Please enter a string (or press 'Enter' to exit): ");
      boolean strictFlag = false;
      userInput = s.nextLine().trim();
      if (userInput == "") {
        break;
      }

      // Make a copy of the string in which punctuation is removed and all text is upper case
      for (int i = 0; i < userInput.length(); i++) {
	if (Character.isLetterOrDigit(userInput.charAt(i))) {  
          firstCopy.append(Character.toUpperCase(userInput.charAt(i)));
	}
	if (userInput.charAt(i) == ' ') {
	  strictFlag = true;
          firstCopy.append(' ');
	}
      }
      // Invoke the recursive palindrome function using the copy and note the answer
      firstTest = isPalindrome(firstCopy);

      // Make another copy of the string, removing both punctuation and spaces
      for (int i = 0; i < userInput.length(); i++) {
	if (Character.isLetterOrDigit(userInput.charAt(i))) {
	  secondCopy.append(Character.toUpperCase(userInput.charAt(i)));
	}
      }

      // Invoke the recursive palindrome function on that copy and note the answer
      secondTest = isPalindrome(secondCopy);

      // Display the result
      if (firstTest == true && strictFlag == true) {
	numOfStrict++;
	System.out.println("Provided string is a strict palindrome.");
      }
      else if (secondTest == true) {
	numOfOrdinary++;
	System.out.println("Provided string is an ordinary palindrome.");
      }
      else {
	numOfNon++;
	System.out.println("Provided string is not a palindrome.");
      }
    } while (userInput != "");
	
    // On exit, provide a summary of the results
    System.out.println();
    System.out.println("Number of ordinary palindromes: " + numOfOrdinary);
    System.out.println("Number of strict palindromes: " + numOfStrict);
    System.out.println("Number of non-palindromes: " + numOfNon);

    // Exit program
    System.exit(0);
  }

  // Recursive palindrome function
  public static boolean isPalindrome(StringBuilder sb) {
    // Base case
    if (sb.length() <= 1) {
      return true;
    }
    // General case
    else {
      if (sb.charAt(0) == sb.charAt(sb.length() - 1)) {
        sb.deleteCharAt(sb.length() - 1);
	sb.deleteCharAt(0);
	return isPalindrome(sb);
      }
      else {
        return false;
      }
    }
  }
}