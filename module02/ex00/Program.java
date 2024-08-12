package module02.ex00;
import java.io.FileInputStream;

import java.util.Scanner;

public class Program {
	public static void main(String args[])
	{
		FileProcessor file = new FileProcessor();
		Scanner scanner = new Scanner(System.in);


		while (true)
		{
			String path = scanner.nextLine();
			if (path.equals("42"))
				break ;
			FileInputStream fileStream = file.init(path);
			if (fileStream != null)
			// file.readFullFile(fileStream);
			// file.readNbBytes(fileStream, 8);
			file.getFormat(fileStream);
		}
	}
}
