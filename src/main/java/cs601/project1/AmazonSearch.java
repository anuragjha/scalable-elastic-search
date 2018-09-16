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

		System.out.println("Starting Project1");
		
		if(args.length !=  4)	{
			System.out.println("Please input 4 cmd line params");
			System.out.println("-reviews <review_file_name.json> -qa <qa_file_name.json>");
			System.exit(1);
		}
		if( (args[0].equals("-reviews")) && (args[2].equals("-qa"))
				&& (args[1].endsWith(".json")) 
				&& (args[3].endsWith(".json")) )	{
			//System.out.println("Starting");
			
			long startTime = System.currentTimeMillis();
			//sending json file to AmazonJsonHandler class
			//////AmazonJsonHandler jr = new AmazonJsonHandler();
			//////jr.takeJsonInput(args[1], args[0]);
			/////jr.takeJsonInput(args[3], args[2]);

			new JsonReviewHandler(args[1]); ///design works the fastest
			new JsonQAHandler(args[3]);   ///design works the fastest
			
			//new AmazonReviewFileHandler(args[1]);  //will have to read whole file at once
			//new AmazonQuesAnsFileHandler(args[3]); // will have to read whole file at once

			System.out.println("Json files read and DataStores built successfully");
			System.out.println("time taken :"+ ((System.currentTimeMillis() - startTime)/1000) + " seconds");
			UserInterface ui = new UserInterface();
			ui.startInterface();

		}
		else	{
			System.out.println("Please input line params in specified format");
			System.out.println("-reviews <review_file_name.json> -qa <qa_file_name.json>");
			System.exit(1);
		}

	}


}