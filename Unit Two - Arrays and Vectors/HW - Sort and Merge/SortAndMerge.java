// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 2 Homework
// Sort and Merge

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Vector;

public class SortAndMerge {    
    public static void main(String [] args) {
    	String firstFileName = "", secondFileName = "", thirdFileName = "";

	// Scanner object
	Scanner userInput = new Scanner(System.in);

	// Display a friendly greeting to the user
	System.out.println("Hello and welcome to Sort and Merge!");
        System.out.println();

        // Check command line for parameters or prompt otherwise
	if (args.length == 0) {
            // Prompt the user for a file name (a list of numbers to be sorted)
            System.out.print("Enter the first file name: ");
            firstFileName = userInput.nextLine().trim();
		
            // Prompt the user for a second file name (another list of numbers to be sorted)
            System.out.print("Enter the second file name: ");
            secondFileName = userInput.nextLine().trim();
	}
        if (args.length == 1) {
            firstFileName = args[0];
            // Prompt the user for a second file name (another list of numbers to be sorted)
            System.out.print("Enter the second file name: ");
            secondFileName = userInput.nextLine().trim();
	}
        if (args.length == 2) {
            firstFileName = args[0];
            secondFileName = args[1];
	}
        if (args.length >= 3) {
            firstFileName = args[0];
            secondFileName = args[1];
            thirdFileName = args[2];
	}
			
        // Open and read the data in the first file
	try {
            Scanner f1 = new Scanner(new File(firstFileName));
            int firstArraySize = f1.nextInt();    // Read the first value in file
            int [] firstArray = new int [firstArraySize];

            // Read the rest of the file to populate the array
            for (int i = 0; i < firstArray.length; i++) {
		firstArray[i] = f1.nextInt();
            }

 	    // Display the unsorted data
	    System.out.println("First file unsorted data:");
	    displayArray(firstArray);
	
            // Call to sorting method that uses an algorithm other than Array.sort
            firstArray = repeatNaturalMerge(firstArray);

            // Display the sorted data
	    System.out.println();
	    System.out.println("First file sorted data:");
            displayArray(firstArray);

            // Open and read the data in the second file
            Scanner f2 = new Scanner(new File(secondFileName));
            int secondArraySize = f2.nextInt();    // Read the first value in file
            int [] secondArray = new int [secondArraySize];
            
            // Read the rest of the file to populate the array
            for (int i = 0; i < secondArray.length; i++) {
            	secondArray[i] = f2.nextInt();
            }

 	    // Display the unsorted data
	    System.out.println("Second file unsorted data:");
	    displayArray(secondArray);	
	
            // Call to sorting method
            secondArray = repeatNaturalMerge(secondArray);

            // Display the sorted data
	    System.out.println();
	    System.out.println("Second file sorted data:");
            displayArray(secondArray);

            // Call to merge method that merges the 2 sorted arrays into a single sorted
            // array using a linear algorithm that makes only one pass through the arrays
            int[] finalArray = new int[firstArraySize + secondArraySize];
            finalArray = merge(firstArray, secondArray, finalArray);

            // Display the merged sorted data
	    System.out.println("The data from both files merged and sorted:");
            displayArray(finalArray);

            // If third file name not provided as a command-line parameter, prompt user
            if (thirdFileName == "") {
            	System.out.print("Enter the third file name: ");
            	thirdFileName = userInput.nextLine().trim();
	        System.out.println();
            }

            // Create that file and dump the merged list to the file
            PrintWriter output = new PrintWriter(thirdFileName);
            output.println(finalArray.length);    // Write the item count of the merged result to the file
            for (int i = 0; i < finalArray.length; i++) {
                output.print(finalArray[i] + " ");    // Write the data item to the file
            }
            output.close();

            // Exit program
	    System.out.println("Data successfully written to output file, goodbye!");
            System.exit(0);
	}
	catch(FileNotFoundException e) {
            System.out.println(e.toString());
            System.exit(0);
	}
    }

    // Method used to split a list into 2 sub-lists, one element at a time
    public static int[] naturalSplit(int[] theList) {
        Vector<Integer> v1 = new Vector<Integer>();
        Vector<Integer> v2 = new Vector<Integer>();
	
        int currentValue = theList[0], previousValue = currentValue;
        boolean flag = true;

        // Flag used to write alternating values to the sub-lists
        for (int i = 0; i < theList.length; i++) {
            currentValue = theList[i];
            if (flag) {
                if (currentValue >= previousValue) {
                    v1.add(currentValue);
                }
                else {
                    v2.add(currentValue);
                    flag = !flag;
                }
            }
            else {
                if (currentValue >= previousValue) {
                    v2.add(currentValue);
                }
                else {
                    v1.add(currentValue);
                    flag = !flag;
                }
            }
            previousValue = currentValue;
        }
        // Write values in each vector to arrays
	int[] arr1 = new int[v1.size()];
	int[] arr2 = new int[v2.size()];

	for (int i = 0; i < arr1.length; i++) {
            arr1[i] = v1.get(i);
	}
	for (int i = 0; i < arr2.length; i++) {
            arr2[i] = v2.get(i);
	}
        // Call the naturalMerge method
	return naturalMerge(arr1, arr2);
    }

