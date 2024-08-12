package ex05;

public class Program {
    public static void main(String[] args) {
		boolean isDevMode = false;
		// System.err.println("args = "+ args[0]);
		if (args.length == 1 && args[0].equals("--dev"))
		// if (args.length == 1 && args[0].equals("--profile=dev"))
			isDevMode = true;
		Menu menu = new Menu(isDevMode);

		menu.showMenu();
	}
}
