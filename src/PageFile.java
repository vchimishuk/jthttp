import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class PageFile extends Page {
	public PageFile(Resource resource) {
		super(resource);
		
		getHeaders().put("Content-Type", resource.getMimeType());
		getHeaders().put("Content-Length", String.format("%d", resource.getSize()));
		// TODO: Add some headers.
	}
	
	@Override
	public BufferedReader getReader() {
		try {
			// TODO: Make it works with binary files.
			return new BufferedReader(new InputStreamReader(new FileInputStream(resource.getPath())));
		} catch (FileNotFoundException e) {
			throw new RuntimeException();
		} catch (IOException e) {
			throw new RuntimeException();
		}
	}

}
