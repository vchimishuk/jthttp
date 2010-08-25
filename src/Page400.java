import java.io.BufferedReader;
import java.io.StringReader;

/**
 * 404
 *
 */
public class Page400 extends Page {
	private String html;
	
	public Page400() {
		this(null);
	}
	
	public Page400(Resource resource) {
		super(resource);
		
		StringBuilder html = new StringBuilder();
		
		html.append("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n");
		html.append("<html>\n");
		html.append("<head>\n");
		html.append("<title>400 Bad Request</title>\n");
		html.append("</head>\n");
		html.append("<body>\n");
		html.append("<h1>Bad Request</h1>\n");
		html.append("<p>Client request is not valid.</p>\n");
		html.append("</body>\n");
		html.append("</html>\n");

		this.html = html.toString();
		
		getHeaders().put("Content-Type", "text/html; charset=UTF-8");
		getHeaders().put("Content-Length", String.format("%d", this.html.length()));
	}

	@Override
	public BufferedReader getReader() {

		return new BufferedReader(new StringReader(html));
	}
}
