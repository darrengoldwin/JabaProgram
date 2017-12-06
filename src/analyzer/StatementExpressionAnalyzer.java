/**
 * 
 */
package analyzer;

import java.util.List;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.errorcheckers.ThisKeywordChecker;
import execution.ExecutionManager;
import execution.commands.ICommand;
import execution.commands.controlled.ForCommand;
import execution.commands.controlled.IConditionalCommand;
import execution.commands.controlled.IControlledCommand;
import execution.commands.controlled.IfCommand;
import execution.commands.controlled.WhileCommand;
import execution.commands.evaluation.ArrayInitializeCommand;
import execution.commands.evaluation.AssignmentCommand;
import execution.commands.evaluation.EvaluationCommand;
import execution.commands.evaluation.MappingCommand;
import execution.commands.simple.FunctionCallCommand;
import execution.commands.simple.IncDecCommand;
import execution.commands.simple.PrintCommand;
import execution.commands.simple.ReturnCommand;
import execution.commands.simple.ScanCommand;
import initial.GUI;
import initial.JabaLexer;
import initial.JabaParser.ExpressionContext;
import initial.JabaParser.StatementExpressionContext;
import statements.StatementControlOverseer;

/**
 * Analyzes a given expression on the statement level.
 * This does not include field declaration analysis.
 * @author NeilDG
 *
 */
public class StatementExpressionAnalyzer implements ParseTreeListener {

	private ExpressionContext readRightHandExprCtx; //used to avoid mistakenly reading right hand expressions as direct function calls as well.
	
	//TODO: find a way to not rely on tree depth for function calls.
	public final static int FUNCTION_CALL_NO_PARAMS_DEPTH = 13;
	public final static int FUNCTION_CALL_WITH_PARAMS_DEPTH = 14;
	
	public StatementExpressionAnalyzer() {
		
	}
	
	public void analyze(StatementExpressionContext statementExprCtx) {
		ParseTreeWalker treeWalker = new ParseTreeWalker();
		treeWalker.walk(this, statementExprCtx);
	}
	
