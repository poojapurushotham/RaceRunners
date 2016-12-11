package ThreadRace;

import java.util.Random;

/** class for creating Threads
 * 
 * @author pooja
 *
 */
public class ThreadRunner extends Thread {
	int distance;
	String RunnersName;
	double RunnersSpeed;
	double RestPercentage;
	ThreadCondition check=null;
	
	/** constructor for Thread object
	 * 
	 * @param RunnersName
	 * @param RunnersSpeed
	 * @param RestPercentage
	 * @param ThreadCondition object
	 */
	public ThreadRunner(String RunnersName, double RunnersSpeed, double RestPercentage, ThreadCondition check) {
		this.RunnersName = RunnersName;
		this.RunnersSpeed = RunnersSpeed;
		this.RestPercentage = RestPercentage;
		this.check = check;
	}
	
	/** method to get distance run by a thread
	 * 
	 * @return int
	 */
	public int getDistance() {
		return distance;
	}
	
	/** run method is called when a thread is started
	 * 
	 */
	@Override
	public void run() {
		//check for conditions of less than final count and if there is no winner yet
		while (distance < RunnersConstants.FINAL_COUNT && !check.getFlag()) {
			//generate random number
			int rand = new Random().nextInt(100);

			//randomize thread run
			if (rand > RestPercentage) {
				distance +=  RunnersSpeed;
				System.out.println(this.getName() + " : " + distance);
				Thread.yield();
			} else { //if thread is not running, then sleep
				try {
					Thread.sleep(RunnersConstants.DEFAULT_SLEEP_TIME);
				} catch (InterruptedException e) {
					check.setFlag();
					break;
				}
			}
		}
		//one of the threads has won, call finished method to declare winners and losers
		MainApp.finished(this, check);
		}
}