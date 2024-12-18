// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 2 Homework
// Square Puzzle Reader

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Vector;

public class SquarePuzzleReader {
    public static void main(String [] args) {
        String fileName = "";
        Vector<Character> v = new Vector<Character>();
        Scanner s = new Scanner(System.in);

	System.out.println("Welcome to the Square Puzzle Reader.");
        System.out.println();

	// Accept user input via command line or prompt
	if (args.length > 0) {
	    fileName = args[0];
	} else {
            System.out.print("Enter the filename: ");
            fileName = s.nextLine();
	}		
	
	try {
	    Scanner f = new Scanner(new File(fileName));
            // Read entire file into a vector of characters
	    while (f.hasNextLine()) {
	    	char currentChar = f.next().charAt(0);
                // Only allowed values are digits 0-9, chars A-Z/a-z, and * or - that designates a blank square
	        if (Character.isLetterOrDigit(currentChar) || currentChar == '*' || currentChar == '-') {
	            v.add(currentChar);
	        }
	        else {
	            System.out.println("Error!! File contains values that are not allowed. Exiting.");
	            System.exit(0);
	        }
	    }

	    // Check if vector size contains number of entries for perfect square	
	    int vectorSize = v.size();
	    if (isPerfectSquare(vectorSize)) {
       	 	double vectorSquareRoot = Math.sqrt(vectorSize);
        	int numRows = (int)vectorSquareRoot;
        	int numCols = (int)vectorSquareRoot;
        	int[][] squareTable = new int [numRows][numCols];
	        int idx = 0;

		System.out.println("Input file:");
		System.out.println();

		// Display the read puzzle
		int k = 0;
		for (int i = 0; i < numRows; i++) {
		    for (int j = 0; j < numCols; j++) {
                        System.out.print(" " + v.get(k++) + " ");
		    }
		    System.out.println();
		}
		System.out.println();
		
	        // Write from the vector to the table
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

		System.out.println("Square table:");
		System.out.println();
		
		// Display the square table 
		for (int i = 0; i < numRows; i++) {
		    for (int j = 0; j < numCols; j++) {
                        // If entry is negative value, display space
			if (squareTable[i][j] == -1) {   
                            System.out.print("   ");
			} else {
                            System.out.printf("%3d", squareTable[i][j]);
			}
                    }
		    System.out.println();
		}
		// Exit program
		System.exit(0);
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

    // Method to check if the size of a vector will produce a perfect square
    public static boolean isPerfectSquare(int size) {
    	double vectorSquareRoot = Math.sqrt(size);
	if ((vectorSquareRoot * vectorSquareRoot) == (double)size) {
	    return true;
	}
	return false;		
    }
}