/**
 * 
 */
package execution.commands.controlled;

import execution.commands.ICommand;
import execution.commands.controlled.IControlledCommand.ControlTypeEnum;

/**
 * An interface conditional command to represent IF-ELSE statements
 * @author NeilDG
 *
 */
public interface IConditionalCommand extends ICommand {
	
	public abstract ControlTypeEnum getControlType();
	public abstract void addPositiveCommand(ICommand command);
	public abstract void addNegativeCommand(ICommand command);
}
