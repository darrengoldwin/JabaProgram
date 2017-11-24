/**
 * 
 */
package execution.commands.evaluation;

import java.math.BigDecimal;
import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.ParserHandler;
import execution.commands.ICommand;
import initial.JabaParser.ExpressionContext;
import representation.MobiFunction;
import representation.MobiValue;
import scope.ClassScope;
import searching.VariableSearcher;
import semantic.util.Expression;
import semantic.util.RecognizedKeywords;
import semantics.symboltable.SymbolTable;

/**
 * A command that evaluates a given expression at runtime.
 * @author Patrick
 *
 */
public class EvaluationCommand implements ICommand, ParseTreeListener {
	private final static String TAG = "MobiProg_EvaluationCommand";
	
	private ExpressionContext parentExprCtx;
	private String modifiedExp;
	private BigDecimal resultValue;
	
	public EvaluationCommand(ExpressionContext exprCtx) {
		this.parentExprCtx = exprCtx;
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println(TAG);
		this.modifiedExp = this.parentExprCtx.getText();
		System.out.println(modifiedExp);

		//catch rules if the value has direct boolean flags
		if(this.modifiedExp.contains(RecognizedKeywords.BOOLEAN_TRUE)) {
			this.resultValue = new BigDecimal(1);
		}
		else if(this.modifiedExp.contains(RecognizedKeywords.BOOLEAN_FALSE)) {
			this.resultValue = new BigDecimal(0);
		}
		else {
			ParseTreeWalker treeWalker = new ParseTreeWalker();
			treeWalker.walk(this, this.parentExprCtx);
			
			Expression evalEx = new Expression(this.modifiedExp);
			//Log.i(TAG,"Modified exp to eval: " +this.modifiedExp);
			this.resultValue = evalEx.eval();
		}
		
	}

	@Override
	public void visitTerminal(TerminalNode node) {

	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub

	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		if (ctx instanceof ExpressionContext) {
			ExpressionContext exprCtx = (ExpressionContext) ctx;
			if (EvaluationCommand.isFunctionCall(exprCtx)) {
				this.evaluateFunctionCall(exprCtx);
			}

			else if (EvaluationCommand.isVariableOrConst(exprCtx)) {
				this.evaluateVariable(exprCtx);
			}
		}
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {

	}

	public static boolean isFunctionCall(ExpressionContext exprCtx) {
		if (exprCtx.arguments() != null) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean isVariableOrConst(ExpressionContext exprCtx) {
		if (exprCtx.primary() != null && exprCtx.primary().Identifier() != null) {
			return true;
		} else {
			return false;
		}
	}

	private void evaluateFunctionCall(ExpressionContext exprCtx) {
		String functionName = exprCtx.expression(0).Identifier().getText();

		ClassScope classScope = SymbolTable.getInstance().getClassScope(
				ParserHandler.getInstance().getCurrentClassName());
		MobiFunction mobiFunction = classScope.searchFunction(functionName);

		if (exprCtx.arguments().expressionList() != null) {
			List<ExpressionContext> exprCtxList = exprCtx.arguments()
					.expressionList().expression();

			for (int i = 0; i < exprCtxList.size(); i++) {
				ExpressionContext parameterExprCtx = exprCtxList.get(i);

				EvaluationCommand evaluationCommand = new EvaluationCommand(parameterExprCtx);
				evaluationCommand.execute();

				mobiFunction.mapParameterByValueAt(evaluationCommand.getResult().toEngineeringString(), i);
			}
		}

		mobiFunction.execute();

		//Log.i(TAG, "Before modified EXP function call: " +this.modifiedExp);
		this.modifiedExp = this.modifiedExp.replace(exprCtx.getText(),
				mobiFunction.getReturnValue().getValue().toString());
		//Log.i(TAG, "After modified EXP function call: " +this.modifiedExp);

	}

	private void evaluateVariable(ExpressionContext exprCtx) {
		MobiValue mobiValue = VariableSearcher
				.searchVariable(exprCtx.getText());

		this.modifiedExp = this.modifiedExp.replaceFirst(exprCtx.getText(),
				mobiValue.getValue().toString());
	}

	/*
	 * Returns the result
	 */
	public BigDecimal getResult() {
		return this.resultValue;
	}
}
