package fr._42.numbers;

//import static org.junit.jupiter.api.Assertions.assertEquals;


import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class NumberWorkerTest {



    @ParameterizedTest
    @ValueSource(ints = {2,3,113,73}) // six numbers
    @DisplayName("True prime numbers check")
    void isPrimeForPrimes(int number)
    {
        NumberWorker numberWorker = new NumberWorker();
        boolean check = numberWorker.isPrime(number);
//        System.out.println(number + " value " + check);
        assertTrue(check);
    }

    @ParameterizedTest
    @ValueSource(ints = {20, 4,130,36}) // six numbers
    @DisplayName("Non prime numbers check")
    void isPrimeForNotPrimes(int number)
    {
        NumberWorker numberWorker = new NumberWorker();
        boolean check = numberWorker.isPrime(number);
        System.out.println(number + " value " + check);
        assertFalse(check);
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, -200, 0 ,1}) // six numbers
    @DisplayName("Exception  numbers check")
    void isPrimeForIncorrectNumbers(int number)
    {
        NumberWorker numberWorker = new NumberWorker();
        assertThrows(IllegalArgumentException.class, () -> {
            numberWorker.isPrime(number);
        }, "The number must be greater than 1");
    }


    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv", numLinesToSkip = 1)
    @DisplayName("Sum of digits check")
    void sumDigitCheck(int number, int sum)
    {
        NumberWorker numberWorker = new NumberWorker();
        int mySum = numberWorker.digitsSum(number);
        assertEquals(mySum, sum);
    }
}