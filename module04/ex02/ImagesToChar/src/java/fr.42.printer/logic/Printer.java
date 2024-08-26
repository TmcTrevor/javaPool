package fr._42.printer.logic;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import com.diogonunes.jcdp.color.ColoredPrinter;
import com.diogonunes.jcdp.color.api.Ansi.BColor;
import com.diogonunes.jcdp.color.api.Ansi.FColor;
public class Printer {


	public void printImage(String color1, String color2, String path)
	{
		try {

			// FColor fg = FColor.valueOf(color2.toUpperCase());
			BColor bg = BColor.valueOf(color1.toUpperCase());
			BColor bg2 = BColor.valueOf(color2.toUpperCase());

			// Create a ColoredPrinter instance with the provided colors
			ColoredPrinter cp = new ColoredPrinter.Builder(1, false)
										.background(bg)
										.build();
			ColoredPrinter cp1 = new ColoredPrinter.Builder(1, false)
										.background(bg2)
										.build();
		File file = new File(path);
		BufferedImage image = ImageIO.read(file);
		for (int i = 0; i < image.getHeight();i++)
		{
			for (int j = 0; j < image.getWidth();  j++)
			{

				if (image.getRGB(j, i) == Color.white.getRGB())
				{
					cp.print(" ");
				}
				if (image.getRGB(j, i) == Color.black.getRGB())
				{
					cp1.print(" ");
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
