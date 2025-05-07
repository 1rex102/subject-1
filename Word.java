//Colton Dean
//this for my 3.4 assighnment
//this for my software programming class
import java.util.Scanner;

public class Word {
    public static void main(String[] args) {
        Boolean debug=false;
        // Hardcoded string
        String testString = "the quick Brown Fox Jumped over the lazy dog.";

        // Split the hardcoded string into words
        String[] words = testString.split("\\s+"); // Split by spaces, including multiple spaces
        System.out.println("Hardcoded String Words:");
        int wordCount = 0;
        for (String word : words) {
            word = word.replaceAll("[.,!?;:]", ""); // Remove punctuation
            System.out.println(word);
            wordCount++;
            if (debug){
                System.out.println("wordCount is "+wordCount);

            }
        }
        System.out.println("Total words: " + wordCount);

        // Get user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a line of text: ");
        String userInput = scanner.nextLine();
        if (debug) {
            System.out.println("imput is "+userInput);
        }
        // Split the user input into words
        String[] userWords = userInput.split("\\s+"); // Split by spaces, including multiple spaces
        System.out.println("\nUser Input Words:");
        wordCount = 0;
        for (String word : userWords) {
            word = word.replaceAll("[.,!?;:]", ""); // Remove punctuation
            System.out.println(word);
            wordCount++;
        }
        System.out.println("Total words: " + wordCount);
    }
}
    


