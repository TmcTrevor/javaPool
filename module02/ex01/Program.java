package module02.ex01;

public class Program {
	public static void main(String[] args) {
		if (args.length != 2)
		{
			System.err.println("Program needs 2 file Path to start");
			System.exit(-1);
		}
		Similarity test = new Similarity();
		test.mainFunction(args[0], args[1]);
	}
}
