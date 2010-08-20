import java.io.File;

/**
 * Resource (file or directory) representation class. 
 *
 * TODO: Check that URI doesn't points to the resource upper than root.
 * TODO: Encode not URI valid characters.
 */
public class Resource implements Cloneable {
	/**
	 * Root folder for of shared resources by the server.
	 * 
	 */
	private String root = "/"; // TODO: Make it more configurable.
	
	/**
	 * Location of the represented resource in the root.
	 * This location can't represents a file out of the root.
	 * 
	 */
	private String uri = "/";
	
	public Resource() {
		
	}
	
	public Resource(String uri) {
		setUri(uri);
	}

	/**
	 * TODO: Check if URI is correct. Add slash at the beginning if we need.
	 * 
	 * @param uri
	 */
	public void setUri(String uri) {
		if (uri == null) {
			throw new NullPointerException("URI can't be null");
		}
		
		if (uri.length() > 0 && uri.charAt(0) != '/') {
			uri = "/" + uri;
		} else if (uri.length() == 0) {
			uri = "/";
		}
		
		this.uri = uri;
	}
	
	public String getUri() {
		return uri;
	}
	
	/**
	 * Append new segment to the represented URI.
	 * 
	 * @param segment
	 */
	public void addSegment(String segment) {
		if (segment == null) {
			throw new NullPointerException("Segment can't be null.");
		}

		segment = ltrim(segment);
		uri = rtrim(uri);
		
		uri = uri + "/" + segment;
	}
	
	/**
	 * Removes last URI segment and returns it.
	 * 
	 * @return Removed segment value.
	 */
	public String removeLastSegment() {
		String segment = null;
		int i;
		
		uri = rtrim(uri);
		
		i = uri.lastIndexOf('/');
		
		if (i >= 0) {
			segment = uri.substring(i + 1);
			uri = uri.substring(0, i);
		}
		
		if (uri.length() == 0) {
			uri = "/";
		}
		
		return segment;
	}
	
	/**
	 * Remove leading slashes.
	 * 
	 * @param str
	 * @return
	 */
	private String ltrim(String str) {
		if (str == null) {
			throw new NullPointerException();
		}
		
		while (str.length() > 0 && str.charAt(0) == '/') {
			str = str.substring(1);
		}
		
		return str;
	}
	
	/**
	 * Remove slashes at the end of the given string.
	 * 
	 * @param str
	 * @return
	 */
	private String rtrim(String str) {
		if (str == null) {
			throw new NullPointerException();
		}

		for (int i = str.length() - 1; i >= 0 && str.charAt(i) == '/'; i--) {
			str = str.substring(0, i);
		}
		
		return str;
	}
	
	/**
	 * Returns true if the resource pointed to the folder.
	 * 
	 * @return
	 */
	public boolean isDirectory() {
		String foo = rtrim(root) + uri;
		
		return new File(rtrim(root) + uri).isDirectory();
	}
	
	/**
	 * Returns full (from the root of the file system) path to the resource.
	 * 
	 * @return
	 */
	public String getPath() {
		return rtrim(root) + uri;
	}
	
	@Override
	public Resource clone() throws CloneNotSupportedException {
		Resource cloned = (Resource) super.clone();
		cloned.root = root;
		cloned.uri = uri;
		
		return cloned;
	}
}
