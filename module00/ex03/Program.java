import java.util.Scanner;

public class Program {


	public static long customPow(int base, int exponent) {
        if (exponent == 0) {
            return 1;
        }

        long result = 1;
        // int absExponent = Math.abs(exponent);

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        // if (exponent < 0) {
        //     return 1 / result;
        // }

        return result;
    }

	private static void display(long grade, int week) {
		System.out.print("Week " + week + " ");
		while (grade > 0) {
			System.out.print("=");
			grade--;
		}
		System.out.println(">");
	}

	private static long minGrade(Scanner in) {
		long min = 9;
        // System.out.print("-> ");
		for (int i = 0; i < 5; i++) {
			int grade = in.nextInt();
			if (grade < 1 || grade > 9) {
				return -1;
			}
			min = grade < min ? grade : min;
		}
		in.nextLine();
		return min;
    }

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int weekNumber = 0;
        long accumalate = 0;


		try {
			// while ()
			while (weekNumber < 18) {
                //   System.out.print("-> ");
				String week = scanner.nextLine();
				if (week.equals("42")) {
					break;
				}
				if (!week.equals("Week " + (weekNumber + 1))) {
					throw new Exception("IllegalArgument");
				}
				long min = minGrade(scanner);
				if (min == -1) {
					throw new Exception("IllegalArgument");
				}
				accumalate += min * customPow(10, weekNumber);
				
					// accumalate += min * Math.pow(10 , weekNumber);
					// accumalate1 += min * Math.pow(10 , weekNumber - 9);
                weekNumber++;
			}
			// int limit = weekNumber < 9 ? weekNumber : 10;
			// System.out.println("here 1 " + accumalate);
			// System.out.println("here 4 " + accumalate1);
			for (int i = 1; i <= weekNumber; i++) {
				display(accumalate % 10, i);
				accumalate /= 10;
			}
			// for (int i = 10; i <= weekNumber; i++) {
			// 	display(accumalate1 % 10, i);
			// 	accumalate1 /= 10;
			// }

		} catch (Exception e) {
			String err = e.getMessage() != null ? e.getMessage() : "EOF";
			System.err.println(err);
			scanner.close();
			System.exit(-1);
		}

	}
}
