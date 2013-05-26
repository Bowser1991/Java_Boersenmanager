package Command;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Vector;

import Exception.*;

public class CommandScanner {
	private Hashtable<String, CommandTypeInfo> validcommandos = new Hashtable<>();
	private static final String REGEXPSEARCHPATTERN = " ";
	private CommandDescriptor descriptor;
	/**
	 * CommandScanner(CommandTypeInfo commandinfo[], CommandDescriptor newdescriptor).
	 * Prueft ein Kommando auf richtige Eingabe Werte
	 * @param commandinfo
	 * CommandType Info Array, also Info aller gueltigen Commandos als Array
	 * @param newdescriptor
	 * in den Descriptor werden alle Infos die der Scanner aus dem Commando
	 * bekommt eingetragen
	 */
	public CommandScanner(CommandTypeInfo commandinfo[], CommandDescriptor newdescriptor){
		for (int i = 0; i < commandinfo.length; i++) {
			validcommandos.put(commandinfo[i].getName(), commandinfo[i]);
		};
		descriptor = newdescriptor;
	}
	/**
	 * checkCommandSyntax(BufferedReader commandinput)
	 * Bekommt einen Buffered Reader übergeben liest von diesem aus eine Zeile aus der Kommandozeile
	 * und überprüft diese dann auf die im ENUM festgelegte Syntax.
	 * @param commandinput
	 * Reader ueber den die EIngabe kommt
	 * @throws WrongCommandException
	 * Wenn zu die falsche Anzahl an Parametern eingegeben wird
	 * @throws IOException
	 */
	public void checkCommandSyntax(BufferedReader commandinput)
			throws WrongCommandException, IOException {
		String command = commandinput.readLine();
		Vector<String> userinsert = new Vector<String>();
		userinsert.addAll(Arrays.asList(command.split(REGEXPSEARCHPATTERN)));
		userinsert.trimToSize();
		// if name of the insert command is ok then
		if (validcommandos.containsKey(userinsert.firstElement())) {
			// what to do when user wants to get some help of commando
			if (userinsert.contains("help") || userinsert.contains("Help")) {
				descriptor.setCommandTypeInfo(validcommandos.get(userinsert.firstElement()));
				userinsert.remove(userinsert.firstElement());
				descriptor.setParams(userinsert.toArray());
				return;
			} else if (userinsert.size() - 1 != validcommandos.get(userinsert.firstElement()).getParamTypes().length) {
				throw new WrongCommandException("invalid number of parameters");
			}else if(userinsert.contains("plain")||userinsert.contains("html")){
				descriptor.setCommandTypeInfo(validcommandos.get(userinsert.firstElement()));
				userinsert.remove(userinsert.firstElement());
				descriptor.setParams(userinsert.toArray());
				return;
			}
			// set to descriptor CommandTypeInfo
			descriptor.setCommandTypeInfo(validcommandos.get(userinsert.firstElement()));
			// set params in descriptor
			descriptor.setParams(checkparamType(userinsert, validcommandos));
			return;
		} else {
			throw new WrongCommandException("The Command " + command
					+ " is not valid");
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
	private Object[] checkparamType(Vector<String> parameter, Hashtable<String, CommandTypeInfo> info) throws NumberFormatException{	
		Class<?> parameterclass = null;
		parameter.trimToSize();
		Object[] objectbuffer = new Object[parameter.size() - 1];
		CommandTypeInfo commandinfo = info.get(parameter.get(0));
		for (int i = 0; i < commandinfo.getParamTypes().length; i++) {
			parameterclass = commandinfo.getParamTypes()[i];
			switch (parameterclass.getName()) {
			case "java.lang.String":
				objectbuffer[i] = parameter.get(i+1);
				break;
			case "int":
				objectbuffer[i] = Integer.parseInt(parameter.get(i+1));
				break;
			case "long":
				objectbuffer[i] = Long.parseLong(parameter.get(i+1));
				break;
			case "short":
				objectbuffer[i] = Short.parseShort(parameter.get(i+1));
				break;
			case "double":
				objectbuffer[i] = Double.parseDouble(parameter.get(i+1));
				break;
			case "float":
				objectbuffer[i] = Float.parseFloat(parameter.get(i+1));
				break;
				default:
					objectbuffer[i] = parameter.get(i+1);
			}
		}
		return objectbuffer;
	}
	public void fillInCommandDesc(CommandDescriptor newdescriptor){
		descriptor = newdescriptor;
	}
	
	
}
