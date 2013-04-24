package Command;

public class CommandDescriptor {

	private CommandTypeInfo commandoinfo;
	private Object[] parameters;
	
	public CommandDescriptor(CommandTypeInfo command){
		commandoinfo =  command;
	}
	
	protected void setParams(Object[] params){
		parameters = params;
	}
	
	public CommandTypeInfo getcommandType(){
		return commandoinfo;
	}
	
	protected void setCommandTypeInfo(CommandTypeInfo newinfo){
		commandoinfo = newinfo;
	}
	
	public Object[] params(){
		return parameters;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
