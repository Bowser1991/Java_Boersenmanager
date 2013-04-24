package Command;

public class CommandDescriptor {

	private CommandTypeInfo commandoinfo;
	private Object[] parameters;
	
	public CommandDescriptor(CommandTypeInfo command){
		commandoinfo =  command;
	}
	
	public void setParams(Object[] params){
		parameters = params;
	}
	
	public CommandTypeInfo commandType(){
		return commandoinfo;
	}
	
	public void setCommandTypeInfo(CommandTypeInfo newinfo){
		commandoinfo = newinfo;
	}
	
	public Object[] params(){
		return parameters;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

}
