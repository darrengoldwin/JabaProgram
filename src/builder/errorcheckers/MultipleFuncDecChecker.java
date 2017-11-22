/**
 * 
 */
package builder.errorcheckers;

import org.antlr.v4.runtime.Token;

import builder.BuildChecker;
import builder.ErrorRepository;
import builder.ParserHandler;
import initial.JabaParser.MethodDeclarationContext;
import representation.MobiFunction;
import scope.ClassScope;
import semantics.symboltable.SymbolTable;

/**
 * Checks for duplicate function declarations
 * @author NeilDG
 *
 */
public class MultipleFuncDecChecker implements IErrorChecker {
	private final static String TAG = "MobiProg_MultipleFuncDecChecker";
	
	private MethodDeclarationContext methodDecCtx;
	private int lineNumber;
	
	public MultipleFuncDecChecker(MethodDeclarationContext methodDecCtx) {
		this.methodDecCtx = methodDecCtx;
		
		Token firstToken = methodDecCtx.getStart();
		this.lineNumber = firstToken.getLine();
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.builder.errorcheckers.IErrorChecker#verify()
	 */
	@Override
	public void verify() {
		this.verifyFunctionCall(this.methodDecCtx.Identifier().getText());
	}
	
	private void verifyFunctionCall(String identifierString) {

		ClassScope classScope = SymbolTable.getInstance().getClassScope(
				ParserHandler.getInstance().getCurrentClassName());
		MobiFunction mobiFunction = classScope.searchFunction(identifierString);
		
		if(mobiFunction != null) {
			BuildChecker.reportCustomError(ErrorRepository.MULTIPLE_FUNCTION, "", identifierString, this.lineNumber);
		}
	}

}
