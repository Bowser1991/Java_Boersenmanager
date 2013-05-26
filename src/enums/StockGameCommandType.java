package enums;
import Command.*;

public enum StockGameCommandType  implements  CommandTypeInfo{
	HELP("help","* gets all available commands"), 
	EXIT("exit","* exit Programm"),  
	CREATEPLAYER("crp","<name> * adds a new Player by name", "addPlayer", Object.class), 
	BUYSHARE("bus","<Playername><Sharename><amount>* buys a Share","buyShare",String.class, String.class, int.class), 
	SELLSHARE("ses","<Playername><Sharename><amount>* sells a Share","sellShare",String.class, String.class, int.class), 
	ACCOUNTWORTH("acw","<name>* gets the account worth by playername","getAllAssetworth",String.class),
	SETACCOUNTWORTH("setacw"," <accountworth><name>* sets the account worth by playername","setPlayerAccount",long.class, String.class),
	PRICEDIVER("pricediv", "<sharename> <playername>* divers Shares to see if there is some provit if sold","diverShareSell",String.class, String.class),
	STARTBOT("startbot", "<playername>* starts bot to automatically buy and sell", "startBot", String.class),
	STOPBOT("stopbot", "<playername>* stops bot to automatically buy and sell", "stopBot", String.class),
	HISTORY("history", "<playername> <param> <mime type>* get history *mimetype: html or plain", "getSortedHistory", String.class, String.class, String.class),
	HISTORYSHARE("historyshare", "<playername> <sharename>* get share history", "getShareHistory", String.class, String.class);
	
	
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
	
	private StockGameCommandType(String command,  String helptext, Class<?>... T){
		commandname = command;
		this.helptext = helptext;
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
