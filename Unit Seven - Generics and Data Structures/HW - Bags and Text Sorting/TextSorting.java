// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 7 Homework
// Bags and Text Sorting - Programming Exercise 21.2 (modified)(12th Edition)

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class TextSorting {
  public static void main(String [] args) {
    String inputFile = "", outputFile = "";
    Scanner userInput = new Scanner(System.in);

    // 21.6 Case Study on pg. 833 of textbook provides example using TreeMap
    // Create TreeMap to holds words as key and count as value
    Map<String, Integer> map = new TreeMap<>();

    // Greet user
    System.out.println("Welcome to Text Sorting!");

    // No command-line parameters, prompt for input filename
    if (args.length == 0) {  
      System.out.print("Enter the file name: ");
      inputFile = userInput.nextLine().trim();
    }
    // One command-line parameter, use parameter to create output file name
    else if (args.length == 1) { 
      inputFile = args[0];
      // Strip away file extension and replace with .out
      int indexOfDot = inputFile.indexOf('.');
      String sub = inputFile.substring(0, indexOfDot);
      outputFile = sub + ".out";
    }
    // 2 input parameters, second parameter is name of output file
    else {  
      inputFile = args[0];
      outputFile = args[1];
    }
		
    // Open file and read data
    try {
      Scanner s = new Scanner(new File(inputFile));

      while (s.hasNext()) {
        // Split line of input into words
        String[] words = s.nextLine().split("[\\s+\\p{P}]");
        for (int i = 0; i < words.length; i++) {
          String key = words[i];
          if (key.length() > 0) {
            // Check if key begins with uppercase letter
            boolean upperFlag = Character.isUpperCase(key.charAt(0));
            // Create uppercase version of key to check while processing
            String capitalKey = Character.toUpperCase(key.charAt(0)) + key.substring(1);

            // Case for uppercase key and map also contains lowercase version 
            // of key, keep lowercase version and increment value
            if (upperFlag && map.containsKey(key.toLowerCase())) {
              int value = map.get(key.toLowerCase()); 
              value++;
              map.put(key.toLowerCase(), value);
            }
            // Case for uppercase key and map doesn't contain lowercase version 
            // of key, place key in map or update key value 
            else if (upperFlag && !map.containsKey(key.toLowerCase())) {
              if (!map.containsKey(key)) {
                map.put(key, 1);
              }
              else {
                int value = map.get(key);
                value++;
                map.put(key, value);
              }
            }
            // Case for lowercase key but map also contains uppercase version 
            // of key, keep lowercase version and increment value
            else if (!upperFlag && map.containsKey(capitalKey)) {
              int value = map.get(capitalKey);
              map.remove(capitalKey); 
              value++;
              map.put(key, value);
            }
            // Other cases for lowercase key, place key in map or increment value
            else {
              if (!map.containsKey(key)) {
                map.put(key, 1);
              }
              else {
                int value = map.get(key);
                value++;
                map.put(key, value);
              }
            }
          }
        }
      }

      // Pg. 834 shows example of creating list of map entries and creating a Comparator for sorting
      List<Map.Entry<String, Integer>> entries = new ArrayList<>(map.entrySet());
      Collections.sort(entries, (entry1, entry2) -> {
        // Sort by comparing lowercase version of keys for the entries
        return entry1.getKey().toLowerCase().compareTo(entry2.getKey().toLowerCase());
      });

      // No command-line parameters given, display results to console
      if (outputFile.equals("")) {
        // Also shown on pg. 834, loop through list
        // Display key and also value if value greater than 1
        for (Map.Entry<String, Integer> entry: entries) {
          if (entry.getValue() > 1) {
            System.out.println(entry.getKey() + " (" + entry.getValue() + ")");
          }
          else {
            System.out.println(entry.getKey());
          }
        }
      }
      // One or more command-line parameters given, write results to output file
      else {
        PrintWriter output = new PrintWriter(outputFile);
        for (Map.Entry<String, Integer> entry: entries) {
          if (entry.getValue() > 1) {
            output.println(entry.getKey() + " (" + entry.getValue() + ")");
          }
          else {
            output.println(entry.getKey());
          }
        }
        output.close();
        System.out.println();
        System.out.println("Finished writing to output file!");
      }

      // Exit program
      System.exit(0);
    }
    catch(FileNotFoundException e) {
      System.out.println(e.toString());
      System.exit(0);
    }
  }
}