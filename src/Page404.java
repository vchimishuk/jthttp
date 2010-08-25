import java.io.BufferedReader;
import java.io.StringReader;

/**
 * 404
 *
 */
public class Page404 extends Page {
	private String html;
	
	public Page404(Resource resource) {
		super(resource);
		
		StringBuilder html = new StringBuilder();
		
		html.append("<!DOCTYPE HTML PUBLIC \"-//IETF//DTD HTML 2.0//EN\">\n");
		html.append("<html>\n");
		html.append("<head>\n");
		html.append("<title>404 Not Found</title>\n");
		html.append("</head>\n");
		html.append("<body>\n");
		html.append("<h1>Not Found</h1>\n");
		html.append("<p>The requested URL " + resource.getUri() + " was not found on this server.</p>\n");
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
