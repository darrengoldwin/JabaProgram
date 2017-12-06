/**
 * 
 */
package execution.commands.controlled;

import java.util.ArrayList;
import java.util.List;

import console.Debug;
import console.Output;
import execution.ExecutionManager;
import execution.ExecutionMonitor;
import execution.commands.ICommand;
import execution.commands.utils.ConditionEvaluator;
import initial.GUI;
import initial.JabaParser.ParExpressionContext;
import mapping.IValueMapper;
import mapping.IdentifierMapper;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

/**
 * Representation of a while command
 * @author NeilDG
 *
 */
public class WhileCommand implements IControlledCommand {

	private final static String TAG = "MobiProg_WhileCommand";
	
	protected List<ICommand> commandSequences; //the list of commands inside the WHILE statement
	
	protected ParExpressionContext conditionalExpr;
	protected String modifiedConditionExpr;
	public boolean isBreakpoint = false;
	
	public WhileCommand(ParExpressionContext conditionalExpr) {
		this.commandSequences = new ArrayList<ICommand>();
		this.conditionalExpr = conditionalExpr;
	}
	
	/*
	 * Executes the command
	 * (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println(TAG);
		this.identifyVariables();
		
		ExecutionMonitor executionMonitor = ExecutionManager.getInstance().getExecutionMonitor();
		
		try {
			//evaluate the given condition
			while(ConditionEvaluator.evaluateCondition(this.conditionalExpr)) {
				for(ICommand command : this.commandSequences) {
					Parameters params = new Parameters();
					params.putExtra(Debug.COMMAND, command);
					if(command.isBreakpoint()) {
						NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_BEFORE_POINT, params);
						//NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_AFTER_POINT, params);
					}
					executionMonitor.tryExecution();
					command.execute();
					
					if(command.isBreakpoint()) {	
						NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_AFTER_POINT, params);
					}
				}
				
				this.identifyVariables(); //identify variables again to detect changes to such variables used.
			}
			
		} catch(InterruptedException e) {
			//Log.e(TAG, "Monitor block interrupted! " +e.getMessage());
		}
	}
	
	protected void identifyVariables() {
		IValueMapper identifierMapper = new IdentifierMapper(this.conditionalExpr.getText());
		identifierMapper.analyze(this.conditionalExpr);
		
		this.modifiedConditionExpr = identifierMapper.getModifiedExp();
	}

	@Override
	public ControlTypeEnum getControlType() {
		return ControlTypeEnum.WHILE_CONTROL;
	}
	
	@Override
	public void addCommand(ICommand command) {
		
		//Console.log(LogType.DEBUG, "		Added command to WHILE");
		this.commandSequences.add(command);
	}
	
	public int getCommandCount() {
		return this.commandSequences.size();
	}

	@Override
	public boolean isBreakpoint() {
		// TODO Auto-generated method stub
		return false;
	}

}
