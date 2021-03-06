
package analyzer;

import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.errorcheckers.UndeclaredChecker;
import execution.ExecutionManager;
import execution.commands.controlled.DoWhileCommand;
import execution.commands.controlled.ForCommand;
import execution.commands.controlled.IConditionalCommand;
import execution.commands.controlled.IControlledCommand;
import execution.commands.controlled.IfCommand;
import execution.commands.controlled.WhileCommand;
import execution.commands.evaluation.MappingCommand;
import execution.commands.simple.PrintCommand;
import execution.commands.simple.ReturnCommand;
import execution.commands.simple.ScanCommand;
import initial.GUI;
import initial.JabaLexer;
import initial.JabaParser.BlockContext;
import initial.JabaParser.ExpressionContext;
import initial.JabaParser.StatementContext;
import scope.LocalScopeCreator;
import statements.StatementControlOverseer;

/**
 * A bridge for statement listener
 * @author Patrick
 *
 */
public class StatementAnalyzer {

	public StatementAnalyzer() {
		
	}
	
	public void analyze(StatementContext ctx) {
		//print statementAnalyzer
		
		if(ctx.PRINT() != null) {
			this.handlePrintStatement(ctx);
		}
		else if(ctx.SCAN() != null) {
			this.handleScanStatement(ctx);
		}
		//an expression
		else if(ctx.statementExpression() != null) {
			StatementExpressionAnalyzer expressionAnalyzer = new StatementExpressionAnalyzer();
			expressionAnalyzer.analyze(ctx.statementExpression());
		}
		
		//a block statement
		else if(ctx.block() != null) {
			BlockContext blockCtx = ctx.block();
			
			BlockAnalyzer blockAnalyzer = new BlockAnalyzer();
			blockAnalyzer.analyze(blockCtx);
		}
		
		//an IF statement
		else if(isIFStatement(ctx)) {
			//Console.log("Par expression: " +ctx.parExpression().getText());
			
			StatementContext statementCtx = ctx.statement(0);
			
			//Console.log(LogType.DEBUG, "IF statement: " +statementCtx.getText());
			
			IfCommand ifCommand = new IfCommand(ctx.parExpression());
			StatementControlOverseer.getInstance().openConditionalCommand(ifCommand);
			
			StatementAnalyzer statementAnalyzer = new StatementAnalyzer();
			statementAnalyzer.analyze(statementCtx);
			
			StatementControlOverseer.getInstance().reportExitPositiveRule();
			
			//check if there is an ELSE statement
			if(isELSEStatement(ctx)) {
				statementCtx = ctx.statement(1);
				
				//Console.log(LogType.DEBUG, "ELSE statement: " +statementCtx.getText());
				statementAnalyzer.analyze(statementCtx);
			}
			
			StatementControlOverseer.getInstance().compileControlledCommand();
		}
		
		else if(isWHILEStatement(ctx)) {
			//Console.log(LogType.DEBUG, "While par expression: " +ctx.parExpression().getText());
			
			StatementContext statementCtx = ctx.statement(0);
			
			WhileCommand whileCommand = new WhileCommand(ctx.parExpression());
			StatementControlOverseer.getInstance().openControlledCommand(whileCommand);
			
			StatementAnalyzer statementAnalyzer = new StatementAnalyzer();
			statementAnalyzer.analyze(statementCtx);
			
			StatementControlOverseer.getInstance().compileControlledCommand();
			//Console.log(LogType.DEBUG, "End of WHILE expression: " +ctx.parExpression().getText());
		}
		
		else if(isDOWHILEStatement(ctx)) {
			//Console.log(LogType.DEBUG, "Do while expression: " +ctx.parExpression().getText());
			
			StatementContext statementCtx = ctx.statement(0);
			
			DoWhileCommand doWhileCommand = new DoWhileCommand(ctx.parExpression());
			StatementControlOverseer.getInstance().openControlledCommand(doWhileCommand);
			
			StatementAnalyzer statementAnalyzer = new StatementAnalyzer();
			statementAnalyzer.analyze(statementCtx);
			
			StatementControlOverseer.getInstance().compileControlledCommand();
			//Console.log(LogType.DEBUG, "End of DO-WHILE expression: " +ctx.parExpression().getText());
		}
		
		else if(isFORStatement(ctx)) {
			//Console.log(LogType.DEBUG, "FOR expression: " +ctx.forControl().getText());
			
			LocalScopeCreator.getInstance().openLocalScope();
			
			ForControlAnalyzer forControlAnalyzer = new ForControlAnalyzer();
			forControlAnalyzer.analyze(ctx.forControl());
			
			ForCommand forCommand = new ForCommand(forControlAnalyzer.getLocalVarDecContext(), forControlAnalyzer.getExprContext(), forControlAnalyzer.getUpdateCommand());
			StatementControlOverseer.getInstance().openControlledCommand(forCommand);
			
			StatementContext statementCtx = ctx.statement(0);
			StatementAnalyzer statementAnalyzer = new StatementAnalyzer();
			statementAnalyzer.analyze(statementCtx);
			
			StatementControlOverseer.getInstance().compileControlledCommand();
			
			LocalScopeCreator.getInstance().closeLocalScope();
			//Console.log(LogType.DEBUG, "End of FOR loop");
		}
		
		else if(isRETURNStatement(ctx) && ExecutionManager.getInstance().isInFunctionExecution()) {
			//Console.log(LogType.DEBUG, "Detected return expression: " +ctx.expression(0).getText());
			this.handleReturnStatement(ctx.expression(0));
		}
	}
	
