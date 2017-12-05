/**
 * 
 */
package execution.commands.evaluation;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import analyzer.FunctionCallVerifier;
import builder.BuildChecker;
import builder.ErrorRepository;
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
		x = x.substring(1, x.length()-1);
//		ParseTreeWalker functionWalker = new ParseTreeWalker();
//		FunctionCallVerifier f = new FunctionCallVerifier();
//		functionWalker.walk(f,arrayCreatorCtx);
		try {
			Float.parseFloat(x);
			BuildChecker.getInstance().reportCustomError(ErrorRepository.TYPE_MISMATCH, "Array Size Should be an Integer", firstToken.getLine());
			
		}catch(Exception e) {
			
//			MobiValue mobiValue = new MobiValue(0, PrimitiveType.INT);
//			if(f.isFunction()) {
//				TypeChecker typeChecker = new TypeChecker(mobiValue, arrayCreatorCtx);
//				typeChecker.checkType(f.getFunc().getReturnValue().getPrimitiveType(), mobiValue.getPrimitiveType());
//			}else {
//				TypeChecker typeChecker = new TypeChecker(mobiValue, arrayCreatorCtx);
//				typeChecker.verify();
//			}
			
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
