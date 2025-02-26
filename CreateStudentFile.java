



import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CreateStudentFile {

    public static void main(String[] args) {

        // Create a Scanner object to read input from the keyboard
        Scanner input = new Scanner(System.in);

        // Prompt the user to enter their first name
        System.out.print("Enter your first name: ");
        String firstName = input.nextLine();

        // Prompt the user to enter their last name
        System.out.print("Enter your last name: ");
        String lastName = input.nextLine();

        // Prompt the user to enter their grade level
        System.out.print("Enter your grade level (e.g., 9th, Freshman, etc.): ");
        String gradeLevel = input.nextLine();

        // Prompt the user to enter their school name
        System.out.print("Enter your school name: ");
        String schoolName = input.nextLine();

        // Close the scanner to release resources
        input.close();

        try (PrintWriter output = new PrintWriter(new FileOutputStream("student.txt"))) {
            // Write the student's information to the file, one line at a time
            output.println(firstName);
            output.println(lastName);
            output.println(gradeLevel);
            output.println(schoolName);
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }

        System.out.println("Student information saved to student.txt");
    }
}
