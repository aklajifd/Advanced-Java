// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 2 Homework
// Sieve of Eratosthenes

import java.util.Scanner;

public class SieveOfEratosthenes {
    public static void main(String [] args) {
	int startValue = 0, stopValue = 0;
	Scanner s = new Scanner(System.in);

        // Display a friendly greeting to the user
	System.out.println("Greetings, I can implement the Sieve of Eratosthenes! Let's find some prime numbers.");
        System.out.println();

        // Accept user input via command-line parameters or prompt
	if (args.length == 0) {
            do {
		// Prompt the user for a start value > 1.
		System.out.print("Enter a start value greater than 1: ");
		startValue = s.nextInt();
	
		if (startValue <= 1) {
                    System.out.println();
                    System.out.println("Invalid start value. Value must be greater than 1.");
		    System.out.println();
		}
            }  while (startValue <= 1);

            // Prompt the user for a stop value, which is the size of the array - 1	
            System.out.print("Enter a stop value: ");
            stopValue = s.nextInt();		
        }
	if (args.length == 1) {
            startValue = Integer.parseInt(args[0]);
	
            // Prompt the user for a stop value, which is the size of the array - 1	
            System.out.print("Enter a stop value: ");
            stopValue = s.nextInt();
	}
	if (args.length >= 2) {
            startValue = Integer.parseInt(args[0]);
            stopValue = Integer.parseInt(args[1]);
	}

        // If stopValue = size of array - 1, array size must be stopValue + 1
	int arraySize = stopValue + 1;	

	// Use the stop value to create an array of type boolean
	boolean[] theArray = new boolean[arraySize];
        // Set every value to true
	for (int i = 0; i < arraySize; i++) {
            theArray[i] = true;
	}
        
        // Square root of stop value to be used in shortcut for finding prime numbers
	int stopValueSquareRoot = (int)Math.sqrt(stopValue);
	
	// Execution start time
	long startTime = System.nanoTime();

	// Execute the Sieve of Eratosthenes algorithm (using false to mark off entries)
	// Shortcut for algorithm uses the square root of the stop value in loop
	for (int i = 2; i <= stopValueSquareRoot; i++) {   // Start with 2, mark off every 2nd integer
            if (theArray[i] == false) {    // Check if current iteration is marked
            	continue;    // Move to the next iteration to check for next unmarked number
            }
            else {
                // 'Mark off' every jth integer
            	for (int j = i + i; j < arraySize; j=j+i) {
                    theArray[j] = false;
		}
            }
	}
	// Execution end time
	long endTime = System.nanoTime();
	long totalExecutionTime = endTime - startTime;
        // Divide by 1 billion to convert from nanoseconds to seconds
	double execTimeInSec = totalExecutionTime / 1000000000.0;
	
	// Display the total number of primes found in the interval [start, stop]
	int numberOfPrimes = 0;
	for (int i = startValue; i < arraySize; i++) {
            if (theArray[i] == true) {
            	numberOfPrimes++;
            }
	}
	System.out.printf("Total number of primes in range %,d to %,d: %,d \n", startValue, stopValue, numberOfPrimes);
	System.out.printf("Execution time: %f sec", execTimeInSec);
	System.out.println();
        // Exit program
	System.exit(0);
    }
}