package module03.ex03;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FileDownloaderThread extends Thread {
	private final ConcurrentHashMap<String, Integer> links;
	private final int id;

	public FileDownloaderThread(int id, ConcurrentHashMap<String, Integer> links) {
		this.links = links;
		this.id = id;
	}

	private Object getLockForKey(String key) {
		return key.intern(); // Ensures the lock is unique per string key
	}

	private void downloadFile(String fileURL, String saveDir, int i) throws IOException {
		try {
			System.out.println("Thread-" + id + " start download file number " + i);
			links.replace(fileURL, 0);
			URI uri = URI.create(fileURL);
			URL url = uri.toURL();
			File theDir = new File(saveDir);
if (!theDir.exists()){
    theDir.mkdirs();
}
			Path targetPath = Paths.get(saveDir).resolve(Paths.get(url.getPath()).getFileName().toString());

			Files.copy(url.openStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
			System.out.println("Thread-" + id + " finished download file number " + i);
		} catch (Exception e) {
			System.err.println("Error while downloading file " + fileURL + ": " + e.getMessage());
		}
	}

	@Override
	public void run() {
		while (true) {
			String urlToDownload = null;
			int linkId = 0;

			synchronized (links) {
				for (Map.Entry<String, Integer> entry : links.entrySet()) {
					if (entry.getValue() != 0) {
						urlToDownload = entry.getKey();
						linkId = entry.getValue();
						links.replace(urlToDownload, 0);
						break;
					}
				}
			}

			if (urlToDownload == null) {
				// No more files to download
				break;
			}

			try {
				downloadFile(urlToDownload, "./Downloads", linkId);
			} catch (IOException e) {
				System.err.println("Thread-" + id + " failed to download file: " + urlToDownload);
			}
		}
	}
}
