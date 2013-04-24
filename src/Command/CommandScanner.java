package Command;
import java.io.BufferedReader;

public class CommandScanner {
	private CommandTypeInfo[] validcommandos;
	private CommandDescriptor descriptor;
	
	public CommandScanner(CommandTypeInfo newcommandoenum[], BufferedReader newreader){
		validcommandos = newcommandoenum;
	
	}
	
	public void fillInCommandDesc( CommandDescriptor newdescriptor){
		descriptor = newdescriptor;
	}
}
