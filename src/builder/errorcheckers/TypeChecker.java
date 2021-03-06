/**
 * 
 */
package builder.errorcheckers;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.BuildChecker;
import builder.ErrorRepository;
import initial.JabaParser.ArrayCreatorRestContext;
import initial.JabaParser.ArrayInitializerContext;
import initial.JabaParser.ExpressionContext;
import initial.JabaParser.LiteralContext;
import representation.MobiValue;
import representation.MobiValue.PrimitiveType;

/**
 * Handles all the type checking
 * 
 * @author NeilDG
 *
 */
public class TypeChecker implements IErrorChecker, ParseTreeListener {
	private final static String TAG = "MobiProg_TypeChecker";

	private MobiValue mobiValue;
	private ExpressionContext exprCtx;
	private ArrayCreatorRestContext arrCtx;
	private int lineNumber;

	public TypeChecker(MobiValue assignmentMobiValue, ExpressionContext exprCtx) {
		this.mobiValue = assignmentMobiValue;
		this.exprCtx = exprCtx;

		Token firstToken = exprCtx.getStart();
		this.lineNumber = firstToken.getLine();
	}

	public TypeChecker(MobiValue assignmentMobiValue, ArrayCreatorRestContext arrCtx) {
		this.mobiValue = assignmentMobiValue;
		this.arrCtx = arrCtx;

		Token firstToken = exprCtx.getStart();
		this.lineNumber = firstToken.getLine();
	}

	@Override
	public void verify() {
		ParseTreeWalker treeWalker = new ParseTreeWalker();
		if(arrCtx != null)
			treeWalker.walk(this, this.arrCtx);
		else
			treeWalker.walk(this, this.exprCtx);
	}
	

	public static boolean isNumeric(String str) {
		return str.matches("-?\\d+(\\.\\d+)?"); // match a number with optional '-' and decimal.
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

		if (ctx instanceof LiteralContext) {

			if (this.mobiValue == null) {

				return;
			}
			LiteralContext literalCtx = (LiteralContext) ctx;
			String expressionString = literalCtx.getText();
			
			if (this.mobiValue.getPrimitiveType() == PrimitiveType.ARRAY) {

			} else if (this.mobiValue.getPrimitiveType() == PrimitiveType.BOOLEAN) {
				if (literalCtx.BooleanLiteral() == null) {
					String additionalMessage = "Expected boolean.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
				}
			} else if (this.mobiValue.getPrimitiveType() == PrimitiveType.INT) {
				if (literalCtx.IntegerLiteral() == null) {
					String additionalMessage = "Expected int.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
				}
			} else if (this.mobiValue.getPrimitiveType() == PrimitiveType.FLOAT
					|| this.mobiValue.getPrimitiveType() == PrimitiveType.DOUBLE) {
				if (literalCtx.FloatingPointLiteral() == null) {
					String additionalMessage = "Expected floating point or double.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
				}
			}

			else if (this.mobiValue.getPrimitiveType() == PrimitiveType.STRING) {
				if (expressionString.charAt(0) != '\"'
						&& expressionString.charAt(expressionString.length() - 1) != '\"') {
					String additionalMessage = "Expected string.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
				}

				else if (literalCtx.StringLiteral() == null) {
					String additionalMessage = "Expected string.";
					BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
				}
			}
		}
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub

	}

	public void checkType(PrimitiveType p, PrimitiveType p2) {

		if (p == PrimitiveType.ARRAY) {

		} else if (p == PrimitiveType.BOOLEAN) {
			if (p2 != PrimitiveType.BOOLEAN) {
				String additionalMessage = "Expected boolean..";
				BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
			}
		} else if (p == PrimitiveType.INT) {
			if (p2 != PrimitiveType.INT) {
				String additionalMessage = "Expected int..";
				BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
			}
		} else if (p == PrimitiveType.FLOAT || p == PrimitiveType.DOUBLE) {
			if (p2 != PrimitiveType.FLOAT || p2 != PrimitiveType.DOUBLE) {
				String additionalMessage = "Expected floating point or double..";
				BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
			}
		}
		else if (p == PrimitiveType.STRING) {
			if (p2 != PrimitiveType.STRING) {
				String additionalMessage = "Expected string..";
				BuildChecker.reportCustomError(ErrorRepository.TYPE_MISMATCH, additionalMessage, this.lineNumber);
			}
		}

	}
}
