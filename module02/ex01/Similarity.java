package module02.ex01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

public class Similarity {

    // private BufferedReader file1;
    // private BufferedReader file2;
    // private BufferedWriter dictionaryFile;
    // private String data1;
    // private String data2;
    private String[] data1Splited;
    private String[] data2Splited;
    // private Set<String> dict;
    private String[] dict;
    private Vector<Integer> vector1;
    private Vector<Integer> vector2;

    private int countWordOccurence(String[] data, String word) {
        int count = 0;
        for (String d : data) {
            if (d.equals(word)) {
                count++;
            }
        }
        return count;
    }

    private void mergeAndMakedict() {
       Set<String> dict1 = new HashSet<>(Arrays.asList(data1Splited));
        Set<String> tmp = new HashSet<>(Arrays.asList(data2Splited));
        dict1.addAll(tmp);
		dict = dict1.toArray(String[]::new);
    }

    private String[] fillSplits(String[] original, String[] newChunks) {
        String[] tmp = new String[original.length + newChunks.length];
        System.arraycopy(original, 0, tmp, 0, original.length);
        System.arraycopy(newChunks, 0, tmp, original.length, newChunks.length);
        return tmp;
    }

    public  String[] readFilesAndStore(String path) {
        String[] data = new String[0];
		String data1;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path))) {
            while ((data1 = bufferedReader.readLine()) != null) {
                if (data1 != null) {
                    data = fillSplits(data, data1.split(" "));
					// System.err.println("data = " + data);
                }
            }
			bufferedReader.close();
            return data;
        } catch (IOException e) {
			System.err.println("Error while reading File with Path " + path + " Details : "+e.getMessage());
            return null;
        }
    }

	public Vector<Integer> fillVector(String[] data)
	{
		Vector<Integer> vector = new Vector<>();
		for (String word : dict)
		{
			vector.add(countWordOccurence(data, word));
		}
		return vector;
	}


	public double calculateDenominator()
	{
		double res = 0;
		double res2 = 0;
		for (int i = 0; i < vector1.size(); i++)
			res += Math.pow(vector1.get(i), 2);

		for (int i = 0; i < vector2.size(); i++)
			res2 += Math.pow(vector2.get(i), 2);
		res = Math.sqrt(res);
		res2 = Math.sqrt(res2);

		return res * res2;
	}

	public double calculateNumerator()
	{
		double res = 0;
		for (int i = 0; i < vector1.size(); i++)
			res += vector1.get(i) *  vector2.get(i);
		return res;
	}

	public double calculateSimilarity()
	{
		double denominator = calculateDenominator();
		double nominator = calculateNumerator();
		if (denominator == 0)
			return 0;

		return nominator / denominator;
	}


	public void writeInFile()
	{
		 try (
             BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("dictionary.txt"))) {
            for (String word : dict) {
                bufferedWriter.write(word + " ");
            }
            // System.out.println("File copied successfully using BufferedReader and BufferedWriter.");
        } catch (IOException e) {

        }
	}


	public void mainFunction(String path1, String path2)
	{
		data1Splited = readFilesAndStore(path1);
        data2Splited = readFilesAndStore(path2);

        mergeAndMakedict();
		Arrays.sort(dict, String.CASE_INSENSITIVE_ORDER);
		writeInFile();

		//  for (String d1 : data2Splited) {
        //     System.out.println(d1);/// write in file dict

        // }
		// System.out.println("-----------");
		vector1 = fillVector(data1Splited);
		vector2 = fillVector(data2Splited);
		// for (int d1 : vector1)
        //     System.out.print(d1 + " ");/// write in file dict

		// System.out.println("");
		// for (int d2 : vector2) {
        //     System.out.print(d2 + " ");/// write in file dict
		// }
		// System.out.println("");

		double res = calculateSimilarity();
		double formattedNumber = (int)(res * 100) / 100.0;

		System.out.println("similarity = " + formattedNumber);

	}

	public Similarity()
	{
		data1Splited = new String[0];
		data2Splited = new String[0];
	}

}
