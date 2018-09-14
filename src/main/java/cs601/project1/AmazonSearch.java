package cs601.project1;

public class AmazonSearch {

	public static void main(String[] args) {
		//	long startTime = System.currentTimeMillis();

		//AmazonJsonHandler jr = new AmazonJsonHandler();
		//jr.takeJsonInput("reviews_Cell_Phones_and_Accessories_5.json");
		//		jr.takeJsonInput("qa_Cell_Phones_and_Accessories.json");

		//jr.takeJsonInput("reviews_Cell_Phones_and_Accessories_5_sample.json");
		//jr.takeJsonInput("qa_Cell_Phones_and_Accessories_sample.json");

		//	System.out.println("Json file read and DataStore built successfully");

		//AmazonDataStore.ONE.buildReviewWordDataStore();
		//AmazonDataStore.ONE.buildQuesAnsWordDataStore();
		//	System.out.println("Word DataStores built successfully");
		//System.out.println("Finding something ReviewDataStore: " + 
		//		AmazonDataStore.ONE.reviewDataStore.get("6073894996"));
		//System.out.println("Finding something ReviewWordDataStore: " + 
		//	AmazonDataStore.ONE.reviewWordDataStore.getIndex().get("the"));
		//System.out.println("Finding something quesAnsDataStore: " + 
		//		AmazonDataStore.ONE.quesAnsDataStore.get("6073894996"));
		//System.out.println("Finding something QuesAnsWordDataStore: " + 
		//	AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().get("the"));

		//for(String str : AmazonDataStore.ONE.reviewWordDataStore.getIndex().get("the").keySet())	{
		//	
		//	System.out.println("Finding something ReviewWordDataStore: " + str);
		//}

		//	System.out.println("time taken :"+ ((System.currentTimeMillis() - startTime)/60000.0) + " minutes");
		//	UserInterface ui = new UserInterface();
		//	ui.startInterface();


		if(args.length !=  4)	{
			System.out.println("Please input 4 cmd line params");
			System.exit(1);
		}
		if( (args[0].equals("-reviews")) && (args[2].equals("-qa"))
				&& (args[1].contains("reviews_Cell_Phones_and_Accessories_5")) && (args[1].contains("json")) 
				&& (args[3].contains("qa_Cell_Phones_and_Accessories")) && (args[3].contains("json")) )	{

			long startTime = System.currentTimeMillis();
			//sending json file to AmazonJsonHandler class
			AmazonJsonHandler jr = new AmazonJsonHandler();
			jr.takeJsonInput(args[1]);
			jr.takeJsonInput(args[3]);

			//jr.takeJsonInput("reviews_Cell_Phones_and_Accessories_5_sample.json");
			//jr.takeJsonInput("qa_Cell_Phones_and_Accessories_sample.json");

			System.out.println("Json file read and DataStore built successfully");
			System.out.println("time taken :"+ ((System.currentTimeMillis() - startTime)/60000.0) + " minutes");
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