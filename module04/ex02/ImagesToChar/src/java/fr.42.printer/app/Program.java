package fr._42.printer.app;

import fr._42.printer.logic.Printer;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.JCommander;

public class Program {

	@Parameter(names={"--white", "-w"}, description = "color to switch with white")
	private String color1;
	@Parameter(names={"--black", "-b"}, description = "color to switch with black")
	private String color2;

  	@Parameter(names = {"--image", "-i"}, description = "Path to the image")
    private String imagePath;

	public static void main(String[] args) {
		Program program = new Program();
        JCommander.newBuilder()
            .addObject(program)
            .build()
            .parse(args);
		System.err.println("1 = " + color1+ "2 "+ color2+ "im = "+ imagePath);
		if (args.length != 3 || args[0].length() != 1 && args[1].length() != 1)
		{
			System.err.println("Error: Wrong flags");
			System.exit(-1);
		}
		Printer printer = new Printer();
		printer.printImage(args[0], args[1], args[2]);
	}
}
