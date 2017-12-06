/**
 * 
 */
package statements;

import java.util.Stack;

import org.antlr.v4.runtime.Token;

import execution.ExecutionManager;
import execution.commands.ICommand;
import execution.commands.controlled.ForCommand;
import execution.commands.controlled.IConditionalCommand;
import execution.commands.controlled.IControlledCommand;
import execution.commands.controlled.IfCommand;
import execution.commands.controlled.WhileCommand;
import execution.commands.evaluation.ArrayInitializeCommand;
import execution.commands.evaluation.AssignmentCommand;
import execution.commands.evaluation.EvaluationCommand;
import execution.commands.evaluation.MappingCommand;
import execution.commands.simple.FunctionCallCommand;
import execution.commands.simple.IncDecCommand;
import execution.commands.simple.PrintCommand;
import execution.commands.simple.ReturnCommand;
import execution.commands.simple.ScanCommand;
import initial.GUI;

/**
 * A singleton class that detects if a certain statement is inside a controlled statement
 * Contains utility functions to add certain commands into the active controlled command.
 * This class makes nested statements possible.
 * @author NeilDG
 *
 */
public class StatementControlOverseer {

	private final static String TAG = "MobiProg_StatementControlOverseer";
	
	private static StatementControlOverseer sharedInstance = null;
	
	public static StatementControlOverseer getInstance() {
		return sharedInstance;
	}
	
	private Stack<ICommand> procedureCallStack;
	//private ICommand rootControlledCommand = null;
	private ICommand activeControlledCommand = null;
	
	private boolean isInPositive = true; //used for conditional statements to indicate if the series of commands should go to the positive command list.
	
	private StatementControlOverseer() {
		this.procedureCallStack = new Stack<ICommand>();
		
		//Log.e(TAG, "Stack initialized!");
	}
	
	public static void initialize() {
		sharedInstance = new StatementControlOverseer();
	}
	
	public static void reset() {
		sharedInstance.procedureCallStack.clear();
		//sharedInstance.rootControlledCommand = null;
		sharedInstance.activeControlledCommand = null;
	}
	
	public void openConditionalCommand(IConditionalCommand command) {
		if(this.procedureCallStack.isEmpty()) {
			this.procedureCallStack.push(command);
			this.activeControlledCommand = command;
		}
		else {
			this.processAdditionOfCommand(command);
		}
		
		this.isInPositive = true;
		
	}
	
	/*
	 * Opens a new controlled command
	 */
	public void openControlledCommand(IControlledCommand command) {
		this.procedureCallStack.push(command);
		this.activeControlledCommand = command;
	}
	
	public boolean isInPositiveRule() {
		return this.isInPositive;
	}
	
	public void reportExitPositiveRule() {
		this.isInPositive = false;
	}
	
	/*
	 * Processes the proper addition of commands.
	 */
	private void processAdditionOfCommand(ICommand command) {
		
		//if the current active controlled command is that of a conditional command,
		//we either add the newly opened command as either positive or a negative command
		if(this.isInConditionalCommand()) {
			IConditionalCommand conditionalCommand = (IConditionalCommand) this.activeControlledCommand;
			
			if(this.isInPositiveRule()) {
				conditionalCommand.addPositiveCommand(command);
			}
			else {
				conditionalCommand.addNegativeCommand(command);
			}
			
			this.procedureCallStack.push(command);
			this.activeControlledCommand = command;
		}
		//just add the newly opened command as a command under the last active controlled command.
		else {
			
			IControlledCommand controlledCommand = (IControlledCommand) this.activeControlledCommand;
			controlledCommand.addCommand(command);
			
			//Console.log(LogType.DEBUG, "Adding to " +controlledCommand.getControlType());
			
			this.procedureCallStack.push(command);
			this.activeControlledCommand = command;
		}
	}
	
	
	/*
	 * Closes the current active controlled command and adds the root controlled command to the execution manager.
	 * The active controlled command is set to null.
	 */
	public void compileControlledCommand() {
		
		//we arrived at the root node, therefore we add this now to the execution manager
		if(this.procedureCallStack.size() == 1) {
			ICommand command = this.procedureCallStack.pop();
//			Token firstToken = comma.getStart();
//			firstToken.getLine();
//			if(firstToken.getLine() == GUI.getInstance().breakpoint) {
//				if(command.getClass().toString().equals(AssignmentCommand.class.toString()))
//					((AssignmentCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(ArrayInitializeCommand.class.toString()))
//					((ArrayInitializeCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(EvaluationCommand.class.toString()))
//					((EvaluationCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(MappingCommand.class.toString()))
//					((MappingCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(FunctionCallCommand.class.toString()))
//					((FunctionCallCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(IncDecCommand.class.toString()))
//					((IncDecCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(PrintCommand.class.toString()))
//					((PrintCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(ReturnCommand.class.toString()))
//					((ReturnCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(ScanCommand.class.toString()))
//					((ScanCommand) command).isBreakPoint = true;
//				else if(command.getClass().toString().equals(WhileCommand.class.toString()))
//					((WhileCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(IfCommand.class.toString()))
//					((IfCommand) command).isBreakpoint = true;
//				else if(command.getClass().toString().equals(ForCommand.class.toString()))
//					((ForCommand) command).isBreakpoint = true;
//			}
			ExecutionManager.getInstance().addCommand(command);
			
			this.activeControlledCommand = null;
		}
		//we pop then add it to the next root node
		else if(this.procedureCallStack.size() > 1) {
			ICommand childCommand = this.procedureCallStack.pop();
			ICommand parentCommand = this.procedureCallStack.peek();
			this.activeControlledCommand = parentCommand;
			
			if(parentCommand instanceof IControlledCommand) {
				IControlledCommand controlledCommand = (IControlledCommand) parentCommand;
				controlledCommand.addCommand(childCommand);

			}
		}
		else {
			//Log.i(TAG, "Procedure call stack is now empty.");
		}
	}
	
	public boolean isInConditionalCommand() {
		return (this.activeControlledCommand != null && activeControlledCommand instanceof IConditionalCommand);
	}
	
	public boolean isInControlledCommand() {
		return (this.activeControlledCommand!= null && this.activeControlledCommand instanceof IControlledCommand);
	}
	
	public ICommand getActiveControlledCommand() {
		return this.activeControlledCommand;
	}
}
