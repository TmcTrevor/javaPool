package module03.ex02;

public class IntegerThreadSum extends Thread {
    

    private int[] array;
    private Counter C;
    private int id;
    private int start;
    private int end;
    private int res;

    public IntegerThreadSum(Counter c, int id,int[] array, int start, int end)
    {
        this.array = array;
        this.C = c;
        this.id = id;
        res = 0;
        this.start = start;
        this.end = end;
    }

    @Override
    public void run()
    {
        for (int i  = start; i <  end ; i++)
        {
            res += array[i];
        }
        synchronized(C)
        {
            C.add(res);
        }
        System.out.println("Thread " + id + ": from " + start + " to " + end + " sum is " + res);
    }

    public int getRes()
    {
        return res;
    }
}