    // Method used to merge 2 sub-lists into one main list 
    public static int[] naturalMerge(int[] list1, int[] list2) {
        int current1 = 0, current2 = 0, current3 = 0, previousNum = 0;
        int[] mainList = new int[list1.length + list2.length];
      
        // While current values in each list are shorter than the list length,
        // perform comparision and write smaller value to the main list
        while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] <= list2[current2]) {
                if (list1[current1] < previousNum) {
                    if (list2[current2] >= previousNum) {   // Check if value in other list is still in order
                        previousNum = list2[current2];
                        mainList[current3++] = list2[current2++];    // Write to main list
                        continue;
                    }
                    else {
                        previousNum = -1;   // Set previousNumber to a negative value and start over
                        continue;
                    }
                }
                else {
                    previousNum = list1[current1];
                    mainList[current3++] = list1[current1++];    // Write to main list
                    continue;
                } 
            }
            if (list2[current2] <= list1[current1]) {
                if (list2[current2] < previousNum) {
                    if (list1[current1] >= previousNum) {    // Check if value in other list is still in order
                        previousNum = list1[current1];
                        mainList[current3++] = list1[current1++];    // Write to main list
                    }
                    else {
                        previousNum = -1;    // Set previousNumber to a negative value and start over
                        continue;
                    }
                }
                else {
                    previousNum = list2[current2];
                    mainList[current3++] = list2[current2++];    // Write to main list
                    continue;
                }
            }
        }
      	// If current value in either list has reached list length, write remaining values to merged list 
        while (current1 < list1.length) {
            mainList[current3++] = list1[current1++];
        }
        while (current2 < list2.length) {
            mainList[current3++] = list2[current2++];
        }
        // Call repeatNaturalMerge method which checks if main list is completely sorted
        return repeatNaturalMerge(mainList);    
    }

    // Method used to check if provided list is sorted. If not, repeatedly calls appropriate methods until list is sorted
    public static int[] repeatNaturalMerge(int[] fileData) {
        boolean inOrder = true;
	int currentNum = 0;
	int previousNum = currentNum;

	// Check if list is sorted
	for (int i = 0; i < fileData.length; i++) {
            currentNum = fileData[i];
            if (currentNum >= previousNum) {
		previousNum = currentNum;
            }
            else {
            	inOrder = false;
		break;
            }
	}

        // If list is sorted, return the sorted data to main method
	if (inOrder) {
            return fileData;
	}
        // If list not sorted, call naturalSplit method which subsequently calls naturalMerge and repeatNaturalMerge methods
	else {
            return naturalSplit(fileData);  
	}
    }

    // This function merges 2 lists that are already sorted
    public static int[] merge(int[] list1, int[] list2, int[] mergedList) {
        int current1 = 0, current2 = 0, current3 = 0;
        // While current values in each list are shorter than the list length,
        // perform comparison and write smaller value to the merged list
	while (current1 < list1.length && current2 < list2.length) {
            if (list1[current1] <= list2[current2]) {
		mergedList[current3++] = list1[current1++];
            }
            else {
		mergedList[current3++] = list2[current2++];
            }
	}
	// If current value in either list has reached list length, write remaining values to merged list 
	while (current1 < list1.length) {
	    mergedList[current3++] = list1[current1++];
	}
	while (current2 < list2.length) {
	    mergedList[current3++] = list2[current2++];
	}
        // Return the merged list
	return mergedList;
    }
    
    // Improved display array function that actually displays the data
    public static void displayArray(int[] theArray, int start, int stop) {
        for (int i = start; i < stop; i++) {
            if (i%10 == 0) {
                System.out.println();
            }
            System.out.printf("%7d", theArray[i]);
        }
        System.out.println();
        System.out.println();
    }

    // Display function invoked by user, checks the array length and calls the actual display array function as needed
    public static void displayArray(int[] theArray) {
        if (theArray.length <= 200) {
            displayArray(theArray, 0, theArray.length);
        }
        else {
            displayArray(theArray, 0, 100);
            displayArray(theArray, theArray.length-100, theArray.length);
        }
    }
}