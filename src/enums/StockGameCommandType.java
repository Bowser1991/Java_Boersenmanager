package enums;
import Command.*;

public enum StockGameCommandType  implements  CommandTypeInfo{
	HELP("help",Messages.getString("helpText")),  //$NON-NLS-1$ //$NON-NLS-2$
	EXIT("exit",Messages.getString("exitText")),   //$NON-NLS-1$ //$NON-NLS-2$
	CREATEPLAYER("crp",Messages.getString("createText"), "addPlayer", Object.class),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	BUYSHARE("bus",Messages.getString("buyText"),"buyShare",String.class, String.class, int.class),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	SELLSHARE("ses",Messages.getString("sellText"),"sellShare",String.class, String.class, int.class),  //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	ACCOUNTWORTH("acw",Messages.getString("accountworthText"),"getAllAssetworth",String.class), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	SETACCOUNTWORTH("setacw",Messages.getString("setaccountworthText"),"setPlayerAccount",long.class, String.class), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	PRICEDIVER("pricediv", Messages.getString("pricediverText"),"diverShareSell",String.class, String.class), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	STARTBOT("startbot", Messages.getString("startbotText"), "startBot", String.class), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	STOPBOT("stopbot", Messages.getString("stopbotText"), "stopBot", String.class), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	HISTORY("history", Messages.getString("historyText"), "getSortedHistory", String.class, String.class, String.class), //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	HISTORYSHARE("historyshare", Messages.getString("historyshareText"), "getShareHistory", String.class, String.class); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
	
	
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
