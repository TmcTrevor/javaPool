import java.util.Scanner;

class Program {

    private static int isPrime(int number) {
        int sqrtNumber = (int)Math.sqrt(number);

        for (int  i = 2; i <= sqrtNumber; i++) {
            if (number % i == 0) {
                System.out.println("false " + (i - 1));
                return 0;
            }
        }
       
        System.out.println("true " + sqrtNumber);
        return 1;
    }
    public static void main(String[] args) {
           Scanner scanner = new Scanner(System.in);
        try {
             
            while (true)
            {
                 System.out.print("-> ");
                if (!scanner.hasNextInt())
   throw new Exception("IllegalArgument");
            int number = scanner.nextInt();
            if (number <= 1)
               throw new Exception("IllegalArgument");
            isPrime(number);
            }
        } catch (Exception e) {
            System.err
                    .println(e.getMessage());
        }
    }
}