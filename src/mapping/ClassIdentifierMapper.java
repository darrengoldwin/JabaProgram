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
import representation.MobiValue;
import scope.ClassScope;
import semantics.symboltable.SymbolTable;

/**
 * Maps an identifier to a given value found in the symbol table manager in the class level.
 * @author NeilDG
 *
 */
public class ClassIdentifierMapper implements ParseTreeListener, IValueMapper {
	
	private MobiValue mobiValue;
	private String originalExp = null;
	private String modifiedExp = null;
	
	public ClassIdentifierMapper(String originalExp) {
		this.originalExp = originalExp;
		this.modifiedExp = originalExp;
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.semantics.mapping.IValueMapper#analyze(com.neildg.mobiprog.generatedexp.JavaParser.ExpressionContext)
	 */
	@Override
	public void analyze(ExpressionContext exprCtx) {
		ParseTreeWalker treeWalker = new ParseTreeWalker();
		treeWalker.walk(this, exprCtx);
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.semantics.mapping.IValueMapper#analyze(com.neildg.mobiprog.generatedexp.JavaParser.ParExpressionContext)
	 */
	@Override
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
				ClassScope classScope = SymbolTable.getInstance().getClassScope(ParserHandler.getInstance().getCurrentClassName());
				
				this.mobiValue = classScope.searchVariableIncludingLocal(variableKey);
				this.modifiedExp = this.modifiedExp.replace(variableKey, this.mobiValue.getValue().toString());
			}
		}
	}
	
	@Override
	public MobiValue getMobiValue() {
		return this.mobiValue;
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.semantics.mapping.IValueMapper#getOriginalExp()
	 */
	@Override
	public String getOriginalExp() {
		return this.originalExp;
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.semantics.mapping.IValueMapper#getModifiedExp()
	 */
	@Override
	public String getModifiedExp() {
		return this.modifiedExp;
	}

}
