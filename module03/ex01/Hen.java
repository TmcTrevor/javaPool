package module03.ex01;

// public class Hen implements Runnable {


// 	private final int count;

// 	public Hen(int count)
// 	{
// 			this.count = count;
// 	}

//         @Override
// 	public void run() {
// 		for (int i = 0; i < count; i++)
// 		{
// 			System.out.println("Hen");
// 			try {
// 				Thread.sleep(2);
// 			} catch (InterruptedException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}
			
// 	  	}
// 	}

// }

////////////// APROCH 2 FAILED SUCCESSFULLY
// public class Hen implements Runnable {


// 	private final int count;
// 	private SharedData queue;

// 	public Hen(SharedData queue, int count)
// 	{
// 			this.count = count;
// 			this.queue = queue;
// 	}

//         @Override
// 	public void run() {
// 		for (int i = 0; i < count; i++)
// 		{
// 			// System.out.println("Hen");
// 			try {
// 				queue.produce("Hen");
// 				System.out.println(queue.consume());
// 			} catch (InterruptedException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}
			
// 	  	}
// 	}

// }
////////////// APROCH 3 worked SUCCESSFULLY

// The Hen thread enters the synchronized(queue) block, locking the queue object.
// It prints "Hen".
// It calls queue.notify(), which will notify any thread waiting on the queue object (in this case, the Egg thread, assuming it has already reached a wait() call).
// Then, it calls queue.wait(), which causes the Hen thread to release the lock on queue and enter a waiting state.


public class Hen implements Runnable {


	private final int count;
	private SharedData queue;

	public Hen(SharedData queue, int count)
	{
			this.count = count;
			this.queue = queue;
	}

        @Override
	public void run() {
		for (int i = 0; i < count; i++)
		{
			// System.out.println("Hen");
			try {
				synchronized(queue)
				{
					queue.produce("Hen");
					System.out.println(queue.consume());
					queue.notify();
					if (i < count - 1)
						queue.wait();
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	  	}
	}

}