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
		int i = 2;
        if (number < i)
			return false;
        int sqrtNumber = (int)Math.sqrt(number); 
        for (int  i = 2; i <= sqrtNumber; i++) {
            if (number % i == 0) {
                // System.out.println("false " + (i - 1));
                return false;
            }
        }
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int count = 0;
		while (sc.hasNext()) {
			int number = sc.nextInt();
			number = sumOfDigits(number);
			if (isPrime(number))
				count++;
		}
		System.out.format("Count of coffee-request : %d\n", count);
	}
}