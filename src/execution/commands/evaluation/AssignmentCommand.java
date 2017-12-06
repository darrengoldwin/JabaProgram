/**
 * 
 */
package execution.commands.evaluation;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import analyzer.FunctionCallVerifier;
import builder.errorcheckers.ConstChecker;
import builder.errorcheckers.TypeChecker;
import builder.errorcheckers.UndeclaredChecker;
import console.Debug;
import console.Output;
import execution.ExecutionManager;
import execution.commands.ICommand;
import initial.GUI;
import initial.JabaLexer;
import initial.JabaParser.ExpressionContext;
import representation.MobiArray;
import representation.MobiValue;
import searching.VariableSearcher;
import semantic.util.AssignmentUtils;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

public class AssignmentCommand implements ICommand{

	private final static String TAG = "MobiProg_NewAssignmentCommand";

	private ExpressionContext leftHandExprCtx;
	private ExpressionContext rightHandExprCtx;
	public boolean isBreakpoint = false; 
	
	public AssignmentCommand(ExpressionContext leftHandExprCtx,
			ExpressionContext rightHandExprCtx) {
		System.out.println(TAG);
		this.leftHandExprCtx = leftHandExprCtx;
		this.rightHandExprCtx = rightHandExprCtx;
		
		UndeclaredChecker undeclaredChecker = new UndeclaredChecker(this.leftHandExprCtx);
		undeclaredChecker.verify();
		
		ConstChecker constChecker = new ConstChecker(this.leftHandExprCtx);
		constChecker.verify();
		
		undeclaredChecker = new UndeclaredChecker(this.rightHandExprCtx);
		undeclaredChecker.verify();
		
		ParseTreeWalker functionWalker = new ParseTreeWalker();
		FunctionCallVerifier f = new FunctionCallVerifier();
		functionWalker.walk(f, this.rightHandExprCtx);
		
		//type check the mobivalue
		MobiValue mobiValue;
		if(ExecutionManager.getInstance().isInFunctionExecution()) {
			mobiValue = VariableSearcher.searchVariableInFunction(ExecutionManager.getInstance().getCurrentFunction(), this.leftHandExprCtx.getText());
		}
		else {
			mobiValue = VariableSearcher.searchVariable(this.leftHandExprCtx.getText());
		}
		if(f.isFunction()) {
			TypeChecker typeChecker = new TypeChecker(mobiValue, this.rightHandExprCtx);
			typeChecker.checkType(f.getFunc().getReturnValue().getPrimitiveType(), mobiValue.getPrimitiveType());
		}else {
			TypeChecker typeChecker = new TypeChecker(mobiValue, this.rightHandExprCtx);
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
		
		try {
			EvaluationCommand evaluationCommand = new EvaluationCommand(this.rightHandExprCtx);
			
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
			if(this.isLeftHandArrayAccessor()) {
				
				this.handleArrayAssignment(evaluationCommand.getResult().toEngineeringString());
			}
			else {
				
				MobiValue mobiValue = VariableSearcher.searchVariable(this.leftHandExprCtx.getText());
				AssignmentUtils.assignAppropriateValue(mobiValue, evaluationCommand.getResult());
			}
			
		} catch(Exception e) {
			
			if(this.isLeftHandArrayAccessor()) {
				
				this.handleArrayAssignment(this.rightHandExprCtx.getText());
			}
			else {
				MobiValue mobiValue = VariableSearcher.searchVariable(this.leftHandExprCtx.getText());
				if(mobiValue.getPrimitiveType().equals(MobiValue.PrimitiveType.STRING) || 
						mobiValue.getPrimitiveType().equals(MobiValue.PrimitiveType.CHAR)) {
					mobiValue.setValue(this.rightHandExprCtx.getText());
					
				}
			}
			
			
			
		}
		
		
	}
	
	public boolean isLeftHandArrayAccessor() {
		List<TerminalNode> lBrackTokens = this.leftHandExprCtx.getTokens(JabaLexer.LBRACK);
		List<TerminalNode> rBrackTokens = this.leftHandExprCtx.getTokens(JabaLexer.RBRACK);
		
		return(lBrackTokens.size() > 0 && rBrackTokens.size() > 0);
	}
	
	private void handleArrayAssignment(String resultString) {
		TerminalNode identifierNode = this.leftHandExprCtx.expression(0).primary().Identifier();
		ExpressionContext arrayIndexExprCtx = this.leftHandExprCtx.expression(1);
		
		MobiValue mobiValue = VariableSearcher.searchVariable(identifierNode.getText());
		MobiArray mobiArray = (MobiArray) mobiValue.getValue();
		
		EvaluationCommand evaluationCommand = new EvaluationCommand(arrayIndexExprCtx);
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
		//create a new array value to replace value at specified index
		MobiValue newArrayValue = new MobiValue(null, mobiArray.getPrimitiveType());
		
		MobiValue m = VariableSearcher.searchVariable(resultString);
		
		if(m != null)
			newArrayValue.setValue(m.getValue().toString());
		else
			newArrayValue.setValue(resultString);
		
		mobiArray.updateValueAt(newArrayValue, evaluationCommand.getResult().intValue());
		
		//Console.log("Index to access: " +evaluationCommand.getResult().intValue()+ " Updated with: " +resultString);
	}

	public ExpressionContext getLeftHandExprCtx() {
		// TODO Auto-generated method stub
		return leftHandExprCtx;
	}

	@Override
	public boolean isBreakpoint() {
		// TODO Auto-generated method stub
		return isBreakpoint;
	}
}
