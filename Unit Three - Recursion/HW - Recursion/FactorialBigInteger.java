// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 3 Homework
// BigInteger Factorial Program

import java.util.Scanner;
import java.math.BigInteger;

public class FactorialBigInteger {
  public static void main(String [] args) {
    int val = 0;
    if (args.length == 1) {
      val = Integer.parseInt(args[0]);
    }
    else {
      Scanner s = new Scanner(System.in);
      System.out.print("Enter an integer for computation of the factorial: ");
      val = s.nextInt();
    }

    // Section that uses the factorial_loop function for computation
    // Comment this section out if the factorial_loop function is not needed for computation
    System.out.println();
    System.out.println("Computing the factorial using the factorial_loop function...");
    System.out.println();

    // Measure the elapsed time for computations
    long t1 = System.currentTimeMillis();

    // Perform the computation and display the result
    BigInteger loopResult = factorial_loop(val);
    System.out.println(val + "! = " + loopResult);

    long t2 = System.currentTimeMillis();

    // Display the elasped time and the number of digits in the factorial
    System.out.println("The elapsed time is " + (t2 - t1) / 1000. + " seconds.");

    int loopDigits = loopResult.toString().length();
    System.out.println("Number of digits in the factorial: " + loopDigits);	
	
    // Section that uses the factorial_recursive function for computation
    // Comment this section out if the factorial_recursive function is not needed for computation
    
    System.out.println();
    System.out.println("Computing the factorial using the factorial_recursive function...");
    System.out.println();

    // Measure the elapsed time for computations
    long t3 = System.currentTimeMillis();

    // Perform the computation and display the result
    BigInteger recursiveResult = factorial_recursive(val);
    System.out.println(val + "! = " + recursiveResult);

    long t4 = System.currentTimeMillis();

    // Display the elasped time and the number of digits in the factorial
    System.out.println("The elapsed time is " + (t4 - t3) / 1000. + " seconds.");

    int recursiveDigits = recursiveResult.toString().length();
    System.out.println("Number of digits in the factorial: " + recursiveDigits);
	
    // Exit program
    System.exit(0);
  }

  public static BigInteger factorial_loop(int n) {
    BigInteger result = BigInteger.ONE;
    if (n > 1) {
      for (int i = 1; i <= n; i++) {
	result = result.multiply(BigInteger.valueOf(i));
      }
    }
    return result;
  }

  public static BigInteger factorial_recursive(int n) {
    BigInteger result = BigInteger.ONE;
    if (n > 1) {
      result = BigInteger.valueOf(n).multiply(factorial_recursive(n - 1));
    }
    return result;
  }
}