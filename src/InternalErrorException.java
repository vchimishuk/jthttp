public class InternalErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public InternalErrorException() {
		super();
	}
	
	public InternalErrorException(Throwable t) {
		super(t);
	}
	
	public InternalErrorException(String message) {
		super(message);
	}
}
