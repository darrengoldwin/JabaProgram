package execution.commands.simple;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.errorcheckers.UndeclaredChecker;
import console.Output;
import execution.commands.ICommand;
import execution.commands.evaluation.EvaluationCommand;
import initial.JabaParser.ExpressionContext;
import initial.JabaParser.LiteralContext;
import initial.JabaParser.PrimaryContext;
import representation.MobiArray;
import representation.MobiValue;
import representation.MobiValue.PrimitiveType;
import representation.MobiValueSearcher;
import semantic.util.StringUtils;

public class PrintCommand implements ICommand, ParseTreeListener {

	private final static String TAG = "Mobi_PrintCommand";
	
	private ExpressionContext expressionCtx;
	
	private String statementToPrint = "";
	private boolean complexExpr = false;
	private boolean arrayAccess = false;
	
	public PrintCommand(ExpressionContext expressionCtx) {
		this.expressionCtx = expressionCtx;
		
		UndeclaredChecker undeclaredChecker = new UndeclaredChecker(this.expressionCtx);
		undeclaredChecker.verify();
	}
	
	@Override
	public void execute() {
		System.out.println(TAG);
		ParseTreeWalker treeWalker = new ParseTreeWalker();
		treeWalker.walk(this, this.expressionCtx);
		
		Output.getInstance().print(this.statementToPrint);
		this.statementToPrint = ""; //reset statement to print afterwards
	}

	@Override
	public void visitTerminal(TerminalNode node) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void visitErrorNode(ErrorNode node) {
		
	}

	@Override
	public void enterEveryRule(ParserRuleContext ctx) {
		if(ctx instanceof LiteralContext) {
			LiteralContext literalCtx = (LiteralContext) ctx;
			
			if(literalCtx.StringLiteral() != null) {
				String quotedString = literalCtx.StringLiteral().getText(); 
				
				this.statementToPrint += StringUtils.removeQuotes(quotedString);
			}
			/*else if(literalCtx.IntegerLiteral() != null) {
				int value = Integer.parseInt(literalCtx.IntegerLiteral().getText());
				this.statementToPrint += value;
			}
			
			else if(literalCtx.FloatingPointLiteral() != null) {
				float value = Float.parseFloat(literalCtx.FloatingPointLiteral().getText());
				this.statementToPrint += value;
			}
			
			else if(literalCtx.BooleanLiteral() != null) {
				this.statementToPrint += literalCtx.BooleanLiteral().getText();
			}
			
			else if(literalCtx.CharacterLiteral() != null) {
				this.statementToPrint += literalCtx.CharacterLiteral().getText();
			}*/
		}
		
		else if(ctx instanceof PrimaryContext) {
			PrimaryContext primaryCtx = (PrimaryContext) ctx;
			
			if(primaryCtx.expression() != null) {
				ExpressionContext exprCtx = primaryCtx.expression();
				this.complexExpr = true;
				//Console.log(LogType.DEBUG, "Complex expression detected: " +exprCtx.getText());

				EvaluationCommand evaluationCommand = new EvaluationCommand(exprCtx);
				evaluationCommand.execute();
				
				this.statementToPrint += evaluationCommand.getResult().toEngineeringString();
			}
			
			else if(primaryCtx.Identifier() != null && this.complexExpr == false) {
				String identifier = primaryCtx.getText();
				
				MobiValue value = MobiValueSearcher.searchMobiValue(identifier);
				if(value.getPrimitiveType() == PrimitiveType.ARRAY) {
					this.arrayAccess = true;
					this.evaluateArrayPrint(value, primaryCtx);
				}
				else if(this.arrayAccess == false) {
					this.statementToPrint += value.getValue();
				}
				
				
			}
		}
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		
	}
	
	public String getStatementToPrint() {
		return this.statementToPrint;
	}
	
	private void evaluateArrayPrint(MobiValue mobiValue, PrimaryContext primaryCtx) {
		
		//move up and determine expression contexts
		ExpressionContext parentExprCtx = (ExpressionContext) primaryCtx.getParent().getParent();
		ExpressionContext arrayIndexExprCtx = parentExprCtx.expression(1);
		
		EvaluationCommand evaluationCommand = new EvaluationCommand(arrayIndexExprCtx);
		evaluationCommand.execute();
		
		MobiArray mobiArray = (MobiArray) mobiValue.getValue();
		MobiValue arrayMobiValue = mobiArray.getValueAt(evaluationCommand.getResult().intValue());
		
		this.statementToPrint += arrayMobiValue.getValue().toString();
	}
	
	

}
