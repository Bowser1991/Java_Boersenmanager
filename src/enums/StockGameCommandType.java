package enums;
import java.lang.reflect.Method;

import Command.*;

public enum StockGameCommandType  implements  CommandTypeInfo{
	HELP("help","* help, exit, crp, bus, ses, acw, setacw", "printhelp"), 
	EXIT("exit","* exit Programm", "exitsystem"),  
	CREATEPLAYER("crp","<name> * adds a new Player by name", "addPlayer", String.class), 
	BUYSHARE("bus","<Playername><Sharename><amount>* buys a Share","buyShare",String.class, String.class, int.class), 
	SELLSHARE("ses","<Playername><Sharename><amount>* sells a Share","sellShare",String.class, String.class, int.class), 
	ACCOUNTWORTH("acw","<name>* gets the account worth by playername","getAllAssetworth",String.class),
	SETACCOUNTWORTH("setacw"," <accountworth><name>* sets the account worth by playername","setPlayerAccount",long.class, String.class);
	
	private String commandname;
	private String helptext;
	private Class<?> classparameter[];
	private String executevalue;
	
	private StockGameCommandType(String command,  String helptext, String methodname, Class<?>... T){
		commandname = command;
		this.helptext = helptext;
		executevalue = methodname;
		int counter = 0;
		classparameter = new Class<?>[T.length];
		for(Class<?> A : T){
			this.classparameter[counter] = A;
			counter++;
		}
	}
	
	

	@Override
	public String getName() {
		return commandname;
	}
	
	public String getImplMethods(){
		return executevalue;
	}
	
	@Override
	public String getHelpText() {
		return helptext;
	}

	@Override
	public Class<?>[] getParamTypes() {
		return classparameter;
	}
}
