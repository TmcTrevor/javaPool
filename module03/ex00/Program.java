package module03.ex00;

public class Program extends Thread {
	public static int count = 0;
	public static void main(String[] args) {
		if (args.length == 1)
		{
		count = Integer.parseInt(args[0].split("=")[1]);
		Egg thread = new Egg(count);
		Hen henObj = new Hen(count);

		Thread thread1 = new Thread(henObj);
		thread1.start();
		thread.start();

		while(thread.isAlive() && thread1.isAlive()) {

		}


	  }

	  @Override
	 public  void run() {
		  for (int i = 0; i < count; i++)
		  System.out.println("HUman");
		}
}



}
