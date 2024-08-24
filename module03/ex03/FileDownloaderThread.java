package module03.ex03;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class FileDownloaderThread extends Thread {
	// private String url;
	private  HashMap<String, Integer> links;
	private int id;
	// private boolean  check;

	public FileDownloaderThread(int id, HashMap<String, Integer> links)
	{
		this.links = links;
		this.id = id;
	}

	public  void downloadFile(String fileURL, String saveDir, int i) throws IOException {
		System.out.println("Thread-" + id +" start download file number "+i);
		links.replace(fileURL, 0);
        URI uri = URI.create(fileURL);
        URL url = uri.toURL();

        Path targetPath = Paths.get(saveDir).resolve(Paths.get(url.getPath()).getFileName().toString());
        Files.copy(url.openStream(), targetPath);
		System.out.println("Thread-" + id +" finished download file number "+i);
    }

	@Override
	public void run()
	{
		// while (true) {
			for (Map.Entry<String, Integer> entry : links.entrySet()) {
			if (entry.getValue() != 0)
			{
				synchronized (entry) {
					try {
						downloadFile(entry.getKey(), "./Downloads", entry.getValue());
					} catch (IOException e) {
						System.err.println(entry.getKey() + " is not downloadable File Error");
					}
				}
			}
		// }
    		// System.out.println(entry.getKey() + "/" + entry.getValue());
}
	}
}
