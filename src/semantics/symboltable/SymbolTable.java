/**
 * 
 */
package semantics.symboltable;

import java.util.HashMap;

import scope.ClassScope;

/**
 * Holds all of the found variables in code and stores them here in the symbol table manager.
 * @author NeilDG
 *
 */
public class SymbolTable {

	private final static String TAG = "MobiProg_SymbolTableManager";
	
	private static SymbolTable sharedInstance = null;
	
	public static SymbolTable getInstance() {
		return sharedInstance;
	}
	
	private HashMap<String, ClassScope> classTable;
	
	private SymbolTable() {
		this.classTable = new HashMap<String, ClassScope>();
	}
	
	public static void initialize() {
		sharedInstance = new SymbolTable();
		System.out.println("SYMBOL TABLE");
	}
	
	public static void reset() {
		
		sharedInstance.classTable.clear();
	}
	
	public void addClassScope(String className, ClassScope classScope) {
		System.out.println("CLASSSS : " + className);
		this.classTable.put(className, classScope);
	}
	
	public ClassScope getClassScope(String className) {
		
		if(this.containsClassScope(className)) {
			return this.classTable.get(className);
		}
		else {
			System.out.println(className + " is not found!");
			return null;
		}
	}
	
	public boolean containsClassScope(String className) {
		return this.classTable.containsKey(className);
	}
	
	public void resetClassTables() {
		ClassScope[] classScopes = this.classTable.values().toArray(new ClassScope[this.classTable.size()]);
		
		for(int i = 0; i < classScopes.length; i++) {
			classScopes[i].resetValues();
		}
	}
}
