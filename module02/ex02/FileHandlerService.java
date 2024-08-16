package module02.ex02;

import java.io.File;
import java.util.Scanner;

public class FileHandlerService {

	private File workDir;
	private String initialPath;
	private String currentPath;

	public FileHandlerService(String path)
	{
		workDir = new File(path);
		this.initialPath = path;
		this.currentPath = path;
		System.out.println(workDir.getAbsolutePath());

	}


	public void readCommand()
	{
		Scanner scanner = new Scanner(System.in);
		while (true)
		{
			String command = scanner.nextLine();
			String[] commandWithArgs = command.split(" ");
			switch(commandWithArgs[0])
			{
				case "ls" -> ft_ls();
				case "cd" -> ft_cd(commandWithArgs[1]);
				case "pwd" -> ft_pwd();
				case "mv" -> ft_mv(commandWithArgs);
				case "exit" -> System.exit(0);
				default ->
					System.err.println(commandWithArgs[0] + ": command not found");

			}
		}
	}

	public String ft_pwd()
	{
		System.out.println(currentPath);
		return currentPath;
	}

    public void ft_ls() {
        // Import the File class


		String[] test = workDir.list();
		for (String s : test)
		{
			// System.err.println(currentPath + "/" + s);
			File tmp = new File(currentPath + "/" + s);

			System.out.println(s + " " + tmp.length() + " KB");
		}

    }

	public void ft_cd(String folderName)
	{
		File newFile = new File(currentPath + "/" + folderName);
		if (newFile.exists() == false)
		{
			System.err.println("cd: no such file or directory: " + folderName);
		}
		else
		{
			this.currentPath = currentPath + "/" + folderName;
			workDir = newFile;
		}

	}

	public void ft_mv(String[] command)
	{

		File target = new File(currentPath + "/" + command[1]);
		File newFile = new File(currentPath + "/" + command[2]);
		if (target.exists() == false)
		{
			System.err.println("cd: no such file or directory: " + command[1]);
		}
		if (newFile.exists())
		{
			System.err.println("mv : file already exists");
		}
		try {
			target.renameTo(newFile);
		} catch (Exception e)
		{
			System.err.println("mv : "+ e.getMessage());
		}

	}

}
