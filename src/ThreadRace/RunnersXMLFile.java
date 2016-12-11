package ThreadRace;

import java.io.*;
import java.nio.file.*;
import java.util.ArrayList;

import javax.xml.stream.*;

public class RunnersXMLFile implements DataSource {
	
	/** implementing getRunnersData method of Data Source interface for reading from XML file
	 * 
	 * @return - List of RunnerData objects 
	 */
	public ArrayList<RunnersData> getRunnersData() {
		ArrayList<RunnersData> runners = new ArrayList<>();
		String fileStr = "Resource/FinalXMLData.xml";
		Path filePath = Paths.get(fileStr);
		RunnersData runner = null;
		
		if (Files.exists(filePath)) {
			//create XMLInputFactory object
			XMLInputFactory input = XMLInputFactory.newFactory();
			try {
				// create a XMLStreamReader object
				FileReader fileReader = new FileReader(filePath.toFile());
				XMLStreamReader reader = input.createXMLStreamReader(fileReader);
				
				while (reader.hasNext()) {
					int eventType = reader.getEventType();
					switch (eventType) {
					
						case XMLStreamConstants.START_ELEMENT :  
							//Checks for first occurrence of Runner element- opening tag
							String eleName = reader.getLocalName();
							if (eleName.equals("Runner")) {
								runner = new RunnersData();
								runner.setName(reader.getAttributeValue(0));
							}
							if (eleName.equals("RunnersMoveIncrement")) {
								runner.setSpeed(Double.parseDouble(reader.getElementText()));
							}
							if (eleName.equals("RestPercentage")) {
								runner.setRestPercentage(Double.parseDouble(reader.getElementText()));
							}
							break;
							
						case XMLStreamConstants.END_ELEMENT :
							//Checks for closing tag of Runner element
							eleName = reader.getLocalName();
							if (eleName.equals("Runner")) {
								runners.add(runner);
							}
							break;
						default :  break;	
						}
					
					reader.next();
				}
			}
			catch (IOException | XMLStreamException e) {
				System.err.println(e);
			}
		}
		return runners;
	}
}