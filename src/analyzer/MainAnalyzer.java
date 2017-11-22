/**
 * 
 */
package analyzer;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.antlr.v4.runtime.tree.TerminalNode;

import builder.ParserHandler;
import execution.ExecutionManager;
import initial.JabaParser.BlockContext;
import initial.JabaParser.MainDeclarationContext;
import initial.JabaParser.MethodBodyContext;
import scope.ClassScope;
import scope.LocalScope;
import scope.LocalScopeCreator;
import semantics.symboltable.SymbolTable;

/**
 * The entry point for the program. Only one main is allowed.
 * @author NeilDG
 *
 */
public class MainAnalyzer implements ParseTreeListener {

	public MainAnalyzer() {
		
	}
	
	public void analyze(MainDeclarationContext ctx) {
		if(!ExecutionManager.getInstance().hasFoundEntryPoint()) {
			ExecutionManager.getInstance().reportFoundEntryPoint(ParserHandler.getInstance().getCurrentClassName());
			
			//automatically create a local scope for main() whose parent is the class scope
			ClassScope classScope = SymbolTable.getInstance().getClassScope(ParserHandler.getInstance().getCurrentClassName());
			LocalScope localScope = LocalScopeCreator.getInstance().openLocalScope();
			localScope.setParent(classScope);
			classScope.setParentLocalScope(localScope);
			
			ParseTreeWalker treeWalker = new ParseTreeWalker();
			treeWalker.walk(this, ctx);
			
			
		}
		else {
			//Console.log(LogType.DEBUG, "Already found main in " +ExecutionManager.getInstance().getEntryClassName());
		}
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
		if(ctx instanceof MethodBodyContext) {
			BlockContext blockCtx = ((MethodBodyContext) ctx).block();
			
			BlockAnalyzer blockAnalyzer = new BlockAnalyzer();
			blockAnalyzer.analyze(blockCtx);
		}
	}

	@Override
	public void exitEveryRule(ParserRuleContext ctx) {
		// TODO Auto-generated method stub
		
	}
}
