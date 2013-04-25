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
	
	public void checkCommandSyntax(String command) throws WrongCommandException{
		String[] userinsert = command.split(REGEXPSEARCHPATTERN);
		for (int i = 0; i < validcommandos.length; i++) {
			//if name of the insert command is ok then
			if(userinsert[0].equals(validcommandos[i].getName())){
				//set to descriptor CommandTypeInfo
				descriptor.setCommandTypeInfo(validcommandos[i]);
				//set params in descriptor
				descriptor.setParams(checkparamType(userinsert, validcommandos[i]));
				return;
			}else if(i == validcommandos.length){
				throw new WrongCommandException("The Command "+command+" is not valid");
			}
		}
	}
	
	private Object[] checkparamType(String[] parameter, CommandTypeInfo info) throws NumberFormatException{	
		Class<?> parameterclass = null;
		Object[] objectbuffer = new Object[parameter.length];
		for (int i = 0; i < info.getParamTypes().length; i++) {
			parameterclass = info.getParamTypes()[i];
			switch (parameterclass.getName()) {
			case "int":
				objectbuffer[i] = Integer.parseInt(parameter[i]);
				break;
			case "long":
				objectbuffer[i] = Long.parseLong(parameter[i]);
				break;
			case "short":
				objectbuffer[i] = Short.parseShort(parameter[i]);
				break;
			case "double":
				objectbuffer[i] = Double.parseDouble(parameter[i]);
				break;
			case "float":
				objectbuffer[i] = Float.parseFloat(parameter[i]);
				break;
			}
		}
		return objectbuffer;
	}
	
	public void fillInCommandDesc(CommandDescriptor newdescriptor){
		descriptor = newdescriptor;
	}
	
	
}
