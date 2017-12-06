/**
 * 
 */
package execution.commands.simple;

import java.util.List;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

import analyzer.FunctionCallVerifier;
import builder.ParserHandler;
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
import scope.ClassScope;
import searching.VariableSearcher;
import semantics.symboltable.SymbolTable;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

/**
 * Represents a function call command
 * @author Patrick
 *
 */
public class FunctionCallCommand implements ICommand {
	private final static String TAG = "MobiProg_FunctionCallCommand";
	
	private MobiFunction mobiFunction;
	private ExpressionContext exprCtx;
	private String functionName;
	public boolean isBreakpoint = false;
	public FunctionCallCommand(String functionName, ExpressionContext exprCtx) {
		System.out.println(TAG);
		this.functionName = functionName;
		this.exprCtx = exprCtx;
		
		this.searchFunction();
		
		ParseTreeWalker functionWalker = new ParseTreeWalker();
		functionWalker.walk(new FunctionCallVerifier(), this.exprCtx);
		
		this.verifyParameters();
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println(TAG);
		this.mapParameters();
		this.mobiFunction.execute();
	}
	
	private void searchFunction() {
		ClassScope classScope = SymbolTable.getInstance().getClassScope(ParserHandler.getInstance().getCurrentClassName());
		this.mobiFunction = classScope.searchFunction(this.functionName);
	}
	
	private void verifyParameters() {
		if(this.exprCtx.arguments() == null || this.exprCtx.arguments().expressionList() == null
				|| this.exprCtx.arguments().expressionList().expression() == null) {
			return;
		}
		
		List<ExpressionContext> exprCtxList = this.exprCtx.arguments().expressionList().expression();
		//map values in parameters
		for(int i = 0; i < exprCtxList.size(); i++) {
			ExpressionContext parameterExprCtx = exprCtxList.get(i);
			this.mobiFunction.verifyParameterByValueAt(parameterExprCtx, i);
		}	
	}
	
	/*
	 * Maps parameters when needed
	 */
	private void mapParameters() {
		if(this.exprCtx.arguments() == null || this.exprCtx.arguments().expressionList() == null
				|| this.exprCtx.arguments().expressionList().expression() == null) {
			return;
		}
		
		List<ExpressionContext> exprCtxList = this.exprCtx.arguments().expressionList().expression();
		
		//map values in parameters
		for(int i = 0; i < exprCtxList.size(); i++) {
			ExpressionContext parameterExprCtx = exprCtxList.get(i);
			
			if(this.mobiFunction.getParameterAt(i).getPrimitiveType() == PrimitiveType.ARRAY) {
				MobiValue mobiValue = VariableSearcher.searchVariable(parameterExprCtx.getText());
				this.mobiFunction.mapArrayAt(mobiValue, i, parameterExprCtx.getText());
			}
			else {
				EvaluationCommand evaluationCommand = new EvaluationCommand(parameterExprCtx);
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
				
				this.mobiFunction.mapParameterByValueAt(evaluationCommand.getResult().toEngineeringString(), i);
			}
		}	
	}
	
	public MobiValue getReturnValue() {
		return this.mobiFunction.getReturnValue();
	}

	@Override
	public boolean isBreakpoint() {
		// TODO Auto-generated method stub
		return isBreakpoint;
	}

}
