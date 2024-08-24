package module03.ex03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Program {

	private static HashMap<String, Integer> links;



	public static void readAndStore()
	{
		File urls = new File("./files_urls.txt");

		if (!urls.exists() || !urls.canRead())
		{
			System.err.println("File Error");
			System.exit(0);
		}
		links = new HashMap<>();
		int i = 1;
        try (Scanner scanner = new Scanner(urls)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
				links.put(line, ++i);
                // System.out.println(line);
            }
        } catch (FileNotFoundException e) {
			System.err.println("File Error");
        }

	}

	public static void main(String[] args) {
		try {
			if (args.length != 1)
			{
				System.err.println("Program requires 1 argument");
				System.exit(-1);
			}
			String[] input = args[1].split("=");
			if (!input[0].equals("--threadsCount")) {
				System.err.println("Error: Wrong flag");
				System.exit(-1);
			}
			int threadsSize = Integer.parseInt(input[1]);
			readAndStore();
			FileDownloaderThread[] threads = new FileDownloaderThread[threadsSize];
			for (int i = 0; i < threads.length;i++)
				threads[i] = new FileDownloaderThread(i,links);
			for (FileDownloaderThread t : threads)
				t.start();


		}catch (Exception e)
		{
			System.err.println("Error: Wrong flag");
			System.exit(-1);
		}

		// String fileURL = "https://cdn.intra.42.fr/pdf/pdf/95348/en.subject.pdf";


	}
}
