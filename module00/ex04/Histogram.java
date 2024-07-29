public class Histogram {
    public static void main(String[] args) {
        // Character frequencies
        int[] frequencies = {36, 35, 27, 14, 12, 9, 7, 4, 2, 2};
        String[] characters = {"D", "A", "C", "B", "F", "E", "G", "R", "T", "W"};

        // Maximum number of # symbols to display
        int maxSymbols = 10;

        // Maximum frequency in the data
        int maxFrequency = 0;
        for (int freq : frequencies) {
            if (freq > maxFrequency) {
                maxFrequency = freq;
            }
        }

    
        double scale = (double) maxSymbols / maxFrequency;

    
        int[] scaledFrequencies = new int[frequencies.length];
        for (int i = 0; i < frequencies.length; i++) {
            scaledFrequencies[i] = (int) (frequencies[i] * scale);
        }

        // Print the frequencies on top
        for (int freq : frequencies) {
            System.out.printf("%2d ", freq);
        }
        System.out.println();

        // Print the histogram vertically
        for (int level = maxSymbols; level > 0; level--) {
            for (int i = 0; i < scaledFrequencies.length; i++) {
                if (scaledFrequencies[i] >= level) {
                    System.out.print("#  ");
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }

        // Print character labels
        for (String character : characters) {
            System.out.print(character + "  ");
        }
        System.out.println();
    }
}
