/**
 * 
 */
package analyzer;

import java.util.List;

import initial.JabaParser.BlockContext;
import initial.JabaParser.BlockStatementContext;
import initial.JabaParser.LocalVariableDeclarationStatementContext;
import initial.JabaParser.StatementContext;
import scope.LocalScopeCreator;

/**
 * Analyzes a statement block
 * @author NeilDG
 *
 */
public class BlockAnalyzer {

	public BlockAnalyzer() {
		LocalScopeCreator.getInstance().openLocalScope();
	}
	
	public void analyze(BlockContext ctx) {
		
		List<BlockStatementContext> blockListCtx = ctx.blockStatement();
		
		for(BlockStatementContext blockStatementCtx : blockListCtx) {
			if(blockStatementCtx.statement() != null) {
				StatementContext statementCtx = blockStatementCtx.statement();
				StatementAnalyzer statementAnalyzer = new StatementAnalyzer();
				statementAnalyzer.analyze(statementCtx);
			}
			else if(blockStatementCtx.localVariableDeclarationStatement() != null) {
				LocalVariableDeclarationStatementContext localVarDecStatementCtx = blockStatementCtx.localVariableDeclarationStatement();
				
				LocalVariableAnalyzer localVarAnalyzer = new LocalVariableAnalyzer();
				localVarAnalyzer.analyze(localVarDecStatementCtx.localVariableDeclaration());
			}
		}
		
		LocalScopeCreator.getInstance().closeLocalScope();
	}
}
