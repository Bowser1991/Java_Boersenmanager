package Command;
import java.io.BufferedReader;
import Exception.*;

public class CommandScanner {
	private CommandTypeInfo[] validcommandos;
	private static final String REGEXPSEARCHPATTERN = " ";
	private CommandDescriptor descriptor;
	
	public CommandScanner(CommandTypeInfo commandinfo[], CommandDescriptor newdescriptor)throws WrongCommandException{
		validcommandos = commandinfo;
		descriptor = newdescriptor;
	}
	
	public void checkCommandSyntax(BufferedReader command) throws WrongCommandException{
		String buffer = command.toString();
		String[] userinsert = buffer.split(REGEXPSEARCHPATTERN);
		for (int i = 0; i < validcommandos.length; i++) {
			if(userinsert[0].equals(validcommandos[i].getName())){
				descriptor.setCommandTypeInfo(validcommandos[i]);
				Object[] paramobject = new Object[validcommandos[i].getParamTypes().length];
				for (int j = 1; j < userinsert.length; j++) {
					paramobject[j] = checkparamType(userinsert[j], validcommandos[i], j);
				}
				return;
			}else if(i == validcommandos.length){
				throw new WrongCommandException("The Command "+command+" is not valid");
			}
		}
	}
	
//	private void writDescriptorInfo(){
//		for (int i = 0; i < validcommandos.length; i++) {
//			if(usercommand[0].equals(validcommandos[i].getName())){
//				descriptor.setCommandTypeInfo(validcommandos[i]);
//			}
//		}
//	}
	
	private Object checkparamType(String parameter, CommandTypeInfo info, int paramnumber){	
		
		
	}
	
	public void fillInCommandDesc(CommandDescriptor newdescriptor){
		descriptor = newdescriptor;
	}
	
	
}
