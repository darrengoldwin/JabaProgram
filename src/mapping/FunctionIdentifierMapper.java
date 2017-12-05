/**
 * 
 */
package mapping;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.ParserHandler;
import initial.JabaParser.ExpressionContext;
import initial.JabaParser.ParExpressionContext;
import initial.JabaParser.PrimaryContext;
import representation.MobiFunction;
import representation.MobiValue;
import scope.ClassScope;
import scope.LocalScope;
import scope.LocalScopeCreator;
import semantics.symboltable.SymbolTable;

/**
 * Maps an identifier to a given value found in the function level. First, we search the mapped parameters if a variable already exists.
 * Then we search its parent local scope using depth-first search.
 * @author NeilDG
 *
 */
public class FunctionIdentifierMapper implements ParseTreeListener, IValueMapper {
	private final static String TAG = "MobiProg_FunctionIdentifierMapper";
	
	private String originalExp = null;
	private String modifiedExp = null;
	
	private MobiFunction assignedFunction;
	private MobiValue mobiValue;
	private LocalScope functionLocalScope;
	
	public FunctionIdentifierMapper(String originalExp, MobiFunction assignedFunction) {
		this.originalExp = originalExp;
		this.modifiedExp = originalExp;
		this.assignedFunction = assignedFunction;
		this.functionLocalScope = assignedFunction.getParentLocalScope();
	}
	
	public void analyze(ExpressionContext exprCtx) {
		ParseTreeWalker treeWalker = new ParseTreeWalker();
		treeWalker.walk(this, exprCtx);
	}
	
	public void analyze(ParExpressionContext exprCtx) {
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		if(ctx instanceof PrimaryContext) {
			PrimaryContext primaryCtx = (PrimaryContext) ctx;
			
			if(primaryCtx.Identifier() != null) {
				String variableKey = primaryCtx.getText();
				
				this.searchVariable(variableKey);
			}
		}
	}
	
	private void searchVariable(String identifierString) {
		if(this.assignedFunction.hasParameter(identifierString)) {
			System.out.println(this.modifiedExp);
			System.out.println(this.assignedFunction.getParameter(identifierString).getValue());
			this.modifiedExp = this.modifiedExp.replace(identifierString, this.assignedFunction.getParameter(identifierString).getValue().toString());
		}
		else {
			this.mobiValue = LocalScopeCreator.searchVariableInLocalIterative(identifierString, this.functionLocalScope);
			
			if(this.mobiValue != null) {
				this.modifiedExp = this.modifiedExp.replace(identifierString, this.mobiValue.getValue().toString());
			}
			else {
				ClassScope classScope = SymbolTable.getInstance().getClassScope(ParserHandler.getInstance().getCurrentClassName());
				this.mobiValue = classScope.searchVariableIncludingLocal(identifierString);
				
				//Console.log("Variable in global scope: " +this.mobiValue.getValue());
				this.modifiedExp = this.modifiedExp.replace(identifierString, this.mobiValue.getValue().toString());
			}
		}
	}
	
	@Override
	public MobiValue getMobiValue() {
		return this.mobiValue;
	}
	
	@Override
	public String getOriginalExp() {
		return this.originalExp;
	}
	
	@Override
	public String getModifiedExp() {
		return this.modifiedExp;
	}
}
