public class Debug {
    static boolean debugMode = true; 
    
        public static void main(String[] args) {
            int[] numbers = {5, 2, 8, 1, 9};
            int target = 10;
            if (debugMode) {
                System.out.println("Starting the findPair function...");
            }
            int[] pair = findPair(numbers, target);
            if (debugMode) {
                System.out.println("Finished the findPair function.");
            }
            if (pair != null) {
                System.out.println("Pair found: " + pair[0] + ", " + pair[1]);
            } else {
                System.out.println("No pair found.");
            }
        }
    
        public static int[] findPair(int[] numbers, int target) {
            if (debugMode) {
                System.out.println("Entered the findPair function.");
                System.out.println("Numbers: " + Arrays.toString(numbers));
                System.out.println("Target: " + target);
            }
            for (int i = 0; i < numbers.length - 1; i++) {
                if (debugMode) {
                    System.out.println("Outer loop iteration: " + i);
                }
                for (int j = i + 1; j < numbers.length; j++) {
                    if (debugMode) {
                        System.out.println("Inner loop iteration: " + j);
                        System.out.println("Checking numbers: " + numbers[i] + " and " + numbers[j]);
                    }
                    if (numbers[i] + numbers[j] == target) {
                        if (debugMode) {
                            System.out.println("Pair found! Returning: " + numbers[i] + ", " + numbers[j]);
                        }
                        return new int[]{numbers[i], numbers[j]};
                    }
                }
            }
            if (debugMode) {
                System.out.println("No pair found. Returning null.");
}

        }
    }