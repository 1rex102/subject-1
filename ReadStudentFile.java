//Colton Dean
//this is for my software programing class
//this is for 1.3


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ReadStudentFile {
    public static void main(String[] args) {
        // Specify the file name
        String fileName = "student.txt";

        // Use try-with-resources to ensure the BufferedReader is closed automatically
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String firstName = br.readLine(); // Read the first line 
            String lastName = br.readLine();  // Read the second line 
            String gradeLevel = br.readLine(); // Read the third line 
            String schoolName = br.readLine(); // Read the fourth line 

            // Print the student information to the console
            System.out.println("Student Information:");
            System.out.println("First Name: " + firstName);
            System.out.println("Last Name: " + lastName);
            System.out.println("Grade Level: " + gradeLevel);
            System.out.println("School Name: " + schoolName);
        } catch (IOException e) {
            System.err.println("Error reading from file: " + e.getMessage());
        }
    }
}

