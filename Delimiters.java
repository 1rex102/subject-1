
    import java.io.*;
import java.util.*;

public class Delimiters {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Entry> entries = new ArrayList<>();
        String filename = "data.txt";

        for (int i = 1; i <= 5; i++) {
            System.out.println("\nEnter data for entry " + i + ":");
            String name = getUserInput(scanner, "Name: ");
            String address = getUserInput(scanner, "Address: ");
            int age = getIntInput(scanner, "Age: "); // Numerical Input
            String zipCode = getUserInput(scanner, "Zip Code: "); // Zip code as string
            String comment = getUserInput(scanner, "Comment: ");

            Entry entry = new Entry(name, address, age, zipCode, comment);
            entries.add(entry);
        }

        try {
            saveDataToFile(entries, filename);
            System.out.println("\nData saved to " + filename);
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
            return;
        }

        try {
            List<Entry> loadedEntries = readDataFromFile(filename);
            displayData(loadedEntries);
        } catch (IOException e) {
            System.err.println("Error reading data from file: " + e.getMessage());
        }
    }

    private static String getUserInput(Scanner scanner, String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    private static int getIntInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static void saveDataToFile(List<Entry> entries, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Entry entry : entries) {
                writer.println(entry.toCSVString()); // Use toCSVString for saving
            }
        }
    }

    private static List<Entry> readDataFromFile(String filename) throws IOException {
        List<Entry> entries = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(","); // Assuming CSV format
                if (parts.length == 5) {
                    try {
                        String name = parts[0];
                        String address = parts[1];
                        int age = Integer.parseInt(parts[2]);
                        String zipCode = parts[3];
                        String comment = parts[4];
                        entries.add(new Entry(name, address, age, zipCode, comment));
                    } catch (NumberFormatException e) {
                        System.err.println("Skipping invalid line: " + line);
                    }
                } else {
                    System.err.println("Skipping malformed line: " + line);
                }
            }
        }
        return entries;
    }

    private static void displayData(List<Entry> entries) {
        System.out.println("\nData Entries:");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        for (Entry entry : entries) {
            System.out.println(entry.toString());
            System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------------");
        }

        calculateAndDisplayStatistics(entries);
    }
    private static void calculateAndDisplayStatistics(List<Entry> entries) {
        
        double averageAge = entries.stream().mapToInt(Entry::getAge).average().orElse(0.0);
        System.out.printf("Average Age: %.2f\n", averageAge);
    
        Map<String, Integer> zipCodeCounts = new HashMap<>();
        for (Entry entry : entries) {
            String zipCode = entry.getZipCode();
            zipCodeCounts.put(zipCode, zipCodeCounts.getOrDefault(zipCode, 0) + 1);
        }

        String modeZipCode = null;
        int maxCount = 0;
        for (Map.Entry<String, Integer> entry : zipCodeCounts.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                modeZipCode = entry.getKey();
            }
        }

        System.out.println("Most Common Zip Code: " + (modeZipCode != null ? modeZipCode : "No zip codes entered"));
    }

    static class Entry {
        private final String name;
        private final String address;
        private final int age;
        private final String zipCode;
        private final String comment;

        public Entry(String name, String address, int age, String zipCode, String comment) {
            this.name = name;
            this.address = address;
            this.age = age;
            this.zipCode = zipCode;
            this.comment = comment;
        }

        public String getName() {
            return name;
        }

        public String getAddress() {
            return address;
        }

        public int getAge() {
            return age;
        }

        public String getZipCode() {
            return zipCode;
        }

        public String getComment() {
            return comment;
        }

        public String toCSVString() {
            return String.join(",", name, address, String.valueOf(age), zipCode, comment);
        }

        @Override
        public String toString() {
            return String.format("Name: %s\nAddress: %s\nAge: %d\nZip Code: %s\nComment: %s",
                    name, address, age, zipCode, comment);
        }
    }
}

