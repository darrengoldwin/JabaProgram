/**
 * 
 */
package execution.adders;

import java.util.ArrayList;

import execution.commands.ICommand;

/**
 * Handles adding of execution to the main control flow.
 * @author NeilDG
 *
 */
public class MainExecutionAdder implements IExecutionAdder {

	private ArrayList<ICommand> mainExecutionList;
	
	public MainExecutionAdder(ArrayList<ICommand> mainExecutionList) {
		this.mainExecutionList = mainExecutionList;
	}
	
	@Override
	public void addCommand(ICommand command) {
		System.out.println(command.getClass().toGenericString());
		this.mainExecutionList.add(command);
	}

}
