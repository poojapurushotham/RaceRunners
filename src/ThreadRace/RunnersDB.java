package ThreadRace;

import java.sql.*;
import java.util.ArrayList;

/**Implementing getRunnersData method of Data Source interface for reading from derby database
 * 
 * @author pooja
 *
 */
public class RunnersDB implements DataSource {
	
	/** getConnection method to open connection to derby database
	 * 
	 * @return Connection object
	 */
	private Connection getConnection() {
		Connection connection = null;
		try {
			String dbDirectory = "Resource";
			System.setProperty("derby.system.home", dbDirectory);
			// set the db url, username, and password
			String dbUrl = "jdbc:derby:MurachDB;create=true";
			String username = "";
			String password = "";
			connection = DriverManager.getConnection(dbUrl,username,password);
		}
		catch (SQLException e) {
			System.err.println("Error loading database driver\n");
			for (Throwable t : e) {
				t.printStackTrace();
			}
		}
		return connection;
	}
	
	/** Implementing getRunnersData method of Data Source interface
	 * 
	 */
	public ArrayList<RunnersData> getRunnersData() {
		String sql = "SELECT RunnersName, RunnersSpeed, RestPercent "
					+ "FROM Runners";
		ArrayList<RunnersData> RunnersList = new ArrayList<>();
		
		try (Connection connection = getConnection();
				PreparedStatement ps = connection.prepareStatement(sql);
				ResultSet rs = ps.executeQuery())
				{
			while (rs.next()) {
				String name = rs.getString("RunnersName");
				double speed = rs.getDouble("RunnersSpeed");
				double rest = rs.getDouble("RestPercent");
				
				RunnersData runner = new RunnersData(name,speed,rest);
				RunnersList.add(runner);
			}
			rs.close();
            ps.close();
            connection.close();
            
            //disconnecting from database
            if (disconnect()) {
            	System.out.println("Disconnected successfully from DB");
			}
            System.gc();
    		return RunnersList;
		} 
		catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**method for disconnecting from database
	 * 
	 * @return boolean
	 */
	private boolean disconnect() {
		try {
	    	String shutdownUrl = "jdbc:derby:MurachDB;shutdown=true";
	    	DriverManager.getConnection(shutdownUrl);
	    	return true;
	    }
	    catch (SQLException e) {
			return false;
		}
	}
}