	private void handlePrintStatement(StatementContext ctx) {
		
		PrintCommand printCommand = new PrintCommand(ctx.expression(0));
		
		Token firstToken = ctx.getStart();
		firstToken.getLine();
		for(int i: GUI.getInstance().breakpoint) 
			if(firstToken.getLine() == i)
				printCommand.isBreakpoint = true;
		
		StatementControlOverseer statementControl = StatementControlOverseer.getInstance();
		//add to conditional controlled command
		if(statementControl.isInConditionalCommand()) {
			IConditionalCommand conditionalCommand = (IConditionalCommand) statementControl.getActiveControlledCommand();
			
			if(statementControl.isInPositiveRule()) {
				conditionalCommand.addPositiveCommand(printCommand);
			}
			else {
				conditionalCommand.addNegativeCommand(printCommand);
			}
		}
		
		else if(statementControl.isInControlledCommand()) {
			IControlledCommand controlledCommand = (IControlledCommand) statementControl.getActiveControlledCommand();
			controlledCommand.addCommand(printCommand);
		}
		else {
			
			ExecutionManager.getInstance().addCommand(printCommand);
		}
		
	}
	
	private void handleScanStatement(StatementContext ctx) {
		System.out.println(ctx.StringLiteral().getText());
		ScanCommand scanCommand = new ScanCommand(ctx.StringLiteral().getText(), ctx.Identifier().getText());
		UndeclaredChecker.verifyVarOrConstForScan(ctx.Identifier().getText(), ctx);
		Token firstToken = ctx.getStart();
		firstToken.getLine();
		for(int i: GUI.getInstance().breakpoint) 
			if(firstToken.getLine() == i)
				scanCommand.isBreakPoint = true;
		StatementControlOverseer statementControl = StatementControlOverseer.getInstance();
		//add to conditional controlled command
		if(statementControl.isInConditionalCommand()) {
			IConditionalCommand conditionalCommand = (IConditionalCommand) statementControl.getActiveControlledCommand();
			
			if(statementControl.isInPositiveRule()) {
				conditionalCommand.addPositiveCommand(scanCommand);
			}
			else {
				conditionalCommand.addNegativeCommand(scanCommand);
			}
		}
		
		else if(statementControl.isInControlledCommand()) {
			IControlledCommand controlledCommand = (IControlledCommand) statementControl.getActiveControlledCommand();
			controlledCommand.addCommand(scanCommand);
		}
		else {
			
			ExecutionManager.getInstance().addCommand(scanCommand);
		}
		
	}
	
	private void handleReturnStatement(ExpressionContext exprCtx) {
		ReturnCommand returnCommand = new ReturnCommand(exprCtx, ExecutionManager.getInstance().getCurrentFunction());
		Token firstToken = exprCtx.getStart();
		firstToken.getLine();
		for(int i: GUI.getInstance().breakpoint) 
			if(firstToken.getLine() == i)
				returnCommand.isBreakpoint = true;
		StatementControlOverseer statementControl = StatementControlOverseer.getInstance();
		
		if(statementControl.isInConditionalCommand()) {
			IConditionalCommand conditionalCommand = (IConditionalCommand) statementControl.getActiveControlledCommand();
			
			if(statementControl.isInPositiveRule()) {
				conditionalCommand.addPositiveCommand(returnCommand);
			}
			else {
				String functionName = ExecutionManager.getInstance().getCurrentFunction().getFunctionName();
				conditionalCommand.addNegativeCommand(returnCommand);
			}
		}
		
		else if(statementControl.isInControlledCommand()) {
			IControlledCommand controlledCommand = (IControlledCommand) statementControl.getActiveControlledCommand();
			controlledCommand.addCommand(returnCommand);
		}
		else {
			
			ExecutionManager.getInstance().addCommand(returnCommand);
		}
		
	}
	
	public static boolean isIFStatement(StatementContext ctx) {
		List<TerminalNode> tokenList = ctx.getTokens(JabaLexer.IF);
		
		return (tokenList.size() != 0);
	}
	
	public static boolean isELSEStatement(StatementContext ctx) {
		List<TerminalNode> tokenList = ctx.getTokens(JabaLexer.ELSE);
		
		return (tokenList.size() != 0);
	}
	
	public static boolean isWHILEStatement(StatementContext ctx) {
		List<TerminalNode> whileTokenList = ctx.getTokens(JabaLexer.WHILE);
		List<TerminalNode> doTokenList = ctx.getTokens(JabaLexer.DO);
		
		return (whileTokenList.size() != 0 && doTokenList.size() == 0);
	}
	
	public static boolean isDOWHILEStatement(StatementContext ctx) {
		List<TerminalNode> whileTokenList = ctx.getTokens(JabaLexer.WHILE);
		List<TerminalNode> doTokenList = ctx.getTokens(JabaLexer.DO);
		
		return (whileTokenList.size() != 0 && doTokenList.size() != 0);
	}
	
	public static boolean isFORStatement(StatementContext ctx) {
		List<TerminalNode> forTokenList = ctx.getTokens(JabaLexer.FOR);
		
		return (forTokenList.size() != 0);
	}
	
	public static boolean isRETURNStatement(StatementContext ctx) {
		List<TerminalNode> returnTokenList = ctx.getTokens(JabaLexer.RETURN);
		
		return (returnTokenList.size() != 0);
	}
}
