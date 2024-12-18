// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 7 Homework
// Iterators and Linked Lists - Programming Exercise 20.6 (12th Edition)

import java.util.Scanner;
import java.util.LinkedList;

public class IteratorLinkedList {
  public static void main(String [] args) {
    int numOfEntries;
    long total = 0;
    Scanner s = new Scanner(System.in);
    LinkedList<Long> myList = new LinkedList<Long>();

    System.out.println("Welcome to the Iterators and Linked Lists program!");
  
    if (args.length == 0) {
      System.out.print("Enter the number of entries to generate: ");
      numOfEntries = s.nextInt();
    }
    else {
      numOfEntries = Integer.parseInt(args[0]);
    }

    // Fill LinkedList with random values
    for (int i = 0; i < numOfEntries; i++) {
      myList.add((long)((Math.random()) * 10000));
    }

    System.out.println();
    System.out.println("Traversing using iterators...");
    double startTime = System.currentTimeMillis();
    // Traverse the list and add each value to total
    // From pg. 786 of textbook, foreach loop uses iterator implicitly
    for (long e: myList) {
      total += e;
    }
    double stopTime = System.currentTimeMillis();
  
    System.out.println("It took " + ( stopTime - startTime ) / 1000 + " seconds");
    System.out.println("Total of the values in the LinkedList: " + total);
    System.out.println();
    
    total = 0;
    System.out.println("Traversing using for loop and get...");
    startTime = System.currentTimeMillis();
    // Traverse the list and add each value to total
    for (int i = 0; i < myList.size(); i++) {
      total += myList.get(i);
    }
    stopTime = System.currentTimeMillis();
  
    System.out.println("It took " + ( stopTime - startTime ) / 1000 + " seconds");
    System.out.println("Total of the values in the LinkedList: " + total);
    
    // Exit program
    System.exit(0);
  }
}