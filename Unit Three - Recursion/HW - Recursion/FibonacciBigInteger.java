// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 3 Homework
// BigInteger Fibonacci Program

import java.util.Scanner;
import java.math.BigInteger;

public class FibonacciBigInteger {
  public static BigInteger [] theMemo;	

  public static void main(String [] args) {
    int val = 0;
    if (args.length == 1) {
      val = Integer.parseInt(args[0]);
    }
    else {
      Scanner s = new Scanner(System.in);
      System.out.print("Please enter the rank of a Fibonacci number: ");
      val = s.nextInt();
    }

    // Section that uses the fib function for computation
    // Comment this section out if memoization is not needed for computation
    System.out.println();
    System.out.println("Computing the Fibonacci number using memoization...");
    System.out.println();

    // Measure the elapsed time for computations
    long t5 = System.currentTimeMillis();

    // Instantiate the array and set all values to 0
    theMemo = new BigInteger [val + 1];
    for (int i = 0; i <= val; i++) {
      theMemo[i] = BigInteger.ZERO;
    }
	
    // Perform the computation and display the result
    BigInteger fibResult = fib(val);
    System.out.println("Fib(" + val + ") = " + fibResult);

    long t6 = System.currentTimeMillis();

    // Display the elasped time and the number of digits in that Fibonacci Number
    System.out.println("The elapsed time is " + (t6 - t5) / 1000. + " seconds.");

    int fibDigits = fibResult.toString().length();
    System.out.println("Number of digits in the fibonacci number: " + fibDigits);
    
    // Section that uses the loop function for computation
    // Comment this section out if the loop function is not needed for computation
    System.out.println();
    System.out.println("Computing the Fibonacci number using the loop function...");
    System.out.println();

    // Measure the elapsed time for computations
    long t1 = System.currentTimeMillis();

    // Perform the computation and display the result
    BigInteger loopResult = fibonacci_loop(val);
    System.out.println("Fib(" + val + ") = " + loopResult);

    long t2 = System.currentTimeMillis();

    // Display the elasped time and the number of digits in that Fibonacci Number
    System.out.println("The elapsed time is " + (t2 - t1) / 1000. + " seconds.");

    int loopDigits = loopResult.toString().length();
    System.out.println("Number of digits in the fibonacci number: " + loopDigits);

    // Section that uses the recursive function for computation
    // Commenting this section out because recursion crashes with stack overflow on relatively small Fibonacci numbers
    /*
    System.out.println();
    System.out.println("Computing the Fibonacci number using the fibonacci_recursive function...");
    System.out.println();

    // Measure the elapsed time for computations
    long t3 = System.currentTimeMillis();

    // Perform the computation and display the result
    BigInteger recursiveResult = fibonacci_recursive(val);
    System.out.println("Fib(" + val + ") = " + recursiveResult);

    long t4 = System.currentTimeMillis();

    // Display the elasped time and the number of digits in that Fibonacci Number
    System.out.println("The elapsed time is " + (t4 - t3) / 1000. + " seconds.");

    int recursiveDigits = recursiveResult.toString().length();
    System.out.println("Number of digits in the fibonacci number: " + recursiveDigits);
    */

    // Exit program
    System.exit(0);
  }

  public static BigInteger fibonacci_loop(int f) {
    BigInteger result = BigInteger.ONE;
    if (f > 2) {
      BigInteger a = BigInteger.ONE;
      BigInteger b = BigInteger.ONE;
      for (int count = 3; count <= f; count++) {
        result = a.add(b);
	a = b;
	b = result;
      }
    }
    return result;
  }

  public static BigInteger fibonacci_recursive(int f) {
    BigInteger result = BigInteger.ONE;
    if (f > 2) {
      result = fibonacci_recursive(f - 1).add(fibonacci_recursive(f - 2));
    }
    return result;
  }

  public static BigInteger fib(int f) {
    BigInteger result = BigInteger.ONE;
      if (f > 2) {
        if (theMemo[f] != BigInteger.ZERO) {
	  result = theMemo[f];
	}
	else {
	  result = fib(f - 1).add(fib(f - 2));
	  theMemo[f] = result;
	}
      }
    return result;
  }
}