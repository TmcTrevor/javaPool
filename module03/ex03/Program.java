package module03.ex03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class Program {

	private static ConcurrentHashMap<String, Integer> links;



	public static void readAndStore()
	{
		File urls = new File("./files_urls.txt");

		if (!urls.exists() || !urls.canRead())
		{
			System.err.println("File Error");
			System.exit(0);
		}
		links = new ConcurrentHashMap<>();
		int i = 0;
        try (Scanner scanner = new Scanner(urls)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
				links.put(line, ++i);
                System.out.println("ggggg " + i +  " test " + links.get(line));
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
			String[] input = args[0].split("=");
			if (!input[0].equals("--threadsCount")) {
				System.err.println("Error: Wrong flag");
				System.exit(-1);
			}
			int threadsSize = Integer.parseInt(input[1]);
			readAndStore();
			// FileDownloaderThread  t = new FileDownloaderThread(threadsSize, links);
			// t.downloadFile("https://i.pinimg.com/originals/11/19/2e/11192eba63f6f3aa591d3263fdb66bd5.jpg", "./", threadsSize);
			FileDownloaderThread[] threads = new FileDownloaderThread[threadsSize];
			for (int i = 0; i < threads.length;i++)
				threads[i] = new FileDownloaderThread(i + 1,links);
			for (FileDownloaderThread t : threads)
				t.start();
			for (FileDownloaderThread t : threads)
				t.join();


		}catch (Exception e)
		{
			System.err.println("Error: Wrong flag");
			System.exit(-1);
		}

		// String fileURL = "https://cdn.intra.42.fr/pdf/pdf/95348/en.subject.pdf";


	}
}
