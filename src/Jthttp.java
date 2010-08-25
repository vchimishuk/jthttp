import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Simple test java http server written with educational purpose.
 * 
 * @author Viacheslav Chumushuk
 * @date 18.08.2010
 */
/*
 * TODO: Add 400 page.
 * TODO: Add 500 page.
 */
public class Jthttp {
	private Logger logger;
	
	/**
	 * Port number listened by server for client's connections.
	 * 
	 */
	private int port;
	
	/**
	 * Maximum number of serving threads working in the same time.
	 * 
	 */
	private int maxThreadsCount;
	
	/**
	 * Socket listened by server for incoming connections.
	 * 
	 */
	private ServerSocket socket;
	/**
	 * Default constructor.
	 * 
	 */
	public Jthttp() {
		this(80, 5);
	}
	
	/**
	 * Port based constructor.
	 * 
	 * @param port Port number for listening.
	 */
	public Jthttp(int port) {
		this(port, 5);
	}
	
	/**
	 * Port number and threads count based constructor.
	 * 
	 * @param port Port number for the listening.
	 * @param maxThreadsCount Maximum number of serving threads.
	 */
	public Jthttp(int port, int maxThreadsCount) {
		this.port = port;
		this.maxThreadsCount = maxThreadsCount;
		
		BasicConfigurator.configure();
		logger = Logger.getLogger(Jthttp.class);
	}
	
	/**
	 * Start server.
	 */
	public void start() {
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
		try {
			for (; ; ) {
				Socket clientSocket = socket.accept();
				
				logger.info(String.format("Client connection handled. From %s:%d",
						clientSocket.getInetAddress().getHostAddress(),
						clientSocket.getPort()));
				
				// TODO: Make it multithreading.
				Servant servant = new Servant(clientSocket);
				servant.run();
				clientSocket.close();
			}
		} catch (IOException e) {
			logger.error("Listening failed.", e);
		}
	}
	
	public static void main(String[] args) {
		Jthttp jthttp = new Jthttp(8080);
		
		jthttp.start();
	}
}
