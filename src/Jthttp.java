import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Simple test java http server written with educational purpose.
 * 
 * @author Viacheslav Chumushuk
 * @date 18.08.2010
 */
public class Jthttp {
	private Logger logger;
	
	/**
	 * Socket listened by server for incoming connections.
	 * 
	 */
	private ServerSocket socket;

	public Jthttp() {
		BasicConfigurator.configure();
		logger = Logger.getLogger(Jthttp.class);
	}
	
	/**
	 * Start server.
	 */
	public void start() {
		int port = Configurations.getInstance().getInt("port");
		
		try {
			socket = new ServerSocket(port);
		} catch (IOException e) {
			logger.error("Unable to create socket object.", e);
			return;
		}
		
		logger.info(String.format("Server starting to listen %d port.", port));
		
		listen();
	}
	
	/**
	 * Listen the server socket for the incoming connections.
	 * 
	 */
	private void listen() {
		ExecutorService executorService = Executors.newFixedThreadPool(Configurations.getInstance().getInt("threadsCount"));
		
		try {
			for (; ; ) {
				Socket clientSocket = socket.accept();
				
				logger.info(String.format("Client connection handled. From %s:%d",
						clientSocket.getInetAddress().getHostAddress(),
						clientSocket.getPort()));
				
				executorService.execute(new Servant(clientSocket));
			}
		} catch (IOException e) {
			logger.error("Listening failed.", e);
		}
	}
	
	public static void main(String[] args) {
		Jthttp jthttp = new Jthttp();
		
		jthttp.start();
	}
}
