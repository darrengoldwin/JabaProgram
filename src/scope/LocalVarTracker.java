package scope;

import java.util.ArrayList;
import java.util.Stack;

import execution.commands.ICommand;
import execution.commands.evaluation.AssignmentCommand;
import execution.commands.evaluation.MappingCommand;
import execution.commands.simple.IncDecCommand;

public class LocalVarTracker {
	
	private final static String TAG = "MobiProg_LocalVar";
	
	private Stack<ArrayList<String>> sessions;
	private static LocalVarTracker sharedInstance = null;
	
	private boolean successful = true;
	
	public static LocalVarTracker getInstance() {
		return sharedInstance;
	}
	
	private LocalVarTracker() {
		sessions = new Stack<ArrayList<String>>();
	}
	public static void initialize() {
		sharedInstance = new LocalVarTracker();
	}
	
	public static void reset() {
		sharedInstance.successful  = true;
	}
	public void populateLocalVars(ICommand command) {
		
		if(command instanceof MappingCommand) {
			sessions.peek().add(((MappingCommand)command).getIdentifierString());
		} else if(command instanceof AssignmentCommand) {
			if(!((AssignmentCommand)command).isLeftHandArrayAccessor())
				sessions.peek().add(((AssignmentCommand)command).getLeftHandExprCtx().getText());
		}else if(command instanceof IncDecCommand) {
			sessions.peek().add(((IncDecCommand)command).getIdentifierString());
		}
	}
	public void resetLocalVar(ArrayList<String> localVars) {
		localVars.clear();
	}
	public void startNewSession() {
		sessions.push(new ArrayList<String>());
	}
	
	public ArrayList<String> getCurrentSession() {
		return this.sessions.peek();
	}
	public ArrayList<String> endCurrentSession() {
		return this.sessions.pop();
	}
	

}
