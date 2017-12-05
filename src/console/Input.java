package console;

import javax.swing.JButton;
import javax.swing.JTextArea;

import utils.notifications.NotificationCenter;
import utils.notifications.NotificationListener;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

public class Input implements NotificationListener{

	public final static String VALUE_ENTERED_KEY = "VALUE_ENTERED_KEY";
	private JTextArea input;
	private JButton submit;
	private String s;
	
	private static Input sharedInstance = null;
	
	public static Input getInstance() {
		return sharedInstance;
	}
	
	private Input(JTextArea input, JButton submit) {
		setInput(input);
		setSubmit(submit);
	}
	
	public JButton getSubmit() {
		return submit;
	}

	public void setSubmit(JButton submit) {
		this.submit = submit;
	}

	public void setInput(JTextArea input) {
		this.input = input;
	}
	
	public JTextArea getInput() {
		return this.input;
	}
	public static void initialize(JTextArea input, JButton submit) {
		sharedInstance = new Input(input, submit);
		
		NotificationCenter.getInstance().addObserver(Notifications.ON_FOUND_SCAN_STATEMENT, sharedInstance);
	}

	public void clear() {
		s= "";
		input.setText(s);
		submit.setEnabled(true);
		input.setEnabled(true);
		
	}
	
	public void close() {
//		submit.setEnabled(false);
//		input.setEnabled(false);
	}
	public Parameters getMessage() {
		
		Parameters param = new Parameters();
		Output.getInstance().print(input.getText().toString() + "\n");
		param.putExtra(VALUE_ENTERED_KEY, input.getText().toString());
		
		return param;
	}
	@Override
	public void onNotify(String notificationString, Parameters params) {
		// TODO Auto-generated method stub
		if(notificationString == Notifications.ON_FOUND_SCAN_STATEMENT) {
			clear();
			
		}
	}

	
}
