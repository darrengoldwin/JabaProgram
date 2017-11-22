/**
 * 
 */
package searching;

import builder.ParserHandler;
import execution.FunctionTracker;
import representation.MobiFunction;
import representation.MobiValue;
import scope.ClassScope;
import scope.LocalScopeCreator;
import semantics.symboltable.SymbolTable;

/**
 * A utility class to search for a certain variable depending on where the control flow is.
 * @author Patrick
 *
 */
public class VariableSearcher {
	private final static String TAG = "VariableSearcher";
	
	public static MobiValue searchVariable(String identifierString) {
		MobiValue mobiValue = null;
		
		if(FunctionTracker.getInstance().isInsideFunction()) {
			mobiValue = searchVariableInFunction(FunctionTracker.getInstance().getLatestFunction(), identifierString);
		}
		
		if(mobiValue == null) {
			ClassScope classScope = SymbolTable.getInstance().getClassScope(ParserHandler.getInstance().getCurrentClassName());
			mobiValue = searchVariableInClassIncludingLocal(classScope, identifierString);
		}
		
		return mobiValue;
	}
	
	public static MobiValue searchVariableInFunction(MobiFunction mobiFunction, String identifierString) {
		MobiValue mobiValue = null;
		
		if(mobiFunction.hasParameter(identifierString)) {
			mobiValue = mobiFunction.getParameter(identifierString);
		}
		else {
			mobiValue = LocalScopeCreator.searchVariableInLocalIterative(identifierString, mobiFunction.getParentLocalScope());
		}
		
		return mobiValue;
	}
	
	public static MobiValue searchVariableInClassIncludingLocal(ClassScope classScope, String identifierString) {
		return classScope.searchVariableIncludingLocal(identifierString);
	}
	
	public static MobiValue searchVariableInClass(ClassScope classScope, String identifierString) {
		return classScope.searchVariable(identifierString);
	}
	
}
