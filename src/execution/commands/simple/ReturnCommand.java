/**
 * 
 */
package execution.commands.simple;

import org.antlr.v4.runtime.Token;

import builder.BuildChecker;
import builder.ErrorRepository;
import builder.errorcheckers.TypeChecker;
import builder.errorcheckers.UndeclaredChecker;
import console.Debug;
import console.Output;
import execution.ExecutionManager;
import execution.commands.ICommand;
import execution.commands.evaluation.EvaluationCommand;
import initial.GUI;
import initial.JabaParser.ExpressionContext;
import representation.MobiFunction;
import representation.MobiValue;
import representation.MobiValue.PrimitiveType;
import searching.VariableSearcher;
import semantic.util.AssignmentUtils;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

/**
 * Represents a return command which is specially used by a function.
 * 
 * @author NeilDG
 *
 */
public class ReturnCommand implements ICommand {
	private final static String TAG = "MobiProg_ReturnCommand";

	private ExpressionContext expressionCtx;
	private MobiFunction assignedMobiFunction;
	public boolean isBreakpoint = false;
	public ReturnCommand(ExpressionContext expressionCtx, MobiFunction mobiFunction) {
		System.out.println(TAG);
		this.expressionCtx = expressionCtx;
		this.assignedMobiFunction = mobiFunction;
		Token firstToken = expressionCtx.getStart();
		firstToken.getLine();

		UndeclaredChecker undeclaredChecker = new UndeclaredChecker(this.expressionCtx);
		undeclaredChecker.verify();

		MobiValue mobiValue = this.assignedMobiFunction.getReturnValue();
		if(mobiFunction.getReturnType().VOID_TYPE.equals(mobiFunction.getReturnType())) {
			BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, "Void Methods, Cannot Return a Value",
					firstToken.getLine());
		}
			
		try {
			MobiValue m = VariableSearcher.searchVariableInFunction(mobiFunction, expressionCtx.getText());
			if (!m.getPrimitiveType().equals(mobiValue.getPrimitiveType())) {
				
				if (mobiValue.getPrimitiveType() == PrimitiveType.BOOLEAN) {

					String additionalMessage = "Expected boolean.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage,
							firstToken.getLine());

				} else if (mobiValue.getPrimitiveType() == PrimitiveType.INT) {

					String additionalMessage = "Expected int.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage,
							firstToken.getLine());

				} else if (mobiValue.getPrimitiveType() == PrimitiveType.FLOAT
						|| mobiValue.getPrimitiveType() == PrimitiveType.DOUBLE) {

					String additionalMessage = "Expected floating point or double.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage,
							firstToken.getLine());

				}

				else if (mobiValue.getPrimitiveType() == PrimitiveType.STRING) {

					String additionalMessage = "Expected string.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage,
							firstToken.getLine());
				}
			}
		} catch (Exception e) {
			
			TypeChecker typeChecker = new TypeChecker(mobiValue, this.expressionCtx);
			typeChecker.verify();
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println(TAG);
		EvaluationCommand evaluationCommand = new EvaluationCommand(this.expressionCtx);
		Parameters params = new Parameters();
		params.putExtra(Debug.COMMAND, evaluationCommand);
		if(evaluationCommand.isBreakpoint()) {
			NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_BEFORE_POINT, params);
			//NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_AFTER_POINT, params);
		}
		evaluationCommand.execute();
		
		if(evaluationCommand.isBreakpoint()) {	
			NotificationCenter.getInstance().postNotification(Notifications.ON_BREAK_AFTER_POINT, params);
		}

		MobiValue mobiValue = this.assignedMobiFunction.getReturnValue();

		AssignmentUtils.assignAppropriateValue(mobiValue, evaluationCommand.getResult());
		// Console.log(LogType.DEBUG,"Return value is: "
		// +evaluationCommand.getResult().toEngineeringString());
	}

	@Override
	public boolean isBreakpoint() {
		// TODO Auto-generated method stub
		return isBreakpoint;
	}

}
