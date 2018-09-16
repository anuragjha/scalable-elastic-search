/**
 * 
 */
package cs601.project1;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @author anuragjha
 *
 */
public class JsonQAHandler {

	public JsonQAHandler(String inputFile)	{
		this.jsonFileReader(inputFile);
	}

	
	
	
	private void jsonFileReader(String inputFile)	{

		JsonParser parser = new JsonParser();
		Path path = Paths.get(inputFile);

		try(
				BufferedReader reader = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))
				)	{
			String line;
			System.out.println("Processing QuesAns");
			while((line = reader.readLine()) != null)	{
				try {
					//JsonElement element = parser.parse(line);
					JsonObject object =  parser.parse(line).getAsJsonObject();

					//LinkedList<AmazonReviews> allReviewRecords = new LinkedList<AmazonReviews>();
					//AmazonReviews thisAmazonReview = new Gson().fromJson(object, AmazonReviews.class);
					//allReviewRecords.add(thisAmazonReview);
					//this.readReviewsData(allReviewRecords);

					AmazonQuesAns thisAmazonQuesAns = new Gson().fromJson(object, AmazonQuesAns.class);
					thisAmazonQuesAns.notifyDataStore();
					
				} catch(JsonSyntaxException jse)	{
					System.out.println("Skipping line ...");
				}
			}	

		}	catch(IOException ioe)	{
			System.out.println("Could not process QA file");
			System.out.println("Exiting System");
			System.exit(0);
		}

	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
