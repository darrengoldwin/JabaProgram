/**
 * 
 */
package execution.commands.controlled;

import java.util.ArrayList;
import java.util.List;

import analyzer.LocalVariableAnalyzer;
import console.Debug;
import console.Output;
import execution.ExecutionManager;
import execution.ExecutionMonitor;
import execution.commands.ICommand;
import execution.commands.utils.ConditionEvaluator;
import initial.GUI;
import initial.JabaParser.ExpressionContext;
import initial.JabaParser.LocalVariableDeclarationContext;
import mapping.IValueMapper;
import mapping.IdentifierMapper;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

/**
 * Represents the for command
 * @author NeilDG
 *
 */
public class ForCommand implements IControlledCommand {

	private final static String TAG = "MobiProg_ForCommand";
	
	private List<ICommand> commandSequences;
	
	private LocalVariableDeclarationContext localVarDecCtx; //a local variable ctx that is evaluated at the start of the for loop
	private ExpressionContext conditionalExpr; //the condition to satisfy
	private ICommand updateCommand; //the update command aftery ever iteration
	
	private String modifiedConditionExpr;
	
	public boolean isBreakpoint = false; 
	public ForCommand(LocalVariableDeclarationContext localVarDecCtx, ExpressionContext conditionalExpr, ICommand updateCommand) {
		this.localVarDecCtx = localVarDecCtx;
		this.conditionalExpr = conditionalExpr;
		this.updateCommand = updateCommand;
		
		this.commandSequences = new ArrayList<ICommand>();
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println(TAG);
		this.evaluateLocalVariable();
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
				
				Parameters params = new Parameters();
				params.putExtra(Debug.COMMAND, this.updateCommand);
				if(updateCommand.isBreakpoint()) {
					NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_BEFORE_POINT, params);
					//NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_AFTER_POINT, params);
				}
				executionMonitor.tryExecution();
				updateCommand.execute();
				
				if(updateCommand.isBreakpoint()) {	
					NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_AFTER_POINT, params);
				}
				this.identifyVariables(); //identify variables again to detect changes to such variables used.
			}
			
		} catch(InterruptedException e) {
			//Log.e(TAG, "Monitor block interrupted! " +e.getMessage());
		}
	}
	
	private void evaluateLocalVariable() {
		if(this.localVarDecCtx != null) {
			LocalVariableAnalyzer localVarAnalyzer = new LocalVariableAnalyzer();
			localVarAnalyzer.markImmediateExecution();
			localVarAnalyzer.analyze(this.localVarDecCtx);
		}
	}
	
	private void identifyVariables() {
		IValueMapper identifierMapper = new IdentifierMapper(this.conditionalExpr.getText());
		identifierMapper.analyze(this.conditionalExpr);
		
		this.modifiedConditionExpr = identifierMapper.getModifiedExp();
	}

	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.controlled.IControlledCommand#getControlType()
	 */
	@Override
	public ControlTypeEnum getControlType() {
		return ControlTypeEnum.FOR_CONTROL;
	}

	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.controlled.IControlledCommand#addCommand(com.neildg.mobiprog.execution.commands.ICommand)
	 */
	@Override
	public void addCommand(ICommand command) {
		
		//Console.log(LogType.DEBUG, "		Added command to FOR");
		this.commandSequences.add(command);
	}
	
	public int getCommandCount() {
		return this.commandSequences.size();
	}

	@Override
	public boolean isBreakpoint() {
		// TODO Auto-generated method stub
		return isBreakpoint;
	}

}
