/**
 * 
 */
package builder.errorcheckers;

import builder.BuildChecker;
import builder.ErrorRepository;
import initial.JabaParser.ExpressionContext;

/**
 * @author NeilDG
 *
 */
public class ThisKeywordChecker implements IErrorChecker {
	private final static String TAG = "ThisKeywordChecker";
	
	private ExpressionContext exprCtx;
	private int lineNumber;
	
	public ThisKeywordChecker(ExpressionContext exprCtx) {
		this.exprCtx = exprCtx;
		this.lineNumber = this.exprCtx.getStart().getLine();
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.builder.errorcheckers.IErrorChecker#verify()
	 */
	@Override
	public void verify() {
		if(exprCtx.Identifier() == null && this.exprCtx.primary() == null) {
			BuildChecker.reportCustomError(ErrorRepository.MISSING_THIS_KEYWORD, "", this.exprCtx.getText(), this.lineNumber);
		}
	}

}
