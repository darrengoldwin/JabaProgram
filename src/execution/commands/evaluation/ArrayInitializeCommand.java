/**
 * 
 */
package execution.commands.evaluation;

import org.antlr.v4.runtime.Token;

import builder.BuildChecker;
import builder.ErrorRepository;
import builder.errorcheckers.UndeclaredChecker;
import execution.commands.ICommand;
import initial.JabaParser.ArrayCreatorRestContext;
import initial.JabaParser.ExpressionContext;
import representation.MobiArray;

/**
 * Represents an initialization of an array using new int[x] for example.
 * @author NeilDG
 *
 */
public class ArrayInitializeCommand implements ICommand {
	private final static String TAG = "ArrayInitializeCommand";
	
	private MobiArray assignedMobiArray;
	private ArrayCreatorRestContext arrayCreatorCtx;
	
	public ArrayInitializeCommand(MobiArray mobiArray, ArrayCreatorRestContext arrayCreatorCtx) {
		System.out.println(TAG);
		this.assignedMobiArray = mobiArray;
		this.arrayCreatorCtx = arrayCreatorCtx;
		
		System.out.println("AAA" + arrayCreatorCtx.getText());
		//UndeclaredChecker undeclaredChecker = new UndeclaredChecker(this.arrayCreatorCtx);
		//undeclaredChecker.verify();
		
		Token firstToken = arrayCreatorCtx.getStart();
		
		String x =arrayCreatorCtx.getText();
		System.out.println(x);
		x = x.substring(1, x.length()-1);
		try {
			Float.parseFloat(x);
			BuildChecker.getInstance().reportCustomError(ErrorRepository.TYPE_MISMATCH, "Array Size Should be an Integer", firstToken.getLine());
		}catch(Exception e) {
			
		}
		
		
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		System.out.println(TAG);
		ExpressionContext exprCtx = this.arrayCreatorCtx.expression(0);
		
		if(exprCtx != null) {
			EvaluationCommand evaluationCommand = new EvaluationCommand(exprCtx);
			evaluationCommand.execute();
			
			this.assignedMobiArray.initializeSize(evaluationCommand.getResult().intValue());
		}
		
	}

}
