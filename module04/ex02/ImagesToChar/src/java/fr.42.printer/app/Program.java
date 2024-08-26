package fr._42.printer.app;

import fr._42.printer.logic.Printer;
import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;


@Parameters(separators = "=")
public class Program {

	@Parameter(names={"--white", "-w"}, description = "color to switch with white", required = true)
	private static String color1;

	@Parameter(names={"--black", "-b"}, description = "color to switch with black", required=true)
	private static String color2;

  	@Parameter(names = {"--image", "-i"}, description = "Path to the image", required=true)
    private static String imagePath;

	public static void main(String[] args) {
		Program program = new Program();
		JCommander jCommander = new JCommander(program);
		try {




		// for (int i = 0; i < args.length; i++) {
        //     if (args[i].contains("=")) {
        //         String[] splitArg = args[i].split("=", 2);
        //         args[i] = splitArg[0];  // key
        //         args = insertArg(args, i + 1, splitArg[1]);  // value
        //     }
        // }
	  	jCommander.parse(args);
		Printer printer = new Printer();
		printer.printImage(color1, color2, imagePath);
	} catch(Exception e)
	{
		System.err.println("error :" + e.getMessage());
		jCommander.usage();
	}
	}
	private static String[] insertArg(String[] args, int index, String value) {
        String[] newArgs = new String[args.length + 1];
        System.arraycopy(args, 0, newArgs, 0, index);
        newArgs[index] = value;
        System.arraycopy(args, index, newArgs, index + 1, args.length - index);
        return newArgs;
    }
}
