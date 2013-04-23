package Command;
import enums.*;

public class CommandScanner {
	private CommandTypeInfo[] validcommandos;
	
	public CommandScanner(){
		validcommandos = StockGameCommandType.values();
	}
}
