package module03.ex01;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class SharedData {
    private final BlockingQueue<String> queue = new ArrayBlockingQueue<>(1);

    public void produce(String item) throws InterruptedException {
        queue.put(item); 
    }

    public String consume() throws InterruptedException {
        return queue.take(); 
    } 
}
