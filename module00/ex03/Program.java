import java.util.Scanner;

public class Program {

	private static void display(int grade, int week) {
		System.out.print("Week " + week + " ");
		while (grade > 0) {
			System.out.print("=");
			grade--;
		}
		System.out.println(">");
	}

	private static int minGrade(Scanner in) {
		int min = 9;
        System.out.print("-> ");
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
        int accumalate = 0;


		try {
			// while ()
			while (weekNumber < 18) {
                  System.out.print("-> ");
				String week = scanner.nextLine();
				if (week.equals("42")) {
                    //  System.out.println("here 4 ");
					break;
				}
				if (!week.equals("Week " + (weekNumber + 1))) {
					throw new Exception("IllegalArgument");
				}
                //   System.out.println("here 1 ");
				int min = minGrade(scanner);
                //  System.out.println("here 44 " +  min);
				if (min == -1) {
					throw new Exception("IllegalArgument");
				}
//  System.out.println("here 2 ");
				accumalate += min * Math.pow(10 , weekNumber) ;
                weekNumber++;
			}
			for (int i = 1; i <= weekNumber; i++) {
				display(accumalate % 10, i);
				accumalate /= 10;
			}
		} catch (Exception e) {
			String err = e.getMessage() != null ? e.getMessage() : "EOF";
			System.err.println(err);
			scanner.close();
			System.exit(-1);
		}

	}
}
