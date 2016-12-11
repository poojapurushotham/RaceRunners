package ThreadRace;

import java.util.ArrayList;

/**Interface that is implemented by all types of Data Sources to get input
 * 
 * @author pooja
 *
 */
public interface DataSource {
	public ArrayList<RunnersData> getRunnersData();
}
