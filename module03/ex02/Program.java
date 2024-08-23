package module03.ex02;
import java.util.*;
public class Program {
    

    public static void main(String[] args) {
        try {
        if (args.length != 2)
        {
            System.err.println("Program requires 2 arguments");
            System.exit(-1);
        }
        String[] input = args[0].split("=");
		if (!input[0].equals("--arraySize")) {
			System.err.println("Error: Wrong flag");
			System.exit(-1);
		}
        String[] input2 = args[1].split("=");
		if (!input2[0].equals("--threadsCount")) {
			System.err.println("Error: Wrong flag");
			System.exit(-1);
		}
        int arraySize = Integer.parseInt(input[1]);
        int threadsSize = Integer.parseInt(input2[1]);
        Random rand = new Random();
        int[] array  = new int[arraySize];
        int res =  0;
        // int threadRes = 0;
        Counter threadRes = new Counter();
        for (int i = 0; i < array.length; i++)
        {
            array[i] = (int) rand.nextInt(1000);
            res+= array[i];
        }

        System.err.println("Sum: " + res);
        int nbElement = Math.ceilDiv(arraySize, threadsSize);
        int start = 0;
        int end = nbElement;
        // 0 10 -> 10 20 / 20
        IntegerThreadSum[]  threads = new IntegerThreadSum[threadsSize];

        for (int i = 0; i < threads.length ; i++)
        {
            threads[i] = new IntegerThreadSum(threadRes, i, array,start , end > arraySize ? arraySize  : end);
            start += nbElement;
            end += nbElement;
        }
        for (int i = 0; i < threads.length ; i++)
        {
            threads[i].start();
        }
        for (int i = 0; i < threads.length ; i++)
        {
            threads[i].join();
        }

        // for (int i = 0; i < threads.length ; i++)
        // {
        //     threadRes += threads[i].getRes();
        // }
        System.out.println("Sum by threads :" + threadRes.getCounter());
	} catch (Exception e) {
        System.err.println(e.getMessage());
        System.exit(-1);
    }
        
    }
}
