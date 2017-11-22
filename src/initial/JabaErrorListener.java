package initial;
import org.antlr.v4.runtime.ANTLRErrorListener;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;
import org.antlr.v4.runtime.atn.ATNConfigSet;
import org.antlr.v4.runtime.dfa.DFA;

import java.util.BitSet;

public class JabaErrorListener implements ANTLRErrorListener {
	private int errors;

	 @Override
     public void syntaxError(Recognizer<?, ?> recognizer, Object o, int i, int i1, String s, RecognitionException e) {
         System.out.println("syntaxError");
         
         
         System.out.println(o.toString() + " ");
         System.out.println("line " + i + ":" + i1);
         System.out.println(s);
         this.errors+=1;
     }

     @Override
     public void reportAmbiguity(Parser parser, DFA dfa, int i, int i1, boolean b, BitSet bitSet, ATNConfigSet atnConfigSet) {
         System.out.println("reportAmbiguity");
         System.out.println("line " + i + ":" + i1);
         this.errors+=1;
     }

     @Override
     public void reportAttemptingFullContext(Parser parser, DFA dfa, int i, int i1, BitSet bitSet, ATNConfigSet atnConfigSet) {
         System.out.println("reportAttemptingFullContext");
         System.out.println("line " + i + ":" + i1);
         this.errors+=1;
     }

     @Override
     public void reportContextSensitivity(Parser parser, DFA dfa, int i, int i1, int i2, ATNConfigSet atnConfigSet) {
         System.out.println("reportContextSensitivity");
         System.out.println("line " + i + ":" + i1);
         this.errors+=1;
     }

     public int getErrorCount() {
    	 return this.errors;
     }
     
     public void setErrorCount(int errors) {
    	 this.errors = errors;
     }
}
