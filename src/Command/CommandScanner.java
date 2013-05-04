package Command;
import java.io.BufferedReader;
import java.io.IOException;

import Exception.*;

public class CommandScanner {
	private CommandTypeInfo[] validcommandos;
	private static final String REGEXPSEARCHPATTERN = " ";
	private CommandDescriptor descriptor;
	
	public CommandScanner(CommandTypeInfo commandinfo[], CommandDescriptor newdescriptor){
		validcommandos = commandinfo;
		descriptor = newdescriptor;
	}
	
	/**
	 * checkCommandSyntax(BufferedReader commandinput)
	 * Bekommt einen Buffered Reader übergeben liest von diesem aus eine Zeile aus der Kommandozeile
	 * und überprüft diese dann auf die im ENUM festgelegte Syntax.
	 * @param commandinput
	 * @throws WrongCommandException
	 * @throws IOException
	 */
	
	public void checkCommandSyntax(BufferedReader commandinput) throws WrongCommandException, IOException{
		String command = commandinput.readLine();
		String[] userinsert = command.split(REGEXPSEARCHPATTERN);
		for (int i = 0; i < validcommandos.length; i++) {
			//if name of the insert command is ok then
			if(userinsert[0].equals(validcommandos[i].getName())){
				// what to do when user wants to get some help of commando
				if(userinsert.length > 1 && userinsert[1].equalsIgnoreCase("help")){
					descriptor.setCommandTypeInfo(validcommandos[i]);
					Object[] bufferObject = new Object[1];
					bufferObject[0] = userinsert[1];
					descriptor.setParams(bufferObject);
					return;
				}
				else if(userinsert.length - 1 != validcommandos[i].getParamTypes().length){
					throw new WrongCommandException("invalid number of parameters");
				}
				//set to descriptor CommandTypeInfo
				descriptor.setCommandTypeInfo(validcommandos[i]);
				//set params in descriptor
				descriptor.setParams(checkparamType(userinsert, validcommandos[i]));
				return;
			}else if(i == validcommandos.length - 1){
				throw new WrongCommandException("The Command "+command+" is not valid");
			}
		}
	}
	
	/**
	 * Object[] checkparamType(String[] parameter, CommandTypeInfo info)
	 * Die Methode bekommt ein array in dem alle Parameter enthalten. Im array an der stelle 0 steht also 
	 * auch der Befehl welcher hier dann eben nicht beachtet werden soll
	 * @param parameter
	 * @param info
	 * @return
	 * @throws NumberFormatException
	 */
	
	private Object[] checkparamType(String[] parameter, CommandTypeInfo info) throws NumberFormatException{	
		Class<?> parameterclass = null;
		Object[] objectbuffer = new Object[parameter.length - 1];
		for (int i = 0; i < info.getParamTypes().length; i++) {
			parameterclass = info.getParamTypes()[i];
			switch (parameterclass.getName()) {
			case "java.lang.String":
				objectbuffer[i] = parameter[i+1];
				break;
			case "int":
				objectbuffer[i] = Integer.parseInt(parameter[i+1]);
				break;
			case "long":
				objectbuffer[i] = Long.parseLong(parameter[i+1]);
				break;
			case "short":
				objectbuffer[i] = Short.parseShort(parameter[i+1]);
				break;
			case "double":
				objectbuffer[i] = Double.parseDouble(parameter[i+1]);
				break;
			case "float":
				objectbuffer[i] = Float.parseFloat(parameter[i+1]);
				break;
			default:
				objectbuffer[i] = parameter[i+1];
			}
		}
		return objectbuffer;
	}
	
	public void fillInCommandDesc(CommandDescriptor newdescriptor){
		descriptor = newdescriptor;
	}
	
	
}
