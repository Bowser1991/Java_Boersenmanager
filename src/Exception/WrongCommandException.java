package Exception;

public class WrongCommandException extends Exception {
	
	public WrongCommandException(String s) {
		super(s);
	}
	
	public WrongCommandException(){
		super();
	}
}
