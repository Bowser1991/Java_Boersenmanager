package enums;
import Command.*;

public enum StockGameCommandType  implements  CommandTypeInfo{
	HELP("help","* help, exit, crp, bus, ses, acw"), 
	EXIT("exit","* exit Programm"), 
	CREATEPLAYER("crp","<name> * adds a new Player by name", String.class), 
	BUYSHARE("bus","<Playername><Sharename><amount>* buys a Share",String.class, String.class, long.class), 
	SELLSHARE("ses","<Playername><Sharename><amount>* sells a Share",String.class, String.class, long.class), 
	ACCOUNTWORTH("acw","<name>* gets the account worth by playername",String.class);
	
	private String commandname;
	private String helptext;
	private Class<?> classparameter[];
	
	private StockGameCommandType(String command, String helptext, Class<?>... T ){
		commandname = command;
		this.helptext = helptext;
		int counter = 0;
		for(Class<?> A : T){
			this.classparameter[counter] = A;
			counter++;
		}
	}

	@Override
	public String getName() {
		return commandname;
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
