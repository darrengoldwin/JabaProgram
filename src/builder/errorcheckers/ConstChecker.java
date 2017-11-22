package builder.errorcheckers;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.BuildChecker;
import builder.ErrorRepository;
import builder.ParserHandler;
import execution.ExecutionManager;
import execution.commands.evaluation.EvaluationCommand;
import initial.JabaParser.ExpressionContext;
import representation.MobiFunction;
import representation.MobiValue;
import scope.ClassScope;
import searching.VariableSearcher;
import semantics.symboltable.SymbolTable;

public class ConstChecker implements IErrorChecker, ParseTreeListener {
	private final static String TAG = "MobiProg_ConstChecker";
	
	private ExpressionContext exprCtx;
	private int lineNumber;
	
	public ConstChecker(ExpressionContext exprCtx) {
		this.exprCtx = exprCtx;
		
		Token firstToken = this.exprCtx.getStart();
		this.lineNumber = firstToken.getLine();
	}
	
	@Override
	public void verify() {
		ParseTreeWalker treeWalker = new ParseTreeWalker();
		treeWalker.walk(this, this.exprCtx);
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		if(ctx instanceof ExpressionContext) {
			ExpressionContext exprCtx = (ExpressionContext) ctx;
			if(EvaluationCommand.isVariableOrConst(exprCtx)) {
				this.verifyVariableOrConst(exprCtx);
			}
		}
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		
	}
	
	private void verifyVariableOrConst(ExpressionContext varExprCtx) {
		MobiValue mobiValue = null;
		
		if(ExecutionManager.getInstance().isInFunctionExecution()) {
			MobiFunction mobiFunction = ExecutionManager.getInstance().getCurrentFunction();
			mobiValue = VariableSearcher.searchVariableInFunction(mobiFunction, varExprCtx.primary().Identifier().getText());
		}
		
		//if after function finding, mobi value is still null, search class
		if(mobiValue == null) {
			ClassScope classScope = SymbolTable.getInstance().getClassScope(ParserHandler.getInstance().getCurrentClassName());
			mobiValue = VariableSearcher.searchVariableInClassIncludingLocal(classScope, varExprCtx.primary().Identifier().getText());
		}
		
		if(mobiValue != null && mobiValue.isFinal()) {
			BuildChecker.reportCustomError(ErrorRepository.CONST_REASSIGNMENT, "", varExprCtx.getText(), this.lineNumber);
		}
	}

}
