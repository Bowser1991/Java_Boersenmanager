package enums;

public enum StockGameCommandType {
	HELP("help","* help, exit, crp, bus, ses, acw",""), 
	EXIT("exit","* exit Programm",""), 
	CREATEPLAYER("help","<name> * adds a new Player by name", "String.class"), 
	BUYSHARE("help","* help, exit, crp, bus, ses, acw"), 
	SELLSHARE("help","* help, exit, crp, bus, ses, acw"), 
	ACCOUNTWORTH("help","* help, exit, crp, bus, ses, acw");
	
	private StockGameCommandType(String command, String helptext, String classes ){
		
	}
}
