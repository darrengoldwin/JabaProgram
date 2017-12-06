package console;

import java.util.ArrayList;

import execution.ExecutionManager;
import execution.commands.ICommand;
import initial.GUI;
import representation.MobiValue;
import searching.VariableSearcher;
import utils.notifications.NotificationCenter;
import utils.notifications.NotificationListener;
import utils.notifications.Notifications;
import utils.notifications.Parameters;

public class Debug implements NotificationListener{

	private final static String TAG = "Debug";
	
	public final static String COMMAND = "COMMAND";
	
	private static Debug sharedInstance = null;
	
	public static Debug getInstance() {
		return sharedInstance;
	}
	
	public static void initialize() {
		sharedInstance = new Debug();
		NotificationCenter.getInstance().addObserver(Notifications.ON_BREAK_BEFORE_POINT, sharedInstance);
		NotificationCenter.getInstance().addObserver(Notifications.ON_BREAK_AFTER_POINT, sharedInstance);
	}
	public void breakBeforePoint(ICommand command) {
		GUI.getInstance().resumeButton.setVisible(true);
		ExecutionManager.getInstance().blockExecution();
		Output.getInstance().print("DEBUG: Break before point! " + command.getClass().getSimpleName() +" at line " + GUI.getInstance().breakpoint + "\n");
		ArrayList<String>variables = VariableSearcher.getAllVariables();
		
		for(String s: variables) {
			
			MobiValue m = VariableSearcher.searchVariable(s);
			
			//System.out.println(s + " " + m.getPrimitiveType() +" " + m.getValue() +" ");
			if(m.getValue() == null)
				GUI.getInstance().getVar(m.getPrimitiveType().toString(), s, "null");
			else
				GUI.getInstance().getVar(m.getPrimitiveType().toString(), s, m.getValue().toString());
		}

	}
	
	public void breakAfterPoint(ICommand command) {
		GUI.getInstance().resumeButton.setVisible(true);
		ExecutionManager.getInstance().blockExecution();
		Output.getInstance().print("DEBUG: Break after point! " + command.getClass().getSimpleName() +" at line " + GUI.getInstance().breakpoint + "\n");
		ArrayList<String>variables = VariableSearcher.getAllVariables();
		
		for(String s: variables) {
			
			MobiValue m = VariableSearcher.searchVariable(s);
			
			//System.out.println(s + " " + m.getPrimitiveType() +" " + m.getValue() +" ");
			if(m.getValue() == null)
				GUI.getInstance().getVar(m.getPrimitiveType().toString(), s, "null");
			else
				GUI.getInstance().getVar(m.getPrimitiveType().toString(), s, m.getValue().toString());
		}

	}
	@Override
	public void onNotify(String notificationString, Parameters params) {
		// TODO Auto-generated method stub
		if(notificationString == Notifications.ON_BREAK_BEFORE_POINT) {
			breakBeforePoint((ICommand)params.getObjectExtra(COMMAND, null));
		}
		else if(notificationString == Notifications.ON_BREAK_AFTER_POINT) {
			breakAfterPoint((ICommand)params.getObjectExtra(COMMAND, null));
		}
	}

}
