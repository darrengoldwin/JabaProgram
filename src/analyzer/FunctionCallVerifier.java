package analyzer;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.ParserHandler;
import builder.errorcheckers.ParameterMismatchChecker;
import execution.commands.evaluation.EvaluationCommand;
import initial.JabaParser.ExpressionContext;
import representation.MobiFunction;
import scope.ClassScope;
import semantics.symboltable.SymbolTable;

public class FunctionCallVerifier implements ParseTreeListener {
	
	private boolean isFunction;
	private MobiFunction func;
	
	public FunctionCallVerifier() {
		isFunction= false;
	}
	public boolean isFunction() {
		return isFunction;
	}
	public MobiFunction getFunc() {
		return func;
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
				if (EvaluationCommand.isFunctionCall(exprCtx)) {
					
					if(exprCtx.expression(0).Identifier() == null)
						return;
					
					String functionName = exprCtx.expression(0).Identifier().getText();
					
					ClassScope classScope = SymbolTable.getInstance().getClassScope(
							ParserHandler.getInstance().getCurrentClassName());
					MobiFunction mobiFunction = classScope.searchFunction(functionName);
					func = mobiFunction;
					isFunction = true;
					if (exprCtx.arguments() != null) {
						ParameterMismatchChecker paramsMismatchChecker = new ParameterMismatchChecker(mobiFunction, exprCtx.arguments());
						paramsMismatchChecker.verify();
						
					}
				}
			}
		}

		@Override
		public void exitEveryRule(ParserRuleContext ctx) {
			// TODO Auto-generated method stub
			
		}
		
	}