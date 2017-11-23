/**
 * 
 */
package builder;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import implementors.JavaBaseImplementor;
import initial.JabaLexer;
import initial.JabaParser;

/**
 * Manages all parsing from different saved files
 * @author Patrick
 *
 */
public class ParserHandler {

	private final static String TAG = "MobiProg_ParserHandler";
	private static ParserHandler sharedInstance = null;
	
	public static ParserHandler getInstance() {
		if(sharedInstance == null) {
			sharedInstance = new ParserHandler();
		}
		
		return sharedInstance;
	}
	
	private JabaLexer sharedLexer;
	private JabaParser sharedParser;
	
	private String currentClassName; //the current class being parsed
	
	private ParserHandler() {
		
	}
	
	public void parseText(String className, String textToParse) {
		this.currentClassName = className.replace(".mobi", "");
		this.sharedLexer = new JabaLexer(new ANTLRInputStream(textToParse));
		CommonTokenStream tokens = new CommonTokenStream(this.sharedLexer);
		this.sharedParser = new JabaParser(tokens);
		this.sharedParser.removeErrorListeners();
		this.sharedParser.addErrorListener(BuildChecker.getInstance());
		
		ParserRuleContext parserRuleContext = this.sharedParser.compilationUnit();
		System.out.println(parserRuleContext.toStringTree(this.sharedParser));
		
		ParseTreeWalker treeWalker = new ParseTreeWalker();
		treeWalker.walk(new JavaBaseImplementor(), parserRuleContext);

		System.out.println("Finished parsing. Compiled executables. Click RUN to execute");
	}
	
	/*
	 * Returns the class name being parsed
	 */
	public String getCurrentClassName() {
		return this.currentClassName;
	}
}
