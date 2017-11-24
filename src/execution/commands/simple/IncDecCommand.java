/**
 * 
 */
package execution.commands.simple;

import builder.errorcheckers.ConstChecker;
import execution.commands.ICommand;
import initial.JabaLexer;
import initial.JabaParser.ExpressionContext;
import mapping.IValueMapper;
import mapping.IdentifierMapper;
import representation.MobiValue;
import representation.MobiValue.PrimitiveType;

/**
 * An increment or decrement command
 * @author NeilDG
 *
 */
public class IncDecCommand implements ICommand {

	private ExpressionContext exprCtx;
	private int tokenSign;
	
	public IncDecCommand(ExpressionContext exprCtx, int tokenSign) {
		this.exprCtx = exprCtx;
		this.tokenSign = tokenSign;
		System.out.println(this.exprCtx);
		ConstChecker constChecker = new ConstChecker(this.exprCtx);
		constChecker.verify();
	}
	
	/* (non-Javadoc)
	 * @see com.neildg.mobiprog.execution.commands.ICommand#execute()
	 */
	@Override
	public void execute() {
		//String identifier = this.exprCtx.primary().Identifier().getText();
		//MobiValue mobiValue = MobiValueSearcher.searchMobiValue(identifier);
		
		IValueMapper leftHandMapper = new IdentifierMapper(
				this.exprCtx.getText());
		leftHandMapper.analyze(this.exprCtx);

		MobiValue mobiValue = leftHandMapper.getMobiValue();
		if(!mobiValue.isFinal()) {
			this.performOperation(mobiValue);
		}else
		{
			
		}
		
	}
	
	/*
	 * Attempts to perform an increment/decrement operation
	 */
	private void performOperation(MobiValue mobiValue) {
		if(mobiValue.getPrimitiveType() == PrimitiveType.INT) {
			int value = Integer.valueOf(mobiValue.getValue().toString());
			
			if(this.tokenSign == JabaLexer.INC) {
				value++;
				mobiValue.setValue(String.valueOf(value));
			}
			else if(this.tokenSign == JabaLexer.DEC) {
				value--;
				mobiValue.setValue(String.valueOf(value));
			}
		}
		else if(mobiValue.getPrimitiveType() == PrimitiveType.LONG) {
			long value = Long.valueOf(mobiValue.getValue().toString());
			
			if(this.tokenSign == JabaLexer.INC) {
				value++;
				mobiValue.setValue(String.valueOf(value));
			}
			else if(this.tokenSign == JabaLexer.DEC) {
				value--;
				mobiValue.setValue(String.valueOf(value));
			}
		}
		else if(mobiValue.getPrimitiveType() == PrimitiveType.BYTE) {
			byte value = Byte.valueOf(mobiValue.getValue().toString());
			
			if(this.tokenSign == JabaLexer.INC) {
				value++;
				mobiValue.setValue(String.valueOf(value));
			}
			else if(this.tokenSign == JabaLexer.DEC) {
				value--;
				mobiValue.setValue(String.valueOf(value));
			}
		}
		else if(mobiValue.getPrimitiveType() == PrimitiveType.SHORT) {
			short value = Short.valueOf(mobiValue.getValue().toString());
			
			if(this.tokenSign == JabaLexer.INC) {
				value++;
				mobiValue.setValue(String.valueOf(value));
			}
			else if(this.tokenSign == JabaLexer.DEC) {
				value--;
				mobiValue.setValue(String.valueOf(value));
			}
		}
		else if(mobiValue.getPrimitiveType() == PrimitiveType.FLOAT) {
			float value = Float.valueOf(mobiValue.getValue().toString());
			
			if(this.tokenSign == JabaLexer.INC) {
				value++;
				mobiValue.setValue(String.valueOf(value));
			}
			else if(this.tokenSign == JabaLexer.DEC) {
				value--;
				mobiValue.setValue(String.valueOf(value));
			}
		}
		else if(mobiValue.getPrimitiveType() == PrimitiveType.DOUBLE) {
			double value = Double.valueOf(mobiValue.getValue().toString());
			
			if(this.tokenSign == JabaLexer.INC) {
				value++;
				mobiValue.setValue(String.valueOf(value));
			}
			else if(this.tokenSign == JabaLexer.DEC) {
				value--;
				mobiValue.setValue(String.valueOf(value));
			}
		}
	}

}
