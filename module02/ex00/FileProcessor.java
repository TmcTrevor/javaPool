package module02.ex00;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.HexFormat;
import java.util.Map;

public class FileProcessor {

		private static HashMap<String, String> formatNcode;

		public FileProcessor() {
			try {
				FileInputStream file = new FileInputStream("./signature.txt");
				formatNcode = new HashMap<>();
				byte[] buffer = new byte[10000];
				int bytesRead = file.read(buffer);
				String wholeFIle = "";
				int fullBytesRead = bytesRead;

				// Loop to read the entire file
				while (bytesRead != -1) {
					// Convert the byte array to a String and print it
					wholeFIle += new String(buffer, 0, bytesRead);

					// Read the next chunk of data
					bytesRead = file.read(buffer);
					fullBytesRead += bytesRead;
				}

				String[] dataSplited = wholeFIle.split("\n");
				int i = 0;
				for (String data : dataSplited)
				{
					String[]  tmp = data.split(",");

					if (tmp.length == 2)
						formatNcode.put(tmp[0],tmp[1].replace(" ", ""));
					else
					{
						System.err.println("Signature File has Error on line "+ i);
						file.close();
						System.exit(-1);
					}
					i++;

				}
				// for (Map.Entry<String, String> entry : formatNcode.entrySet())

				// 	System.out.println(" data 0 = "+ entry.getKey() + " data 1 " + entry.getValue());
			} catch (FileNotFoundException e)
			{
				System.err.println("Error No signature file ");
			}
			catch (IOException e)
			{
				System.err.println("Error No signature file ");

			}


		}

		public FileInputStream init(String Path)
		{
			try {
				FileInputStream fileInputStream = new FileInputStream(Path);
			return fileInputStream;
			} catch (FileNotFoundException e)
			{
				System.err.println("Error where opening file" +  e.getMessage());
				return null;
			}
		}


		public String readFullFile(FileInputStream file)
		{
			try {
			int nb = 0;
			// nb = file.read();
			while ((nb = file.read()) != -1)
            System.out.print((char)nb);
			// System.out.println(nb);
			} catch (IOException e)
			{
				System.err.println("Error while reading file" + e.getMessage());
			}
			return "";
		}


		public static <K, V> K getKeyByValue(Map<K, V> map, V value) {
			for (Map.Entry<K, V> entry : map.entrySet()) {
				if (entry.getValue().equals(value)) {
					return entry.getKey();
				}
			}
			return null;  // Return null if the value is not found
		}

		public static String findFOrmat(Map<String,String> map, String value) {
			for (Map.Entry<String, String> entry : map.entrySet()) {
				if (entry.getValue().contains(value) || value.contains(entry.getValue())) {
					return entry.getKey();
				}
			}
			return null;  // Return null if the value is not found
		}



		public String getFormat(FileInputStream file)
		{


			byte[] f8Bytes = readNbBytes1(file, 8);
			String hexFormat = asciiToHex(f8Bytes);
			String format = findFOrmat(formatNcode, hexFormat);
			return format;

		}

		public String readNbBytes(FileInputStream file, int nbBytes)
		{
			try {
			byte[] buffer = new byte[nbBytes];
			int bytesRead = file.read(buffer);
			// System.out.println(new String(buffer, 0, bytesRead));
			String test =  new String(buffer, 0, bytesRead);
			// char[] characters = test.toCharArray();
			// System.out.println(" hhhh " + asciiToHex(test));
			// for (int i = 0; i < characters.length; i++)
			// 	System.out.println("int = " +  characters[i] + " char " + charToHex(characters[i]));
			return test;
			} catch (IOException e)
			{
				System.err.println("Error while reading file" + e.getMessage());
			}
			return "";

		}

		public byte[] readNbBytes1(FileInputStream file, int nbBytes)
		{
			try {
			byte[] buffer = new byte[nbBytes];
			file.read(buffer);
			return buffer;
			} catch (IOException e)
			{
				System.err.println("Error while reading file" + e.getMessage());
			}
			return null;
		}



		public static String asciiToHex(String asciiValue) {

			char[] chars = asciiValue.toCharArray();
			StringBuffer hex = new StringBuffer();

			for (int i = 0; i < chars.length; i++) {
			  hex.append(Integer.toHexString((int) chars[i]));
			}
			return hex.toString();
		  }

		  public static String asciiToHex(byte[] bytes) {

			// char[] chars = asciiValue.toCharArray();
			StringBuilder hex = new StringBuilder();
            for (byte b : bytes) {
                hex.append(String.format("%02X", b));
            }
			return hex.toString();
		  }


		static public String byteToHex(byte b) {
			// Returns hex String representation of byte b
			char hexDigit[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9', 'a', 'b', 'c', 'd', 'e', 'f' };
			char[] array = { hexDigit[(b >> 4) & 0x0f], hexDigit[b & 0x0f] };
			return new String(array);
		}
		public static String charToHex(char c) {
			// Returns hex String representation of char c
			byte hi = (byte) (c >>> 88);
			byte lo = (byte) (c & 0xff);
			return byteToHex(hi) + byteToHex(lo);
		}



}
