// Daniel Fijalka
// OCCC - Fall 2024
// Advanced Java
// Unit 1 Homework
// Magic Eight Ball

import java.util.Scanner;

public class MagicEightBall {
    
    public static void main(String [] args) {
        String question = "";
        int numOfQuestions = 0;
        Scanner userQuestion = new Scanner(System.in);

        while (true) {
            // Prompt the user for a question
            System.out.print("Ask a question: ");
        
            // Accept that question
            question = userQuestion.nextLine();

            // If not the empty string, display a randomly-selected response
            if (question.length() != 0) {
                System.out.println(shakeBall());
                ++numOfQuestions;
                System.out.println();
                continue;
            }
            // If the empty string, exit
            else {
                System.out.println();
                break;
            }
        }

        // Display the number of questions answered and exit
        System.out.println("Number of questions answered: " + numOfQuestions);
        System.out.println("Till next time, goodbye!");
        System.exit(0);
    }
    
    public static String shakeBall() {
        int randomNumber = (int) (Math.random() * 16);
        
        // Switch case
        switch(randomNumber) {
            // Cases cover values 1-15, default statement is case 0
            case 1:
                return "Let's revisit that question later";
            case 2:
                return "We'll make it happen";
            case 3:
                return "You might have a better response with a fortune cookie";
            case 4:
                return "Reply hazy try again";
            case 5:
                return "Signs point to yes";
            case 6:
                return "My sources say no";
            case 7:
                return "Don't count on it";
            case 8:
                return "Perhaps";
            case 9:
                return "100% yes";              
            case 10:
                return "Outlook not so good";
            case 11:
                return "It is certain";              
            case 12:
                return "Without a doubt";
            case 13:
                return "You're joking, right?";
            case 14:
                return "Ask me this time next year";
            case 15:
                return "Hhhmmm...";
            default:
                return "Better not tell you now"; 
        }
    }
}