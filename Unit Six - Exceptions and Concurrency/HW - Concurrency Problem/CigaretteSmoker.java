// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 6 Homework
// Concurrency Problem - Cigarette Smoker's Problem

import java.util.concurrent.Semaphore;
import java.util.Scanner;

public class CigaretteSmoker {
  static int theTable = -1;  // value for cleared table
  static int numOfSmokers = 0;
  static String[] theTableItems = new String[]{ "matches", "tobacco", "paper" };
  static Semaphore tableSemaphore = new Semaphore(1);
  static Semaphore agentSemaphore = new Semaphore(0);

  public static void mySleep() {
    try {
      Thread.sleep((int)(Math.random()*1000));
    }
    catch (InterruptedException e) {
      // do nothing
    }
  }

  public static void main(String [] args) {
    Agent a = new Agent();
    Scanner scan = new Scanner(System.in);

    System.out.println("Welcome to Cigarette Smokers!");
    System.out.print("Enter the number of smokers (minimum of 3 needed): ");
    numOfSmokers = scan.nextInt();
    Smoker[] s = new Smoker[numOfSmokers];
    System.out.println("Starting the program with " + numOfSmokers + " smokers...");

    a.start();
    for (int i = 0; i < numOfSmokers; i++) {
      s[i] = new Smoker(i);
      System.out.println("Starting Smoker " + i + "...");
      s[i].start();
    }
  }

  private static class Agent extends Thread {
    public Agent() {
      super();
    }
 
    public void run() {
      while(true) {
        mySleep();
        System.out.println("Agent is running...");
        System.out.println("Agent is trying to acquire the table...");
        try {
          tableSemaphore.acquire();
          System.out.println("Agent has the table...");
          mySleep();
          theTable = (int)(Math.random()*numOfSmokers);  // assign random value to theTable
          System.out.println("Agent put " + theTableItems[theTable] + " on the table...");

          // Agent is done restocking table, releases table
          System.out.println("Agent done restocking, releasing the table...");
          tableSemaphore.release();
          System.out.println("Agent going to sleep...");
          agentSemaphore.acquire();  // puts Agent to sleep in wait queue
        }
        catch (InterruptedException e) {}
      }
    }
  }

  private static class Smoker extends Thread {
    int i;
    public Smoker(int i) {
      super();
      this.i = i;
    }

    public void run() {
      while (true) {
        mySleep();
        System.out.println("Smoker " + i + " trying to acquire the table...");
        try { 
          tableSemaphore.acquire(); 
          System.out.println("Smoker " + i + " has the table!");
          System.out.println("Smoker " + i + " needs " + theTableItems[i] + "...");
          mySleep();

          if (theTable == -1) {  // table is empty
            System.out.println("Smoker " + i + " didn't find anything, table is empty!");
          }
          else if (theTable == i) {  // table has item smoker needs
            System.out.println("Smoker " + i + " found " + theTableItems[theTable] + "!");
            System.out.println("Smoker " + i + " is smoking...");
            theTable = -1;  //  Smoker clears the table

            // Smoker issues release on agentSemaphore
            agentSemaphore.release();  // Agent wakes up, can attempt to acquire table
            System.out.println("Agent is awake!");  
          }
          else {  // table has item smoker doesn't need
            System.out.println("Smoker " + i + " didn't find " + theTableItems[i] + "...");
            System.out.println("Current item on the table is " + theTableItems[theTable] + "...");
          }

          System.out.println("Smoker " + i + " releasing table and going to sleep...");
          tableSemaphore.release();
        }
        catch (InterruptedException e) {}
      }
    }
  }
}