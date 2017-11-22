package mapping;

import initial.JabaParser.ExpressionContext;
import initial.JabaParser.ParExpressionContext;
import representation.MobiValue;

public interface IValueMapper {

	public abstract void analyze(ExpressionContext exprCtx);
	public abstract void analyze(ParExpressionContext exprCtx);
	public abstract String getOriginalExp();
	public abstract String getModifiedExp();
	public abstract MobiValue getMobiValue();

}