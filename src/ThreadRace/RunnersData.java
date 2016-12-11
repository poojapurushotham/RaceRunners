package ThreadRace;

/** class for storing the data read from the data sources
 * 
 * @author pooja
 *
 */
public final class RunnersData {
	String RunnersName;
	double RunnersSpeed;
	double RestPercentage;
	
	/*parameterized constructor
	 * 
	 */
	public RunnersData(String RunnersName, double RunnersSpeed, double RestPercentage) {
		this.RunnersName = RunnersName;
		this.RunnersSpeed = RunnersSpeed;
		this.RestPercentage = RestPercentage;
	}
	
	/*default constructor
	 * 
	 */
	public RunnersData() {}
	
	/** getter and setter methods for instance variables
	 */
	public String getName() {
		return RunnersName;
	}
	
	public void setName(String RunnersName) {
		this.RunnersName = RunnersName;
	}
	
	public double getSpeed() {
		return RunnersSpeed;
	}
	
	public void setSpeed(double RunnersSpeed) {
		this.RunnersSpeed = RunnersSpeed;
	}
	
	public double getRestPercentage() {
		return RestPercentage;
	}
	
	public void setRestPercentage(double RestPercentage) {
		this.RestPercentage = RestPercentage;
	}
}