
import java.util.Scanner;

public class Program {

    static int sumOfDigits(int number) {
        int sum = 0;
        while (number != 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }

    static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        int sqrtNumber = (int) Math.sqrt(number);
        for (int i = 2; i <= sqrtNumber; i++) {
            if (number % i == 0) {
                // System.out.println("false " + (i - 1));
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int count = 0;
        try {
            while (true) {
                System.out.print("-> ");
                if (!scanner.hasNextInt()) {
                    throw new Exception("IllegalArgument");
                }
                int number = scanner.nextInt();
                if (number == 42) {
                    break;
                }
                number = sumOfDigits(number);
                if (isPrime(number)) {
                    count++;
                }
            }
            System.out.format("Count of coffee-request : %d\n", count);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(-1);
            scanner.close();
        }
    }
}
