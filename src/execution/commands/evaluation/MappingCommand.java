/**
 * 
 */
package execution.commands.evaluation;

import org.antlr.v4.runtime.tree.ParseTreeWalker;

import analyzer.FunctionCallVerifier;
import builder.errorcheckers.TypeChecker;
import builder.errorcheckers.UndeclaredChecker;
import execution.ExecutionManager;
import execution.commands.ICommand;
import initial.JabaParser.ExpressionContext;
import representation.MobiValue;
import searching.VariableSearcher;
import semantic.util.AssignmentUtils;

/**
 * A mapping command that evaluates a given expression context then maps
 * its corresponding value. Has an identifier string that assigns the value to it.
 * This is different from assignment command. This one is used for any variable initialization.
 * 
 * @author Patrick
 *
 */
public class MappingCommand implements ICommand {
	private final static String TAG = "MobiProg_MappingCommand";
	
	private String identifierString;
	private ExpressionContext parentExprCtx;
	
	private String modifiedExp;
	
	public MappingCommand(String identifierString, ExpressionContext exprCtx) {
		System.out.println(TAG);
		
		this.identifierString = identifierString;
		this.parentExprCtx = exprCtx;
		
		UndeclaredChecker undeclaredChecker = new UndeclaredChecker(this.parentExprCtx);
		undeclaredChecker.verify();
		
		ParseTreeWalker functionWalker = new ParseTreeWalker();
		FunctionCallVerifier f = new FunctionCallVerifier();
		functionWalker.walk(f, this.parentExprCtx);
		
		MobiValue mobiValue;
		if(ExecutionManager.getInstance().isInFunctionExecution()) {
			mobiValue = VariableSearcher.searchVariableInFunction(ExecutionManager.getInstance().getCurrentFunction(), identifierString);
		}
		else {
			mobiValue = VariableSearcher.searchVariable(identifierString);
		}
		
		if(f.isFunction()) {
			TypeChecker typeChecker = new TypeChecker(mobiValue, this.parentExprCtx);
			typeChecker.checkType(f.getFunc().getReturnValue().getPrimitiveType(), mobiValue.getPrimitiveType());
		}else {
			TypeChecker typeChecker = new TypeChecker(mobiValue, this.parentExprCtx);
			typeChecker.verify();
		}
		
	}
	
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println(TAG);
		this.modifiedExp = this.parentExprCtx.getText();
		
		System.out.println(this.parentExprCtx.getText());
		EvaluationCommand evaluationCommand = new EvaluationCommand(this.parentExprCtx);
		evaluationCommand.execute();
		MobiValue mobiValue = VariableSearcher.searchVariable(this.identifierString);
		AssignmentUtils.assignAppropriateValue(mobiValue, evaluationCommand.getResult());
	}
	
	/*
	 * Returns the modified exp, with mapped values.
	 */
	public String getModifiedExp() {
		return this.modifiedExp;
	}


	public String getIdentifierString() {
		// TODO Auto-generated method stub
		return this.identifierString;
	}
}
