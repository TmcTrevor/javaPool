package fr._42.numbers;

public class NumberWorker {


    public  boolean isPrime(int number) {
        if (number <= 1)
            throw new IllegalArgumentException("The number must be greater than 1");
        if (number == 2)
            return true;
        if (number % 2 == 0)
            return false;
        for (int i = 3; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

//    public boolean isPrime(int number) {
//
//        if (number <= 1)
//            throw new IllegalArgumentException("The number must be greater than 1");
//        for (int i = 3; i <= Math.sqrt(number); i += 2) {
//            if (number % i == 0) {
//                return false;
//            }
//        }
//        return true;
//    }

    public int digitsSum(int number) {
        int sum = 0;
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }


}