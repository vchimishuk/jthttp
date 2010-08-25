import java.io.BufferedReader;
import java.io.File;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Directory listing page builder class.
 * 
 */
public class PageListing extends Page {
	private class FileComparator implements Comparator<File> {
		@Override
		public int compare(File a, File b) {
			return a.getName().compareToIgnoreCase(b.getName());
		}
	}
	
	private String html;
	
	public PageListing(Resource resource) throws IllegalArgumentException {
		super(resource);
		
		if (resource.getType() != ResourceType.DIRECTORY) {
			throw new IllegalArgumentException("Resource should represents a folder");
		}
		
		html = getHtml();
		
		getHeaders().put("Content-Type", "text/html; charset=UTF-8");
		getHeaders().put("Content-Length", String.format("%d", html.length()));
	}
	
	private String getHtml() {
		StringBuilder html = new StringBuilder();
		Resource parentRes;
		
		try {
			parentRes = resource.clone();
			parentRes.removeLastSegment();
		} catch (CloneNotSupportedException e) {
			throw new RuntimeException(e);
		}
		
		html.append("<!DOCTYPE HTML PUBLIC \"-//W3C//DTD HTML 3.2 Final//EN\">\n");
		html.append("<html>\n");
		html.append("<head>\n");
		html.append("<title>Index of " + resource.getUri() + "</title>\n");
		html.append("</head>\n");
		html.append("<body>\n");
		html.append("<h1>Index of " + resource.getUri() + "</h1>");
		html.append("<pre>\n");
		html.append("Name                    Last modified           Size\n");
		html.append("<hr>");
		html.append("<a href=\"" + parentRes.getUri() + "\">Parent Directory</a>\n");
		
		/*
		 * File listing.
		 * Sorting listing we do in next way:
		 * 1. Split folder's items to two lists: directories and files.
		 * 2. Than sort every independent lists.
		 * 3. Join this two sorted lists into one, directories first.
		 */
		File directory = new File(resource.getPath());
		List<File> direcories = new LinkedList<File>();
		List<File> files = new LinkedList<File>();

		for (File file : directory.listFiles()) {
			if (file.isDirectory()) {
				direcories.add(file);
			} else {
				files.add(file);
			}
		}

		Collections.sort(direcories, new FileComparator());
		Collections.sort(files, new FileComparator());
		
		List<File> items = new LinkedList<File>();
		items.addAll(direcories);
		items.addAll(files);
		
		for (File item : items) {
			String name = item.getName();
			String lastMod = new SimpleDateFormat("dd-MMM-yyyy H:m").format(new Date(item.lastModified()));
			String size = "-";
			Resource itemRes;

			try {
				itemRes = resource.clone();
				itemRes.addSegment(name);
			} catch (CloneNotSupportedException e) {
				throw new RuntimeException(e);
			}
			
			if (itemRes.getType() == ResourceType.DIRECTORY) {
				name += "/";
			} else {
				size = FileSize.bytesToHuman(item.length());
			}
		
			html.append("<a href=\"" + itemRes.getUri() + "\">" + name);
			html.append("</a>");
			html.append(strRepeat(" ", Math.abs(24 - name.length())));
			html.append(lastMod);
			html.append(strRepeat(" ", Math.abs(24 - lastMod.length())));
			html.append(size);
			html.append("\n");
		}
		
		html.append("<hr>\n");
		html.append("</pre>\n");
		html.append("</body>\n");
		html.append("</html>\n");
		
		return html.toString();
	}
	
	@Override
	public BufferedReader getReader() {
		return new BufferedReader(new StringReader(html));
	}

	/**
	 * Repeat given string N times.
	 * Fond on: http://stackoverflow.com/questions/1235179/simple-way-to-repeat-a-string-in-java
	 * 
	 * @param str
	 * @param times
	 * @return
	 */
	private String strRepeat(String str, int times) {
		return String.format(String.format("%%0%dd", times), 0).replace("0", str);
	}
}
