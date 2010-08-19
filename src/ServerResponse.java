import java.io.BufferedReader;
import java.util.HashMap;

/**
 * This class represents server's response (text, status code and so on).
 *
 */
public class ServerResponse {
	private int code;
	
	private HashMap<String, String> headers = new HashMap<String, String>();
	
	private BufferedReader reader;
	
	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public BufferedReader getReader() {
		return reader;
	}

	public void setReader(BufferedReader reader) {
		this.reader = reader;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public String toString() {
		return "ServerResponse [code=" + code + ", headers=" + headers
				+ ", reader=" + reader + "]";
	}
}
