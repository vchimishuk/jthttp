/**
 * Utility class for human readable (bytes, kilobytes, megabytes, ...) file size.
 *
 */
public class FileSize {
	private static float KILOBYTE = 1024f;
	
	private static float MEGABYTE = KILOBYTE * 1024f;
	
	private static float GIGABYTE = MEGABYTE * 1024f;
	
	public static String bytesToHuman(long size) throws IllegalArgumentException {
		if (size < 0) {
			throw new IllegalArgumentException("Size can't be less than zero");
		}
		
		String res;
		float foo;
		
		if (size < KILOBYTE) { // Bytes
			res = Long.toString(size);
		} else if (size < MEGABYTE) { // Kilobytes
			foo = size / KILOBYTE;
			res =  String.format("%.1fK", foo);
		} else if (size < GIGABYTE) { // Megabytes
			foo = size / MEGABYTE;
			res =  String.format("%.1fM", foo);
		} else { // Gigabytes
			foo = size / GIGABYTE;
			res =  String.format("%.1fG", foo);
		}
		
		// Remove '.0' at the end.
		if (res.length() > 3 && res.charAt(res.length() - 2) == '0' && res.charAt(res.length() - 3) == '.') {
			
			res = res.substring(0, res.length() - 3) + res.substring(res.length() - 1);
		}

		return res;
	}
}
