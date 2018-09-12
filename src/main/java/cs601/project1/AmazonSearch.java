package cs601.project1;

public class AmazonSearch {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		AmazonJsonHandler jr = new AmazonJsonHandler();
		jr.takeJsonInput("reviews_Cell_Phones_and_Accessories_5_sample.json");
		jr.takeJsonInput("qa_Cell_Phones_and_Accessories_sample.json");
		//jr.takeJsonInput("reviews_Cell_Phones_and_Accessories_5.json");
		//jr.takeJsonInput("qa_Cell_Phones_and_Accessories.json");
		
		System.out.println("Json file read and DataStore built successfully");

		//System.out.println("Finding something ReviewAsinDataStore: " + 
		//		AmazonDataStore.ONE.reviewAsinDataStore.get("6073894996"));
		System.out.println("Finding something ReviewWordDataStore: " + 
			AmazonDataStore.ONE.reviewWordDataStore.getIndex().get("the"));
		//System.out.println("Finding something quesAnsAsinDataStore: " + 
		//		AmazonDataStore.ONE.quesAnsAsinDataStore.get("6073894996"));
		System.out.println("Finding something QuesAnsWordDataStore: " + 
			AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().get("the"));
		
		for(String str : AmazonDataStore.ONE.reviewWordDataStore.getIndex().get("the").keySet())	{
			
			System.out.println("Finding something ReviewWordDataStore: " + str);
		}
		
		System.out.println("time taken :"+ ((System.currentTimeMillis() - startTime)/60000.0) + " minutes");
		//UserInterface ui = new UserInterface();
		//ui.startInterface();
	}

}


//Project understanding
// 1. Have to read Json file; there are 2 diff Json file
// 2. read records inside the json file 
//		Review recored - 9 params
//      QuesAns record - 7 or 6 elements based on questype
//