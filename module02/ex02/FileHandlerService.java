package module02.ex02;

import java.io.File;
import java.util.Scanner;

public class FileHandlerService {

	private File workDir;
	// private String initialPath;
	private String currentPath;

	public FileHandlerService(String path)
	{
		workDir = new File(path);
		// this.initialPath = path;
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
				case "cd" -> {
					if (commandWithArgs.length == 2)
						ft_cd(commandWithArgs[1]);
					else
						System.err.println("cd : command need one argument");
				}
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
		// System.out.println(currentPath);
		System.out.println(workDir.getAbsolutePath());
		return currentPath;
	}

    public void ft_ls() {
        // Import the File class


		try {
			String[] test = workDir.list();

		for (String s : test)
		{
			// System.err.println(currentPath + "/" + s);
			File tmp = new File(currentPath + "/" + s);

			System.out.println(s + " " + tmp.length() + " KB");
		}

	} catch (Exception e)
	{
		System.err.println("ls : "+ e.getMessage());
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
			if (!newFile.isDirectory())
				System.err.println("cd: not a directory " + folderName);
			else if (newFile.canExecute())
			{
				this.currentPath = currentPath + "/" + folderName;
				workDir = newFile;
			}
			else
			System.err.println("cd: permision Denied: " + folderName);
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
