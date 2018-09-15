package cs601.project1;

/**
 * AmazonSearch class is main class that controls the flow of the program
 * @author anuragjha
 *
 */
public class AmazonSearch {
	/**
	 * main method starts the program, it calls the AmazonJasonHandler method to read the json file 
	 * and then calls the UserInterface to start
	 * @param args 
	 * 			-reviews <review_file_name> -qa <qa_file_name>
	 */
	public static void main(String[] args) {

		if(args.length !=  4)	{
			System.out.println("Please input 4 cmd line params");
			System.out.println("-reviews <review_file_name> -qa <qa_file_name>");
			System.exit(1);
		}
		if( (args[0].equals("-reviews")) && (args[2].equals("-qa"))
				&& (args[1].contains(".json")) 
				&& (args[3].contains(".json")) )	{
			//System.out.println("Starting");
			long startTime = System.currentTimeMillis();
			//sending json file to AmazonJsonHandler class
			AmazonJsonHandler jr = new AmazonJsonHandler();
			jr.takeJsonInput(args[1]);
			jr.takeJsonInput(args[3]);

			//jr.takeJsonInput("reviews_Cell_Phones_and_Accessories_5_sample.json");
			//jr.takeJsonInput("qa_Cell_Phones_and_Accessories_sample.json");

			System.out.println("Json files read and DataStores built successfully");
			System.out.println("time taken :"+ ((System.currentTimeMillis() - startTime)/1000) + " seconds");
			UserInterface ui = new UserInterface();
			ui.startInterface();

		}
		else	{
			System.out.println("Please input line params in specified format");
			System.out.println("-reviews <review_file_name> -qa <qa_file_name>");
			System.exit(1);
		}

	}


}