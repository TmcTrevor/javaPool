package fr._42.printer.app;

import fr._42.printer.logic.Printer;

public class Program {
	public static void main(String[] args) {
		if (args.length != 3 || args[0].length() != 1 && args[1].length() != 1)
		{
			System.err.println("Error: Wrong flags");
			System.exit(-1);
		}
		Printer printer = new Printer();
		printer.printImage(args[0], args[1], args[2]);
	}
}
