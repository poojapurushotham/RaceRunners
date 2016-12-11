package ThreadRace;

import java.util.ArrayList;
import java.util.Scanner;

/** Application to be run - contains main method
 * 
 * @author pooja
 * @name PoojaPurushotham_FinalProject
 */
public class MainApp {
	
	/**main method
	 * 
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String args[]) throws InterruptedException {
		Scanner sc = new Scanner(System.in);
		String choice = "y";
		
		//loops to get in user's input to select the type of input source
		while(choice.equalsIgnoreCase("y")) {
			//Creating lists to hold thread objects input runner objects 
			ArrayList<Thread> RunnersList = new ArrayList<>();
			ArrayList<RunnersData> runners = new ArrayList<>();
			DataSource ds = null;
			System.out.println("Welcome to the Marathon Race Runner Program \nSelect your data source: ");
			System.out.println("1. Derby database \n2. XML file \n3. Text file \n4. Default two runners \n5. Exit");
			int source = DataValidator.getInt(sc,"Enter your choice:");
			ThreadCondition check = new ThreadCondition();
			
			//select from different data sources for input
			switch(source) {
			case 1 :  
					//Reading input from Database
					System.out.println("Reading input from Database ... ");
					try {
						ds = new RunnersDB();
						runners = ds.getRunnersData();
					} catch(NullPointerException e) {
						System.out.println("Could not read from the database");
					}
					break;
			case 2 : 
					//Reading input from XML file
					System.out.println("Reading input from XML file ... ");
					try {
						ds = new RunnersXMLFile();
						runners = ds.getRunnersData();
					} catch(NullPointerException e) {
						System.out.println("Could not read from the XML file");
					}			
					for(RunnersData r : runners) {
						System.out.println(r.getName());
					}
					break;
			case 3 : 
					//Reading input from Text file
					System.out.println("Reading input from Text file ... ");
					ds = new RunnersTextFile();
					runners = ds.getRunnersData();
					break;
			case 4 :
					//Reading input of 2 runners Tortoise and Hare
					System.out.println("Reading input of 2 runners Tortoise and Hare ... ");
					RunnersData runner = new RunnersData("Tortoise", 10, 0);
					runners.add(runner);
					runner = new RunnersData("Hare", 100, 90);
					runners.add(runner);
					break;
			case 5 : 
					//exit
					System.out.println("\nThank you for using my Marathon Race Program");
					return;
			default : 
					System.out.println("Wrong Input!");
					break;
			}
			
			System.out.println("\nRace started : Get set Go !");
			
			//creating threads by copying the RunnerData state variables to Thread objects
			for (RunnersData r : runners) {
				Thread t = new ThreadRunner(r.getName(), r.getSpeed(), r.getRestPercentage(), check);
				t.setName(r.getName());
				RunnersList.add(t);
				System.out.println(t.getName() + " created");
			}
			
			//starting threads
			for (Thread t : RunnersList) {
				t.start();
				System.out.println(t.getName() + " started");
				Thread.yield();
			}
			for (Thread t : RunnersList) {
				t.join();
			}
			//get user input for continuing the loop
			choice = DataValidator.getString(sc, "\nContinue? (y/n)");
		}
		sc.close();
	}
	
	/** Finished method declares a winner and losers and sets the flag that stops rest of threads from counting
	 * 
	 * @param ThreadRunner object
	 * @param ThreadCondition object
	 */
	public static synchronized void finished(ThreadRunner t, ThreadCondition check) {
		if (t.distance >= RunnersConstants.FINAL_COUNT && !check.getFlag()){
			check.setFlag();
			System.out.println("\n" + t.getName() + " : I Finished !");
			System.out.println("\nThe race is over! The " + t.getName() + " is the winner.");
		} else {
			System.out.println("\n" + t.getName() + " : You beat me fair and square.");
		}
	}

}