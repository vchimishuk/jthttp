
public class PageFactory {
	public static Page getPage(Resource resource) {
		Page page;
		
		switch (resource.getType()) {
		case FILE:
			page = new PageFile(resource);
			break;

		case DIRECTORY:
			page = new PageListing(resource);
			break;
			
		case NOT_READABLE:
			page = new Page403(resource);
			break;
			
		default:
			page = new Page404(resource);
			break;
		}
		
		return page;
	}
}
