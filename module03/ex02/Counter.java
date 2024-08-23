package module03.ex02;

public class Counter {
    private static int counter;

    public Counter() {
        counter = 0;
    }

    public void add(int newNumber)
    {
        counter += newNumber;
    }

    public int getCounter()
    {
        return counter;
    }
}
