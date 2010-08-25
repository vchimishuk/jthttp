import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

/**
 * Represents client's request data.
 */
public class ClientRequest {
	private Logger logger = Logger.getLogger(ClientRequest.class);
	
	private HttpMethod method;
	
	private String uri;
	
	private String protocolVersion;
	
	private HashMap<String, String> headers = new HashMap<String, String>();
	
	public ClientRequest() {
		
	}
	
	/**
	 * Create request object based on raw client's request data.
	 * 
	 * @param request
	 */
	public ClientRequest(List<String> requestData) throws BadRequestException {
		try {
			if (requestData.size() < 1) {
				throw new BadRequestException("Request is to short.");
			}
			
			String line;
			
			/*
			 * Parse first line. Format: Method URI HTTP/Version
			 */
			line = requestData.remove(0);
			
			Pattern pattern = Pattern.compile("^(\\w+) (.+) HTTP/([\\d\\.]+)$");
			Matcher matcher = pattern.matcher(line);
			
			if (!matcher.matches()) {
				throw new BadRequestException();
			}
			
			String m = matcher.group(1).toUpperCase(); // Parsed method.
			if (m.equals("GET")) {
				method = HttpMethod.GET;
				uri = matcher.group(2);
				protocolVersion = matcher.group(3);
			} else {
				throw new BadRequestException(String.format("Unsupported HTTP method '%s'",
						matcher.group(1)));
			}
			
			/*
			 * Parse rest of headers.
			 */
			for (String l: requestData) {
				pattern = Pattern.compile("^(\\S+):\\s+(.+)$");
				matcher = pattern.matcher(l);
				
				if (!matcher.matches()) {
					logger.debug("Illegal request header line: " + l);
				} else {
					headers.put(matcher.group(1), matcher.group(2));
				}
			}
			
		} catch (IllegalArgumentException e) {
			throw new BadRequestException("Bad client request format. Request data: " + requestData.toString()
					+ " " + e.getMessage());
		}
	}

	public HttpMethod getMethod() {
		return method;
	}

	public void setMethod(HttpMethod method) {
		this.method = method;
	}

	public HashMap<String, String> getHeaders() {
		return headers;
	}

	public void setHeaders(HashMap<String, String> headers) {
		this.headers = headers;
	}
	
	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getProtocolVersion() {
		return protocolVersion;
	}

	public void setProtocolVersion(String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	@Override
	public String toString() {
		return "ClientRequest [headers=" + headers + ", method=" + method
				+ ", protocolVersion=" + protocolVersion + ", uri=" + uri + "]";
	}
}
