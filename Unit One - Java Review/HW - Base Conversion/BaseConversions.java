// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 1 Homework
// Base Conversions

import java.math.BigInteger;
import java.util.Scanner;

public class BaseConversions {
    public static void main (String [] args) {
        String valueToConvert, convertedValue;
        int initialBase, desiredBase;
        
        Scanner s = new Scanner(System.in);
        
        // Display a friendly greeting to the user
        System.out.println("Welcome to Base Conversions!");
        
        // Accept user input via command line or prompt
        if (args.length > 0) {
            valueToConvert = args[0];
        } else {
            System.out.println();
            System.out.print("Enter the value to convert: ");
            valueToConvert = s.nextLine();
        }
        
        if (args.length > 1) {
            initialBase = Integer.parseInt(args[1]);
            // Accept any base from 2 to 36
            if (initialBase < 2 || initialBase > 36) {
                System.out.println();
                System.out.print("Invalid initial base. ");
                System.out.println("Please rerun the program using a base from 2 to 36.");
                System.exit(0);
            }
        } else {
            // Check for valid base
            do {
                System.out.println();
                System.out.print("Enter the initial base: ");
                initialBase = s.nextInt();
                if (initialBase < 2 || initialBase > 36) {
                    System.out.println("Invalid initial base. Base must be between 2 and 36.");
                }
            } while (initialBase < 2 || initialBase > 36);
        }
        
        if (args.length > 2) {
            desiredBase = Integer.parseInt(args[2]);
            // Accept any base from 2 to 36
            if (desiredBase < 2 || desiredBase > 36) {
                System.out.println();
                System.out.print("Invalid desired base. ");
                System.out.println("Please rerun the program using a base from 2 to 36.");
                System.exit(0);
            }
        } else {
            // Check for valid base
            do {
                System.out.println();
                System.out.print("Enter the desired base: ");
                desiredBase = s.nextInt();
                if (desiredBase < 2 || desiredBase > 36) {
                    System.out.println("Invalid desired base. Base must be between 2 and 36.");
                }
            } while (desiredBase < 2 || desiredBase > 36);
        }
        
        // If String is not a legal expression of a number in the initial base, 
        // display an error message and exit the program
        if (!isValidInteger(valueToConvert, initialBase)) {
            System.out.println("Error!! Provided input is illegal expression of a number in the initial base. Exiting.");
            System.exit(0);
        }
        
        // Convert the value represented by the String in the initial base to the desired base
        convertedValue = convertInteger(valueToConvert, initialBase, desiredBase);
        // Display the result
        System.out.println();
        System.out.println("Converted result: " + convertedValue);
    }
    
    public static boolean isValidInteger(String theValue, int theBase) {
        // Return true if theValue is a valid expression in theBase, otherwise return false
        char currentChar;
        int numericValueOfChar;
        
        // Check each character in theValue
        for (int i = 0; i < theValue.length(); i++) {
            currentChar = theValue.charAt(i);
            // Get corresponding numeric value
            if (Character.isDigit(currentChar)) {
                numericValueOfChar = currentChar - '0';
            }
            else if (Character.isUpperCase(currentChar)) {
                numericValueOfChar = currentChar - 'A' + 10;
            } 
            else if (Character.isLowerCase(currentChar)) {
                numericValueOfChar = currentChar - 'a' + 10;
            }
            else {
                return false;
            }
            // Return false if value is invalid (not within base range)
            // Valid numericValueOfChar must be in range 0 - 35
            if (numericValueOfChar < 0 || numericValueOfChar >= theBase) {
                return false;
            }
        }
        return true;
    }
    
    // Converts theValue from initialBase to finalBase and returns the result as a String
    public static String convertInteger(String theValue, int initialBase, int finalBase) {
        // Creating BigInteger instances using BigInteger.valueOf(long) 
        BigInteger bigIntInitialBase = BigInteger.valueOf(Long.valueOf(initialBase));
        BigInteger bigIntFinalBase = BigInteger.valueOf(Long.valueOf(finalBase));
        BigInteger bigIntResult = BigInteger.ZERO;  // BigInteger class constant
        BigInteger bigIntPower = BigInteger.ONE;  // BigInteger class constant
        String temp = "", finalString = "";
        int charNumericValue = 0;
        
        // Convert the input String from the initial base into a BigInteger (in base 10)
        // Chp. 6 provides algorithm to convert to Base 10 BigInteger uing initial base.
        // Use algorithm hn*base.pow(n) + hn-1*base.pow(n-1)...
        for (int i = (theValue.length() - 1); i >= 0; i--) {
            char currentChar = theValue.charAt(i);
            // Check numeric ranges of the chars in theValue
            if (currentChar >= '0' && currentChar <= '9') {
                charNumericValue = currentChar - '0';
            }
            else if (currentChar >= 'A' && currentChar <= 'Z') {
                charNumericValue = currentChar - 'A' + 10;
            }
            else if (currentChar >= 'a' && currentChar <= 'z') {
                charNumericValue = currentChar - 'a' + 10;
            }
            else {
                System.out.println("Invalid character");
            }
            // Implement algorithm
            BigInteger bigIntNumericValue = BigInteger.valueOf(Long.valueOf(charNumericValue));
            // Multiply current value in string by power and add to result
            bigIntResult = bigIntResult.add(bigIntNumericValue.multiply(bigIntPower));
            // Acts in place of exponent in algorithm, increases during each run in loop
            bigIntPower = bigIntPower.multiply(bigIntInitialBase);
        }
        
        // Convert base-10 BigInteger into a String representation of that value in the desired target base
        // Stop when quotient reaches 0
        while (!bigIntResult.equals(BigInteger.ZERO)) {
            // Use mod to get remainder
            int remainder = bigIntResult.mod(bigIntFinalBase).intValue();
            // Write value of remainder to a temporary String
            if (remainder < 10) {
                temp += (char)('0' + remainder);
            }
            else {
                temp += (char)('A' + remainder - 10);
            }
            bigIntResult = bigIntResult.divide(bigIntFinalBase);
        }
        // 'Read' the remainders from bottom to top and write to finalString
        for (int j = (temp.length() - 1); j >= 0; j--) {
            finalString += temp.charAt(j);
        }
        return finalString;
    }
}