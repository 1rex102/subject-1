
    import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Stringtokenizer {

    public static void main(String[] args) {
        
        String filePath = "path/to/your/textfile.txt";

        try {
            String text = new String(Files.readAllBytes(Paths.get(filePath)));
            Map<Character, Integer> frequencies = new HashMap<>();

            //Tokenization and Frequency Calculation
            for (char c : text.toLowerCase().toCharArray()) {
                if (Character.isLetter(c)) {
                    frequencies.put(c, frequencies.getOrDefault(c, 0) + 1);
                }
            }

            
            int totalFrequency = 0;
            for (int count : frequencies.values()) {
                totalFrequency += count;
            }
            double averageFrequency = (double) totalFrequency / frequencies.size(); 

            //Outputting Letter Frequencies and Percentages
            System.out.println("Letter	Frequency	Percentage");
            double totalPercentage = 0;
            for (Map.Entry<Character, Integer> entry : frequencies.entrySet()) {
                char letter = entry.getKey();
                int frequency = entry.getValue();
                double percentage = (double) frequency / totalFrequency * 100;
                System.out.printf("%c		%d		%.2f%s%n", letter, frequency, percentage, "%");
                totalPercentage += percentage;
            }

            System.out.printf("\nTotal		%.2f		%.2f%s%n", averageFrequency, totalPercentage, "%");

        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }
    }
}

