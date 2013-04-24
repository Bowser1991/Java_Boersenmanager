package Command;

public class CommandDescriptor {

	private CommandTypeInfo[] commandoinfo;
	private Object[] parameters;
	
	public CommandDescriptor(Class<CommandTypeInfo> commandenum){
		commandoinfo = (CommandTypeInfo[]) commandenum.getTypeParameters();
	}
	
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
