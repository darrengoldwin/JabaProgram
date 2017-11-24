package console;

import javax.swing.JTextArea;

public class Output {

	private JTextArea console;
	private String log;
	
	private static Output sharedInstance = null;
	
	public static Output getInstance() {
		return sharedInstance;
	}
	
	private Output(JTextArea console) {
		setConsole(console);
		clear();
	}
	
	public void setConsole(JTextArea console) {
		this.console = console;
	}
	
	public JTextArea getConsole() {
		return this.console;
	}
	public static void initialize(JTextArea console) {
		sharedInstance = new Output(console);
	}

	public void printa(String s) {
		log+= s;
		
		console.setText(log);
	}
	
	public void print(String s) {
		log+= s + "\n";
		
		console.setText(log);
	}
	
	
	public void clear() {
		log ="";
		
		console.setText(log);
	}
}
