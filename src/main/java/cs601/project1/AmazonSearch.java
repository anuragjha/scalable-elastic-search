package cs601.project1;

public class AmazonSearch {

	public static void main(String[] args) {
		long startTime = System.currentTimeMillis();
		AmazonJsonHandler jsonReader = new AmazonJsonHandler();
		//jsonReader.takeJsonInput("reviews_Cell_Phones_and_Accessories_5_sample.json");
		//jsonReader.takeJsonInput("qa_Cell_Phones_and_Accessories_sample.json");
		jsonReader.takeJsonInput("reviews_Cell_Phones_and_Accessories_5.json");
		jsonReader.takeJsonInput("qa_Cell_Phones_and_Accessories.json");
		
		System.out.println("Json file read and DataStore built successfully");

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
		
		System.out.println("time taken :"+ ((System.currentTimeMillis() - startTime)/60000.0) + " minutes");
		//UserInterface ui = new UserInterface();
		//ui.startInterface();
	}

}