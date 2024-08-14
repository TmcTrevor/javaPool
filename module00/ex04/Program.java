
import java.util.Scanner;

class Program {

    public static int[] counts = new int[65536];
    public static char[] characters = new char[65536];
    public static int[] charCounts = new int[65536];
    public static int uniqueChars = 0;

    public static void counter(String input) {

        char[] newString = input.toCharArray();
        for (int i = 0; i < input.length(); i++) {
            counts[newString[i]]++;
        }

        // System.out.println("Character counts:"); // 11    1 1 1 1 2 3 4 8 9 6 33
        // for (int i = 0; i < counts.length; i++) {
        //     if (counts[i] > 0) {
        //         System.out.println((char) i + ": " + counts[i]);
        //         // notNUllChar[i] = charCounts[i]
        //     }
        // }
    }

    public static void fillArrays() {
        for (int i = 0; i < counts.length; i++) {
            if (counts[i] > 0) {
                characters[uniqueChars] = (char) i;
                charCounts[uniqueChars] = counts[i];
                uniqueChars++;
            }
        }
    }

    public static void sortNumAndChar() {
        for (int i = 0; i < uniqueChars - 1 && i < 10; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < uniqueChars; j++) {
                if (charCounts[j] > charCounts[maxIndex]
                        || (charCounts[j] == charCounts[maxIndex] && characters[j] < characters[maxIndex])) {
                    maxIndex = j;
                }
            }
            // Swap the counts
            int tempCount = charCounts[i];
            charCounts[i] = charCounts[maxIndex];
            charCounts[maxIndex] = tempCount;

            // Swap the characters
            char tempChar = characters[i];
            characters[i] = characters[maxIndex];
            characters[maxIndex] = tempChar;
        }
    }

    public static void displayResult() {
        int[] scaledFrequencies = new int[10];
        double scale = (double) 10 / charCounts[0];

        for (int i = 0; i < 10; i++) {
            scaledFrequencies[i] = (int) (charCounts[i] * scale);
        }

        int checker = 0;

        for (int i = 0; i < 10 && charCounts[i] == charCounts[0];i++)
        {
            System.out.print(charCounts[0] + " ");
            checker++;
        }
        System.out.println("");
        for (int level = 10; level > 0 && (10 -  level) < charCounts.length ; level--) {
            for (int i = 0; i < scaledFrequencies.length; i++) {

                if (scaledFrequencies[i] >= level) {

                    System.out.print("# ");
                } else {
                    while (i < scaledFrequencies.length && scaledFrequencies[i] >= level - 1) {

                        System.out.print(charCounts[checker] + " ");
                        checker++;
                        i++;
                    }
                    break;

                }
            }
            System.out.println();
        }

        for (int i = 0; i < 10; i++) {

            System.out.print(characters[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
			// System.out.print("-> ");
            String input = scanner.nextLine();

            counter(input);

            // Create arrays to hold characters and their counts for sorting
            fillArrays();

            // Sort the characters by their counts using selection sort
            sortNumAndChar();
            if (input.equals(""))
                System.out.println("empty String");
            else
                displayResult();
            scanner.close();
        }
    }
}

// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWKFKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42

// AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSSSSSSSSSSSSSSSSSSSSSSSDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDDWEWWK大大大大FKKDKKDSKAKLSLDKSKALLLLLLLLLLRTRTETWTWWWWWWWWWWOOOOOOO42
 
