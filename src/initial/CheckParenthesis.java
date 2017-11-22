package initial;
import java.util.ArrayList;
import java.util.Stack;

public class CheckParenthesis {
	
	private ArrayList<String> p;
	public CheckParenthesis(String s) {
		char[] c = s.toCharArray();
		
		p = new ArrayList<String>();
		
		for(int i =0; i< c.length; i++)
			if(c[i] == '{' || c[i] == '}') 
				p.add(String.valueOf(c[i]));
		
		System.out.println(p);
				
	}
	
	public boolean isBalance() {
		
		
		Stack<String> stack = new Stack<String> ();
		
		for(int i =0; i< p.size(); i++) {
			if(p.get(i).equals("{")) {
				stack.push("{");
			} else if(!stack.isEmpty())
				stack.pop();
			else
				return false;
		}
		
		if(stack.isEmpty())
			return true;
		else 
			return false;
		
	}

}
