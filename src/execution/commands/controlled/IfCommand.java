/**
 * 
 */
package execution.commands.controlled;

import java.util.ArrayList;
import java.util.List;

import execution.ExecutionManager;
import execution.ExecutionMonitor;
import execution.commands.ICommand;
import execution.commands.controlled.IControlledCommand.ControlTypeEnum;
import execution.commands.utils.ConditionEvaluator;
import initial.JabaParser.ParExpressionContext;
import mapping.IValueMapper;
import mapping.IdentifierMapper;

/**
 * A representation of a conditional statement
 * @author NeilDG
 *
 */
public class IfCommand implements IConditionalCommand {

	private final static String TAG = "MobiProf_IfCommand";
	
	private List<ICommand> positiveCommands; //list of commands to execute if the condition holds true
	private List<ICommand> negativeCommands; //list of commands to execute if the condition holds false
	
	private ParExpressionContext conditionalExpr;
	private String modifiedConditionExpr;
	
	public IfCommand(ParExpressionContext conditionalExpr) {
		this.positiveCommands = new ArrayList<ICommand>();
		this.negativeCommands = new ArrayList<ICommand>();
		
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
			//execute the positive commands
			if(ConditionEvaluator.evaluateCondition(this.conditionalExpr)) {
				for(ICommand command : this.positiveCommands) {
					executionMonitor.tryExecution();
					command.execute();
				}
			}
			//execute the negative commands
			else {
				for(ICommand command : this.negativeCommands) {
					executionMonitor.tryExecution();
					command.execute();
				}
			}
		} catch(InterruptedException e) {
			//Log.e(TAG, "Monitor block interrupted! " +e.getMessage());
		}
		
	}
	
	private void identifyVariables() {
		IValueMapper identifierMapper = new IdentifierMapper(this.conditionalExpr.getText());
		System.out.println(this.conditionalExpr.getText());
		identifierMapper.analyze(this.conditionalExpr);
		
		this.modifiedConditionExpr = identifierMapper.getModifiedExp();
	}

	@Override
	public ControlTypeEnum getControlType() {
		return ControlTypeEnum.CONDITIONAL_IF;
	}
	
	@Override
	public void addPositiveCommand(ICommand command) {
		this.positiveCommands.add(command);
	}
	
	@Override
	public void addNegativeCommand(ICommand command) {
		this.negativeCommands.add(command);
	}
	
	public void clearAllCommands() {
		this.positiveCommands.clear();
		this.negativeCommands.clear();
	}
	
	public int getPositiveCommandsCount() {
		return this.positiveCommands.size();
	}
	
	public int getNegativeCommandsCount() {
		return this.negativeCommands.size();
	}

}
