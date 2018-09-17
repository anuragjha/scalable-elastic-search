package cs601.project1;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author anuragjha
 *
 */
public class AmazonQuesAnsFileHandler {
	
	JsonHandler jsonHandler;
	
	public AmazonQuesAnsFileHandler(String quesAnsFile)	{
		super();
		processJsonFile(quesAnsFile);
	}
	
	
	private void processJsonFile(String quesAnsFile)	{
		
		System.out.println("Processing QuesAns file.");
		for(String thisRecord : (JsonHandler.jsonFileReader(quesAnsFile)))	{
			try	{
			AmazonQuesAns thisAmazonQA = new Gson().fromJson(thisRecord, AmazonQuesAns.class);
			thisAmazonQA.notifyDataStore();
			} catch(JsonSyntaxException jse)	{
				System.out.println("Skipping line ...");
				}
			
		}
		
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
