/**
 * 
 */
package execution.commands.controlled;

import console.Debug;
import console.Output;
import execution.ExecutionManager;
import execution.ExecutionMonitor;
import execution.commands.ICommand;
import initial.GUI;
import initial.JabaParser.ParExpressionContext;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

/**
 * Represents a do while command which is essentially a modified while command
 * @author NeilDG
 *
 */
public class DoWhileCommand extends WhileCommand {

	private final static String TAG = "MobiProg_DoWhileCommand";
	
	public DoWhileCommand(ParExpressionContext parExprCtr) {
		super(parExprCtr);
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println(TAG);
		this.executeFirstCommandSequence();
		super.execute();
	}
	
	/*
	 * Executes the first command sequence before actually executing the behavior for the while command
	 */
	private void executeFirstCommandSequence() {
		this.identifyVariables();
		
		ExecutionMonitor executionMonitor = ExecutionManager.getInstance().getExecutionMonitor();
		
		try {
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
			
		} catch(InterruptedException e) {
			//Log.e(TAG, "Monitor block interrupted! " +e.getMessage());
		}
	}

	@Override
	public ControlTypeEnum getControlType() {
		return ControlTypeEnum.DO_WHILE_CONTROL;
	}
}
