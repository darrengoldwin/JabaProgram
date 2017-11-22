/**
 * 
 */
package execution.commands.evaluation;

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
		this.assignedMobiArray = mobiArray;
		this.arrayCreatorCtx = arrayCreatorCtx;
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		ExpressionContext exprCtx = this.arrayCreatorCtx.expression(0);
		
		if(exprCtx != null) {
			EvaluationCommand evaluationCommand = new EvaluationCommand(exprCtx);
			evaluationCommand.execute();
			
			this.assignedMobiArray.initializeSize(evaluationCommand.getResult().intValue());
		}
		
	}

}
