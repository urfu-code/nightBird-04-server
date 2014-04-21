
public class CodeException extends Exception {
private static final long serialVersionUID = 1L;
	
	public CodeException(String message) {
		super("Error: " + message);
	}
}
