package ThreadRace;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

public class RunnersTextFile implements DataSource {
	
	/** implementing getRunnersData method of Data Source interface for reading from text file
	 * 
	 * @return - List of RunnerData objects 
	 */
	public ArrayList<RunnersData> getRunnersData() { 
		ArrayList<RunnersData> RunnersList = new ArrayList<>();
		RunnersData runner = null;
		String fileStr = "Resource/FinalTextData.txt";
		Path filePath = Paths.get(fileStr);
		if (Files.exists(filePath)) {
			File runnersFile = filePath.toFile();
			try (BufferedReader in = new BufferedReader(new FileReader(runnersFile))) {
				String line;
				while ((line = in.readLine()) != null) {
					runner = new RunnersData();
			
					/* get data from each tuple by splitting the line
					 *  
					 */
					String[] strLine = line.split(" ");
					runner.setName(strLine[0]);
					runner.setSpeed(Double.parseDouble(strLine[1]));
					runner.setRestPercentage(Double.parseDouble(strLine[2]));
					
					/* add the RunnerData object to arraylist of RunnerData
					 * 
					 */
					RunnersList.add(runner);
				}
			}
			catch (IOException e) {
				System.err.println(e);
			}
		}
			return RunnersList;
	}
}