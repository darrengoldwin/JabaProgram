/**
 * 
 */
package scope;

import representation.MobiValue;

/**
 * A generic scope interface
 * @author NeilDG
 *
 */
public interface IScope {

	public abstract MobiValue searchVariableIncludingLocal(String identifier);
	public abstract boolean isParent();
}
