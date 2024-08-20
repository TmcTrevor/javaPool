package module03.ex01;

// public class Egg extends Thread {

// 	private final int count;

// 	public Egg(int count) {
// 		this.count = count;
// 	}

// 	@Override
// 	public void run() {
// 		for (int i = 0; i < count; i++) {
// 			System.out.println("Egg");
// 			try {
// 				Thread.sleep(2);
// 			} catch (InterruptedException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}
// 		}
// 	}

// }


////////////// APROCH 2 FAILED SUCCESSFULLY

// public class Egg extends Thread {

// 	private final int count;
// 	private SharedData queue;

// 	public Egg(SharedData queue, int count) {
// 		this.queue = queue;
// 		this.count = count;
// 	}

// 	@Override
// 	public void run() {
// 		for (int i = 0; i < count; i++) {
// 			// System.out.println("Egg");
// 			try {
// 				queue.produce("Egg");
// 				System.out.println(queue.consume());
// 			} catch (InterruptedException e) {
// 				// TODO Auto-generated catch block
// 				e.printStackTrace();
// 			}
// 		}
// 	}

// }

////////////// APROCH 3 worked SUCCESSFULLY
public class Egg extends Thread {

	private final int count;
	private SharedData queue;

	public Egg(SharedData queue, int count) {
		this.queue = queue;
		this.count = count;
	}

	@Override
	public void run() {
		for (int i = 0; i < count; i++) {
			// System.out.println("Egg");
			try {
				synchronized(queue)
				{
				queue.produce("Egg");
			
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
