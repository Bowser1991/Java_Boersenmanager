package Exception;

public class WrongNameException extends RuntimeException {

	public WrongNameException() {
		super();
	}
	
	public WrongNameException(String s) {
		super(s);
	}

}
