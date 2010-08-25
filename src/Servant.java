import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;


/**
 * Client connection processing class.
 * 
 */
public class Servant {
	private Logger logger; 
	
	private Socket socket;
	
	public Servant(Socket socket) {
		this.socket = socket;
		
		logger = Logger.getLogger(Servant.class);
	}
	
	public void run() {
		try {
			/*
			 * Read the request.
			 */
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			List<String> requestData = new LinkedList<String>();
			
			for(; ; ) {
				String line = reader.readLine();
				
				if (line == null || line.trim().length() == 0) {
					break;
				}
				
				requestData.add(line);
			}

			/*
			 * Parse request.
			 */
			ClientRequest request = null;
			try {
				request = new ClientRequest(requestData);
				logger.debug("Parsed request: " + request);
			} catch (IllegalArgumentException e) {
				// TODO: Show some error page here or do some other things in deal with RFC.
			}
			
			/*
			 * Process request and send answer to the client.
			 */
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			processRequest(request, writer);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			logger.info("Communication with client failed.", e);
		
			try {
				socket.close();
			} catch (IOException ee) {
				// Leave this unhandled.
			}
		}
	}
	
	/**
	 * Process client's request.
	 * Outputs folder liting or file content to the client socket.
	 * 
	 * @param request
	 */
	private void processRequest(ClientRequest request, BufferedWriter writer) {
		ServerResponse response = new ServerResponse();
		Resource resource = new Resource(request.getUri());
		Page page = PageFactory.getPage(resource);		
		
		sendResponce(page, writer);
	}
	
	private BufferedReader getDirectoryListing(Resource resource) {
        PageListing dirListing = new PageListing(resource);

        return dirListing.getReader();
	}
	
	/**
	 * Write server's responce to the given output object.
	 * 
	 * @param responce
	 * @param writer
	 */
	private void sendResponce(Page page, BufferedWriter writer) {
		try {
			/*
			 * Write some general headers.
			 */
			writer.write("HTTP/1.1 200 OK\r\n");
	
	        /*
	         * Write request depends headers.
	         */
	        for (String key : page.getHeaders().keySet()) {
	        	writer.write(key);
	        	writer.write(": ");
	        	writer.write(page.getHeaders().get(key));
	        	writer.write("\r\n");
	        }
	        
	        // Headers end marker.
	    	writer.write("\r\n");
	    	
	    	/*
	    	 * Copy data from the reader to the writer, -- send a request body.
	    	 */
	    	BufferedReader reader = page.getReader();
	    	char[] buff = new char[512];
	    	int readChars;
	    	for (; ; ) {
	    		readChars = reader.read(buff, 0, 512);
	    		if (readChars <= 0) {
	    			break;
	    		}
	    		
	    		writer.write(buff, 0, readChars);
	    	}
	    	
	    	 reader.close();
		} catch (IOException e) {
			// TODO: Handle and log it.
		}
	}
}
