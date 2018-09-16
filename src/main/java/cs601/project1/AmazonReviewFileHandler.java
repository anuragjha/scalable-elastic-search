/**
 * 
 */
package cs601.project1;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

/**
 * @author anuragjha
 *
 */
public class AmazonReviewFileHandler {
	
	JsonHandler jsonHandler;
	
	public AmazonReviewFileHandler(String reviewFile)	{
		super();
		processJsonFile(reviewFile);
	}
	
	
	private void processJsonFile(String reviewFile)	{
		System.out.println("Processing Reviews");
		for(String thisRecord : (JsonHandler.jsonFileReader(reviewFile)))	{
			try	{
			AmazonReviews thisAmazonReview = new Gson().fromJson(thisRecord, AmazonReviews.class);
			thisAmazonReview.notifyDataStore();
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
