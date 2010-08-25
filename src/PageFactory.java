
public class PageFactory {
	public static Page getPage(Resource resource) {
		Page page;
		
		if (resource.isDirectory()) {
			page = new PageListing(resource);
		} else {
			page = new Page404(resource);
//			try {
//				response.getHeaders().put("Content-Type", resource.getMimeType());
//				response.setReader(resource.getReader());
//			} catch (FileNotFoundException e) {
//				logger.debug("File not found.", e);
//				
//				// TODO: Show 404 page here.
//				throw new RuntimeException();
//			}
		}
		
		return page;
	}
}
