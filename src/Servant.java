import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StringReader;
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
	
	private String docRoot;

	
	public Servant(Socket socket, String docRoot) {
		this.socket = socket;
		this.docRoot = docRoot;
		
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
		File file = new File(docRoot + request.getUri());	// TODO: This is not secure, we can move up from the docRoot with '..' URI segments. 
		
		if (file.isDirectory()) {
			response.setReader(getDirectoryListing(file));
		} else {
			throw new RuntimeException("Retriving file content is not supported.");
		}
		
		sendResponce(response, writer);
	}
	
	private BufferedReader getDirectoryListing(File dir) {
        String s = "This is the internal StringReader buffer.";

        return new BufferedReader(new StringReader(s));
	}
	
	/**
	 * Write server's responce to the given output object.
	 * 
	 * @param responce
	 * @param writer
	 */
	private void sendResponce(ServerResponse response, BufferedWriter writer) {
		try {
			/*
			 * Write some general headers.
			 */
			writer.write("HTTP/1.1 200 OK\r\n");
	        writer.write("Server: Jthttp v0.0 \r\n");	// TODO: Make it configurable.
	        writer.write("Content-Type: text/html\r\n");
	        writer.write("Connection: close\r\n");
	
	        /*
	         * Write request depends headers.
	         */
	        for (String key : response.getHeaders().keySet()) {
	        	writer.write(key);
	        	writer.write(": ");
	        	writer.write(response.getHeaders().get(key));
	        	writer.write("\r\n");
	        }
	        
	        // Headers end marker.
	    	writer.write("\r\n");
	    	
	    	/*
	    	 * Copy data from the reader to the writer, -- send a request body.
	    	 */
	    	char[] buff = new char[512];
	    	int readChars;
	    	for (; ; ) {
	    		readChars = response.getReader().read(buff, 0, 512);
	    		if (readChars <= 0) {
	    			break;
	    		}
	    		
	    		writer.write(buff, 0, readChars);
	    	}
	    	
	    	response.getReader().close();
		} catch (IOException e) {
			// TODO: Handle and log it.
		}
	}
}
