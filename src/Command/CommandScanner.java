package Command;
import java.io.BufferedReader;
import Exception.*;

public class CommandScanner {
	private CommandTypeInfo[] validcommandos;
	private static final String REGEXPSEARCHPATTERN = " ";
	private CommandDescriptor descriptor;
	private String[] usercommand;
	
	public CommandScanner(CommandTypeInfo newcommandoenum[], BufferedReader newreader)throws WrongCommandException{
		validcommandos = newcommandoenum;
		String s = newreader.toString();
		usercommand = s.split(REGEXPSEARCHPATTERN);
		checkCommandSyntax(usercommand[0]);
	}
	
	private void checkCommandSyntax(String command) throws WrongCommandException{
		for (int i = 0; i < validcommandos.length; i++) {
			if(command.equals(validcommandos[i].getName())){
				return;
			}
		}
		throw new WrongCommandException("The Command is nod"+command+" valid");
	}
	
	private void writDescriptorInfo(){
		for (int i = 0; i < validcommandos.length; i++) {
			if(usercommand[0].equals(validcommandos[i].getName())){
				descriptor.setCommandTypeInfo(validcommandos[i]);
			}
		}
	}
	
	public void fillInCommandDesc(CommandDescriptor newdescriptor){
		descriptor = newdescriptor;
	}
}
