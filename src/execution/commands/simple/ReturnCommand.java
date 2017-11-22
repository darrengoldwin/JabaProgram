/**
 * 
 */
package execution.commands.simple;

import builder.errorcheckers.TypeChecker;
import builder.errorcheckers.UndeclaredChecker;
import execution.commands.ICommand;
import execution.commands.evaluation.EvaluationCommand;
import initial.JabaParser.ExpressionContext;
import representation.MobiFunction;
import representation.MobiValue;
import semantic.util.AssignmentUtils;

/**
 * Represents a return command which is specially used by a function.
 * @author NeilDG
 *
 */
public class ReturnCommand implements ICommand {
	private final static String TAG = "MobiProg_ReturnCommand";
	
	private ExpressionContext expressionCtx;
	private MobiFunction assignedMobiFunction;
	
	public ReturnCommand(ExpressionContext expressionCtx, MobiFunction mobiFunction) {
		this.expressionCtx = expressionCtx;
		this.assignedMobiFunction = mobiFunction;
		
		UndeclaredChecker undeclaredChecker = new UndeclaredChecker(this.expressionCtx);
		undeclaredChecker.verify();
		
		MobiValue mobiValue = this.assignedMobiFunction.getReturnValue();
		TypeChecker typeChecker = new TypeChecker(mobiValue, this.expressionCtx);
		typeChecker.verify();
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		EvaluationCommand evaluationCommand = new EvaluationCommand(this.expressionCtx);
		evaluationCommand.execute();
		
		MobiValue mobiValue = this.assignedMobiFunction.getReturnValue();
		
		AssignmentUtils.assignAppropriateValue(mobiValue, evaluationCommand.getResult());
		//Console.log(LogType.DEBUG,"Return value is: " +evaluationCommand.getResult().toEngineeringString());
	}

}
