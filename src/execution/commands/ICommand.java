package execution.commands;


public interface ICommand {
	public boolean isBreakpoint();
	public abstract void execute();
}