	public void analyze(ExpressionContext exprCtx) {
		ParseTreeWalker treeWalker = new ParseTreeWalker();
		treeWalker.walk(this, exprCtx);
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
			
			if(isAssignmentExpression(exprCtx)) {
				//Console.log(LogType.DEBUG, "Assignment expr detected: " +exprCtx.getText());
				
				List<ExpressionContext> exprListCtx = exprCtx.expression();
				AssignmentCommand assignmentCommand = new AssignmentCommand(exprListCtx.get(0), exprListCtx.get(1));
				
				this.readRightHandExprCtx = exprListCtx.get(1);
				this.handleStatementExecution(assignmentCommand);
				
			}
			else if(isIncrementExpression(exprCtx)) {
				//Console.log(LogType.DEBUG, "Increment expr detected: " +exprCtx.getText());
				
				List<ExpressionContext> exprListCtx = exprCtx.expression();
				
				IncDecCommand incDecCommand = new IncDecCommand(exprListCtx.get(0) ,JabaLexer.INC);
				this.handleStatementExecution(incDecCommand);
			}
			
			else if(isDecrementExpression(exprCtx)) {
				//Console.log(LogType.DEBUG, "Decrement expr detected: " +exprCtx.getText());
				
				List<ExpressionContext> exprListCtx = exprCtx.expression();
				
				IncDecCommand incDecCommand = new IncDecCommand(exprListCtx.get(0) ,JabaLexer.DEC);
				this.handleStatementExecution(incDecCommand);
				
			}
			
			else if(this.isFunctionCallWithParams(exprCtx)) {
				this.handleFunctionCallWithParams(exprCtx);
			}
			
			else if(this.isFunctionCallWithNoParams(exprCtx)) {
				this.handleFunctionCallWithNoParams(exprCtx);
			}
		}
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		
	}
	
	private void handleStatementExecution(ICommand command) {
		
		Token firstToken = readRightHandExprCtx.getStart();
		firstToken.getLine();
		for(int i: GUI.getInstance().breakpoint) 
			if(firstToken.getLine() == i) {
				if(command.getClass().toString().equals(AssignmentCommand.class.toString()))
					((AssignmentCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(ArrayInitializeCommand.class.toString()))
					((ArrayInitializeCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(EvaluationCommand.class.toString()))
					((EvaluationCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(MappingCommand.class.toString()))
					((MappingCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(FunctionCallCommand.class.toString()))
					((FunctionCallCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(IncDecCommand.class.toString()))
					((IncDecCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(PrintCommand.class.toString()))
					((PrintCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(ReturnCommand.class.toString()))
					((ReturnCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(ScanCommand.class.toString()))
					((ScanCommand) command).isBreakPoint = true;
				else if(command.getClass().toString().equals(WhileCommand.class.toString()))
					((WhileCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(IfCommand.class.toString()))
					((IfCommand) command).isBreakpoint = true;
				else if(command.getClass().toString().equals(ForCommand.class.toString()))
					((ForCommand) command).isBreakpoint = true;
			}
		StatementControlOverseer statementControl = StatementControlOverseer.getInstance();
		
		//add to conditional controlled command
		if(statementControl.isInConditionalCommand()) {
			IConditionalCommand conditionalCommand = (IConditionalCommand) statementControl.getActiveControlledCommand();
			
			if(statementControl.isInPositiveRule()) {
				conditionalCommand.addPositiveCommand(command);
			}
			else {
				conditionalCommand.addNegativeCommand(command);
			}
		}
		
		else if(statementControl.isInControlledCommand()) {
			IControlledCommand controlledCommand = (IControlledCommand) statementControl.getActiveControlledCommand();
			controlledCommand.addCommand(command);
		}
		else {
			
			ExecutionManager.getInstance().addCommand(command);
		}
		
	}
	
	private void handleFunctionCallWithParams(ExpressionContext funcExprCtx) {
		ExpressionContext functionExprCtx = funcExprCtx.expression(0);
		String functionName = functionExprCtx.Identifier().getText();
		
		FunctionCallCommand functionCallCommand = new FunctionCallCommand(functionName, funcExprCtx);
		this.handleStatementExecution(functionCallCommand);
		
		//Console.log(LogType.DEBUG, "Function call with params detected: " +functionName);
	}
	
	private void handleFunctionCallWithNoParams(ExpressionContext funcExprCtx) {
		String functionName = funcExprCtx.Identifier().getText();
		
		FunctionCallCommand functionCallCommand = new FunctionCallCommand(functionName, funcExprCtx);
		this.handleStatementExecution(functionCallCommand);
		
		//Console.log(LogType.DEBUG, "Function call with no params detected: " +functionName);
	}
	
	public static boolean isAssignmentExpression(ExpressionContext exprCtx) {
		List<TerminalNode> tokenList = exprCtx.getTokens(JabaLexer.ASSIGN);
		return (tokenList.size() > 0);
	}
	
	public static boolean isIncrementExpression(ExpressionContext exprCtx) {
		List<TerminalNode> incrementList = exprCtx.getTokens(JabaLexer.INC);
		
		return (incrementList.size() > 0);
	}
	
	public static boolean isDecrementExpression(ExpressionContext exprCtx) {
		List<TerminalNode> decrementList = exprCtx.getTokens(JabaLexer.DEC);
		
		return (decrementList.size() > 0);
	}
	
	private boolean isFunctionCallWithParams(ExpressionContext exprCtx) {
		ExpressionContext firstExprCtx = exprCtx.expression(0);
		
		if(firstExprCtx != null) {
			if(exprCtx != this.readRightHandExprCtx) {
				ThisKeywordChecker thisChecker = new ThisKeywordChecker(firstExprCtx);
				thisChecker.verify();
				
				return (firstExprCtx.Identifier() != null);
			}
		}
		
		return false;
		
	}
	
	private boolean isFunctionCallWithNoParams(ExpressionContext exprCtx) {
		if(exprCtx.depth() == FUNCTION_CALL_NO_PARAMS_DEPTH) {
			ThisKeywordChecker thisChecker = new ThisKeywordChecker(exprCtx);
			thisChecker.verify();
			if(exprCtx.Identifier() != null)
				return true;
		}
		
		return false;
	}
}
