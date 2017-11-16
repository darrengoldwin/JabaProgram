import org.antlr.v4.runtime.ANTLRErrorStrategy;
import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Parser;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class App
{
    public String output(String s, int linecount)
    {	
    
    	String input = "public class Test { \n" + s +"\n}"; 
    	ANTLRInputStream is = new ANTLRInputStream(input);
        //lexer
        JabaLexer jl = new JabaLexer(is);
        CommonTokenStream cts = new CommonTokenStream(jl);
        //parser
        JabaParser jp = new JabaParser(cts);
        //listener
        JabaBaseListener listener = new JabaBaseListener();
        //listener 2
        AntlrJabaListener listener2 = new AntlrJabaListener();
        //walker
        ParseTreeWalker walker = new ParseTreeWalker();
        //Error Listener
        JabaBaseErrorListener a = new JabaBaseErrorListener();
        a.setOutput("");
        jp.removeErrorListeners();
        
		jp.addErrorListener(a);
        jp.compilationUnit();
//        //entry point
//        JabaParser.CompilationUnitContext entryPoint =  jp.compilationUnit();
//      walker.walk(listener, entryPoint);
//        if(jp.getNumberOfSyntaxErrors() == 0)
//        	System.out.println("Compilation Complete, No Errors");
       
        if(jp.getNumberOfSyntaxErrors() == 0)
        	a.setOutput("No Syntax Error!");
	     return a.getOutput();
      
    }
}