package fr._42.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
public class Printer {


	public void printImage(String char1, String char2, String path)
	{
		try {
		File file = new File(path);
		BufferedImage image = ImageIO.read(file);
		for (int i = 0; i < image.getHeight();i++)
		{
			for (int j = 0; j < image.getWidth();  j++)
			{

				if (image.getRGB(j, i) == Color.white.getRGB())
				{
					System.out.print(char1);
				}
				if (image.getRGB(j, i) == Color.black.getRGB())
				{
					System.out.print(char2);
				}

			}
			System.out.println("");
		}
	} catch (Exception e)
	{
		System.err.println("Error in image print" + e.getMessage());
	}

	}
}
