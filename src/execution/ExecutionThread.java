/**
 * 
 */
package execution;

import java.util.ArrayList;

import execution.commands.ICommand;
import utils.notifications.NotificationCenter;
import utils.notifications.Notifications;

/**
 * A worker thread that handles the execution of actions from ExecutionManager
 * @author NeilDG
 *
 */
public class ExecutionThread extends Thread {
	private final static String TAG = "ExecutionThread";
	
	private ArrayList<ICommand> executionList = new ArrayList<ICommand>();
	private ExecutionMonitor executionMonitor;
	
	public ExecutionThread(ArrayList<ICommand> executionList, ExecutionMonitor executionMonitor) {
		this.executionList = executionList;
		this.executionMonitor = executionMonitor;
	}
	
	/*
	 * Runs the thread by executing all actions provided that the execution flag isn't acquired by any other commands.
	 * If a command attempts to acquire the flag, this thread will block until its flag is released(presumably by the command 
	 * who acquired it or another command).
	 * (non-Javadoc)
	 * @see java.lang.Thread#run()
	 */
	@Override
	public void run() {
		try {
			
			for(ICommand command : this.executionList) {
				this.executionMonitor.tryExecution();
				command.execute();
			}
		}
		catch(InterruptedException e) {
			System.out.println("Monitor block interrupted! " +e.getMessage());
		}
		
		NotificationCenter.getInstance().postNotification(Notifications.ON_EXECUTION_FINISHED);
	}
}
