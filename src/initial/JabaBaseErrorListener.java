package initial;
import java.util.BitSet;
import java.util.Collections;
import java.util.List;

import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CommonToken;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

public class JabaBaseErrorListener implements ANTLRErrorListener{

	private String output;
	
	public String getOutput() {
		return output;
	}

	public void setOutput(String output) {
		this.output = output;
	}

	@Override
	public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			String msg, RecognitionException e) {
		
			
		if (msg.contains("extraneous input '<EOF>'"))
			msg ="Missing }";
		else if(msg.contains("expecting {'>', '<', '==', '<=', '>=', '!='}"))
			msg = "Expected Comparison Operator";
//		else if(msg.contains("expecting Identifier"))
//			msg = "invalid identifier name";
		else if(msg.contains("no viable alternative at input"))
			msg = msg.replace("no viable alternative at input", "Not Valid Parameters");
		else if(msg.contains("extraneous input ','"))
			msg = "Too many ','";
		if(msg.contains("Cannot return primitiveType"))
			output +="line "+(line-2)+":"+msg+"\n";
		else
			output += "line "+(line-1)+":"+": "+msg+"\n";

	}

	@Override
	public void reportAmbiguity(Parser arg0, DFA arg1, int arg2, int arg3, boolean arg4, BitSet arg5,
			ATNConfigSet arg6) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reportAttemptingFullContext(Parser arg0, DFA arg1, int arg2, int arg3, BitSet arg4, ATNConfigSet arg5) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reportContextSensitivity(Parser arg0, DFA arg1, int arg2, int arg3, int arg4, ATNConfigSet arg5) {
		// TODO Auto-generated method stub
		
	}
}
