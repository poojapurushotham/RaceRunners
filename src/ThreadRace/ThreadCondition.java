package ThreadRace;

/** class to contain the variable that controls the thread run
 * 
 * @author pooja
 *
 */
public class ThreadCondition {

	boolean condition = false;
	
	//accessor method for condition variable
	public boolean getFlag() {
		return condition;
	}
	
	//mutator method for condition variable
	public void setFlag() {
		this.condition = true;
	}
}
