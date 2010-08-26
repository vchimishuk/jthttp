import java.io.BufferedReader;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

/**
 * General server response representation.
 * 
 */
public abstract class Page {
	protected HashMap<String, String> headers = new HashMap<String, String>();

	protected Resource resource;
	
	private Page() {
		throw new RuntimeException();
	}
	
	public Page(Resource resource) {
		this.resource = resource;
		
        headers.put("Server", Configurations.getInstance().getString("version"));
        headers.put("Date", new SimpleDateFormat("EEE, dd MMM yyyy H:m:s Z").format(new Date()));
        headers.put("Connection", "close");
	}
	
	public abstract BufferedReader getReader();
	
	public HashMap<String, String> getHeaders() {
		return this.headers;
	}
	
	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
}
