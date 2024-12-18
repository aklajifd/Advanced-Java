// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 3 Homework
// Sudoku Solver

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class SudokuSolver {
  public static void main(String [] args) {
    String fileName = "";
    Vector<Character> v = new Vector<Character>();
    Scanner s = new Scanner(System.in);
    boolean completedPuzzle = false;

    // Display a friendly greeting to the user
    System.out.println();
    System.out.println("Welcome to the Sudoku Solver!");
    System.out.println();

    // Accept filename via command line or prompt
    if (args.length > 0) {
      fileName = args[0];
    } else {
        System.out.print("Enter the filename: ");
        fileName = s.nextLine();
    }		
	
    // Attempt to open the file
    try {
      Scanner f = new Scanner(new File(fileName));
      // Read numbers in file as type char, populate a table with them 
      while (f.hasNextLine()) {
        char currentChar = f.next().charAt(0);
        // Only allowed values are digits 0-9, chars A-Z/a-z, and * or - that designates an empty spot
	if (Character.isLetterOrDigit(currentChar) || currentChar == '*' || currentChar == '-') {
	  v.add(currentChar);
	}
	else {
	  System.out.println("Error!! File contains values that are not allowed. Exiting.");
	  System.exit(0);
	}
      }

      // Determine dimensionality of puzzle for the generic Sudoku solver
      int vectorSize = v.size();
      if (isPerfectSquare(vectorSize)) {
        double vectorSquareRoot = Math.sqrt(vectorSize);
        int tableSize = (int)vectorSquareRoot;
        int numRows = (int)vectorSquareRoot;
        int numCols = (int)vectorSquareRoot;
        int[][] squareTable = new int [numRows][numCols];
	int idx = 0;

	// Display the contents of the file 
	System.out.println("File contents:");
	System.out.println();
	int k = 0;
	for (int i = 0; i < numRows; i++) {
	  for (int j = 0; j < numCols; j++) {
            System.out.print(" " + v.get(k++) + " ");
	  }
	  System.out.println();
	}  
	System.out.println();
		
	// Write from the vector to the puzzle
	for (int i = 0; i < numRows; i++) {
          for (int j = 0; j < numCols; j++) {
            char currentChar = v.get(idx++);
	    if (Character.isDigit(currentChar)) {	
	      squareTable[i][j] = currentChar - '0';
	    }
	    if (Character.isUpperCase(currentChar)) {
              squareTable[i][j] = currentChar - 'A' + 10;
	    }
	    if (Character.isLowerCase(currentChar)) {
              squareTable[i][j] = currentChar - 'a' + 10;
	    }
	    if (currentChar == '*' || currentChar == '-') {
              squareTable[i][j] = -1;    // Use negative value for spaces
	    }
	  }
	}

	// Solve the puzzle using the algorithm given
	completedPuzzle = solvePuzzle(squareTable, tableSize);

        // Display the solved puzzle, or an appropriate error message if no solution is found
	if (completedPuzzle) {
	  printPuzzle(squareTable, numRows, numCols);
	  System.exit(0);    // Exit program
	}
	else {
	  System.out.println("No solution was found for the provided puzzle.");
	  System.exit(0);    // Exit program
	}
      }
      else {
	System.out.println("Error!! File does not contain number of entries for a perfect square. Exiting.");
	System.exit(0);
      } 
    }
    catch (FileNotFoundException e) {
      System.out.println(e.toString());
      System.exit(0);
    }
  }

  // Method to display the solved puzzle
  public static void printPuzzle(int[][] thePuzzle, int numRows, int numCols) {
    System.out.println("Solved puzzle:");
    System.out.println();
    for (int r = 0; r < numRows; r++) {
      for (int c = 0; c < numCols; c++) {
        System.out.printf("%3d", thePuzzle[r][c]);
      }
      System.out.println();
    }
  } 

  // Method to check if the size of a vector will produce a perfect square
  public static boolean isPerfectSquare(int size) {
    double vectorSquareRoot = Math.sqrt(size);
    if ((vectorSquareRoot * vectorSquareRoot) == (double)size) {
      return true;
    }
    return false;		
  }

  // Method that returns true if every spot in the puzzle has a value
  public static boolean isCompletePuzzle(int[][] thePuzzle, int puzzleSize) {
    for (int r = 0; r < puzzleSize; r++) {
      for (int c = 0; c < puzzleSize; c++) {
        if (thePuzzle[r][c] == -1) {
          return false;
        }
      }
    }
    return true;
  }

  // Method that returns true if the puzzle contains no counterexamples
  public static boolean isValidPuzzle(int[][] thePuzzle, int puzzleSize) {
    // Check if duplicate value exists in each row
    for (int r = 0; r < puzzleSize; r++) {
      // Using an array of type boolean as a 'flag' to signal if a duplicate is found
      boolean[] flag = new boolean[puzzleSize + 1];
      for (int c = 0; c < puzzleSize; c++) {
        int currentValue = thePuzzle[r][c];
        // Ignore the empty spaces
        if (currentValue == -1) {
          continue;
        }
        else {
          if (flag[currentValue] == false) {    // If number hasn't occurred in row, value in flag array will be false 
            flag[currentValue] = true;    // Mark value in flag array as true so future iterations can catch duplicates
	  }
	  else {
	    return false;    // Duplicate was found in row, return false
          }
        }
      }
    }

    // Check if duplicate value exists in each column
    for (int c = 0; c < puzzleSize; c++) {
      // Using an array of type boolean as a 'flag' to signal if a duplicate is found
      boolean[] flag = new boolean[puzzleSize + 1];
      for (int r = 0; r < puzzleSize; r++) {
        int currentValue = thePuzzle[r][c];
        // Ignore the empty spaces
        if (currentValue == -1) {
          continue;
        }
        else {
          if (flag[currentValue] == false) {    // If number hasn't occurred in column, value in flag array will be false 
            flag[currentValue] = true;    // Mark value in flag array as true so future iterations can catch duplicates
	  }
	  else {
	    return false;    // Duplicate was found in column, return false
          }
        }
      }
    }

    // Check if duplicate value exists in each subgrid
    int subSize = (int)Math.sqrt(puzzleSize);

    // Loop through the larger puzzle as a whole
    for (int subRow = 0; subRow < puzzleSize; subRow += subSize) {
      for (int subCol = 0; subCol < puzzleSize; subCol += subSize) {
      // Using an array of type boolean as a 'flag' to signal if a duplicate is found
        boolean[] flag = new boolean[puzzleSize + 1];
        // Now loop through each subgrid and check for duplicate values
        for (int r = 0; r < subSize; r++) {
          for (int c = 0; c < subSize; c++) {
	    // Current value indices are found by adding the outer and inner indices
            int currentValue = thePuzzle[subRow + r][subCol + c];
            // Ignore the empty spaces
            if (currentValue == -1) {
              continue;
            }
            else {
              if (flag[currentValue] == false) {    // If number hasn't occurred within subgrid, value in flag array will be false 
                flag[currentValue] = true;    // Mark value in flag array as true so future iterations can catch duplicates
	      }
	      else {
	        return false;    // Duplicate was found within subgrid, return false
              }
            }
          }
        }
      }
    }
    // Return true if all above checks have passed
    return true;
  }

  // Method that returns true if puzzle is valid and complete
  public static boolean isSolvedPuzzle(int[][] thePuzzle, int puzzleSize) {
    if (isCompletePuzzle(thePuzzle, puzzleSize) && isValidPuzzle(thePuzzle, puzzleSize)) {
      return true;
    }
    return false;
  }

  // Method that implements the solution strategy
  public static boolean solvePuzzle(int[][] thePuzzle, int puzzleSize) {
    // Base case
    // Return true if puzzle is valid and complete
    if (isSolvedPuzzle(thePuzzle, puzzleSize)) {
      return true;
    }
    
    // Recursive case
    // Locate the first blank space in the puzzle
    for (int r = 0; r < puzzleSize; r++) {
      for (int c = 0; c < puzzleSize; c++) {
        if (thePuzzle[r][c] == -1) {    // Blank space found
          for (int candidate = 1; candidate <= puzzleSize; candidate++) {    
            thePuzzle[r][c] = candidate;    // Drop in a candidate
            // Check if candidate is valid and make a recursive call to solvePuzzle() with the updated puzzle
            // If false, next loop run will try next candidate and make another recursive call
            if (isValidPuzzle(thePuzzle, puzzleSize) && solvePuzzle(thePuzzle, puzzleSize)) {
              return true;    // Return true if true
            }
          }
          thePuzzle[r][c] = -1;    // No next candidate, reset candidate cell to blank value and return false
	  return false;
        }        
      }
    }
    return false;
  }
}
