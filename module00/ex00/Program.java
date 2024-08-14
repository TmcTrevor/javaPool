class Program {
    public static void main(String[] args) {

        int number = 123456;
        int numberDigit = 0;
        // if (args != null)
        //     if (args[0] != null)
        //     number = Integer.parseInt(args[0]);

        // while ( number != 0) {
            numberDigit += number % 10;
            number /= 10;
			numberDigit += number % 10;
            number /= 10;
			numberDigit += number % 10;
            number /= 10;
			numberDigit += number % 10;
            number /= 10;
			numberDigit += number % 10;
            number /= 10;
			numberDigit += number % 10;
            // number /= 10;
        // }
        System.out.println(numberDigit);
    }
}
