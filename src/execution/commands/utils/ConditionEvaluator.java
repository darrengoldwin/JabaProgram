/**
 * 
 */
package execution.commands.utils;

import console.Debug;
import console.Output;
import execution.ExecutionManager;
import execution.commands.evaluation.EvaluationCommand;
import initial.GUI;
import initial.JabaParser.ExpressionContext;
import initial.JabaParser.ParExpressionContext;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

/**
 * Utility class for execution that evaluates a condition.
 * This is used for IF, WHILE, DO-WHILE and FOR control statements.
 * @author NeilDG
 *
 */
public class ConditionEvaluator {

	private final static String TAG = "MobiProg_ConditionEvaluator";
	
	/*
	 * Evaluates the modified conditional expression via Eval-Ex
	 */
	/*public static boolean evaluateCondition(String modifiedConditionExpr) {
		
		//catch rules if the if value has direct boolean flags
		if(modifiedConditionExpr.contains("(true)")) {
			return true;
		}
		else if(modifiedConditionExpr.contains("(false)")) {
			return false;
		}
		
		Expression expr = new Expression(modifiedConditionExpr);
		int result = expr.eval().intValue();
		
		//Console.log("Evaluating " +modifiedConditionExpr+ ". result is " +result);
		
		if(result == 1) {
			return true;	
		}
		else {
			return false;
		}
	}*/
	
	public static boolean evaluateCondition(ParExpressionContext parExprCtx) {
		
		//ComparisonContext
		//ComparisonContext conditionaExprCtx = parExprCtx.comparison();
		ExpressionContext conditionExprCtx = parExprCtx.expression();
		
		//catch rules if the if value has direct boolean flags
		if(conditionExprCtx.getText().contains("(true)")) {
			return true;
		}
		else if(conditionExprCtx.getText().contains("(false)")) {
			return false;
		}
		
		EvaluationCommand evaluationCommand = new EvaluationCommand(conditionExprCtx);
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
		
		int result = evaluationCommand.getResult().intValue();
		
		//Console.log("Evaluating: " +conditionExprCtx.getText() + " Result: " +result);
		
		return (result == 1);
	}
	
	public static boolean evaluateCondition(ExpressionContext conditionExprCtx) {
		
		//catch rules if the if value has direct boolean flags
		if(conditionExprCtx.getText().contains("(true)")) {
			return true;
		}
		else if(conditionExprCtx.getText().contains("(false)")) {
			return false;
		}
		
		EvaluationCommand evaluationCommand = new EvaluationCommand(conditionExprCtx);
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
		
		int result = evaluationCommand.getResult().intValue();
		
		return (result == 1);
	}
}
