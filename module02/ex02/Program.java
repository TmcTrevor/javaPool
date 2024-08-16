package module02.ex02;


public class Program {

	public static void main(String[] args) {
		String path;
		if (args.length == 1)
			path = args[0];
		else
			path = ".";
		FileHandlerService file = new FileHandlerService(path);
		file.readCommand();

	}


}
