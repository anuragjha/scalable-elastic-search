/**
 * 
 */
package cs601.project1;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @author anuragjha
 *
 *  AmazonJsonReader reads the json file and creates the corresponding Review or QuesAns object
 * 		files - reviews_Cell_Phones_and_Accessories_5_sample.json
 * 		files - qa_Cell_Phones_and_Accessories_sample.json
 */
public class AmazonJsonHandler {

	/**
	 * jsonFileReader method takes a input file and uses
	 * @param inputFile
	 */
	public boolean jsonFileReader(String inputFile)	{

		JsonParser parser = new JsonParser();
		Path path = Paths.get(inputFile);

		try(
				BufferedReader reader = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))
				)	{
			String line;

			while((line = reader.readLine()) != null)	{
				try {

					JsonElement element = parser.parse(line);
					JsonObject object = element.getAsJsonObject();
					this.jsonRecordSorter(object);

				} catch(JsonSyntaxException jse)	{
					System.out.println("Skipping line ...");
				}

			}
			return true;
			
		} catch(IOException ioe)	{
			System.out.println("IO exception:\n"+ioe.getStackTrace());
			return false;
		}

	}



	/**
	 * jsonRecordSorter method sorts the individual record
	 * @param object
	 */
	private void jsonRecordSorter(JsonObject object) {
		
		if(object.get("reviewerID") != null)	{
			
			readReviewsData(object);
		}
		else if(object.get("questionType") != null)	{
			
			readQuesAnsData(object);
		}
		else	{
			
			System.out.println("Record type not found");
		}

	}



	/**
	 * readReviewsData method takes the Json object and creates the Review Object
	 * @param object
	 */
	private void readReviewsData(JsonObject object) {
		//Reviews object ->
		//reviewerID, asin, reviewerName, helpful, reviewText, overall, summary, unixReviewTime, reviewTime
		
		//JsonElement nameElement;
		//nameElement = object.get("reviewerID");
		//String reviewerID = nameElement.getAsString();
		// -> can be written as ==> String reviewerID = object.get("reviewerID").getAsString();
		String reviewerID = object.get("reviewerID").getAsString();
		String asin = object.get("asin").getAsString();
		String reviewerName; 											//needed coz of line 270 in json file 
		if(object.get("reviewerName") != null)	{
			reviewerName = object.get("reviewerName").getAsString();
		} else	{
			reviewerName = "";
		}
		int[] helpful = new int[2]; 			//assuming that helpful is a array of 2 integers always
		helpful[0] = object.get("helpful").getAsJsonArray().get(0).getAsInt();
		helpful[1] = object.get("helpful").getAsJsonArray().get(1).getAsInt();
		String reviewText = object.get("reviewText").getAsString();
		double overall = object.get("overall").getAsDouble();
		String summary = object.get("summary").getAsString();
		String unixReviewTime = object.get("unixReviewTime").getAsString();
		String reviewTime = object.get("reviewTime").getAsString();
		 
		// create a new Review Object
		new AmazonReviews(reviewerID, asin, reviewerName, helpful, reviewText, 
				overall, summary, unixReviewTime, reviewTime);
		
	}


	

	/**
	 * readQuesAnsData takes the Json object and creates the QuesAns Object
	 * @param object
	 */
	private void readQuesAnsData(JsonObject object) {
		//QuesAns object ->
		//questionType;asin;answerTime;unixTime;question;answerType;answer;
		
		//JsonElement nameElement;
		//nameElement = object.get("questionType");
		//String questionType = nameElement.getAsString();
		// -> can be written as ==> String questionType = object.get("questionType").getAsString();
		String questionType = object.get("questionType").getAsString();
		String asin = object.get("asin").getAsString();
		String answerTime = object.get("answerTime").getAsString();
		String unixTime;			//direct assignment gives NullPointerException - one or more record do not have this data
		if(object.get("unixTime") != null)	{
			unixTime = object.get("unixTime").getAsString();
		} else 	{
			unixTime = "";
		}
		String question = object.get("question").getAsString();
		String answerType;			//direct assignment gives NullPointerException - one or more record do not have this data
		if(object.get("answerType") != null)	{
			answerType = object.get("answerType").getAsString();
		}	else	{
			answerType = "";
		}
		String answer = object.get("answer").getAsString();
		
		//create a QuesAnsObject
		new AmazonQuesAns(questionType, asin, answerTime, unixTime, question, answerType, answer);
		
	}

	
	
	/**
	 * using main method to test this class only
	 * @param args
	 */
	public static void main(String[] args) {
		
		AmazonJsonHandler jr = new AmazonJsonHandler();
		jr.jsonFileReader("reviews_Cell_Phones_and_Accessories_5.json");
		//jr.jsonFileReader("reviews_Cell_Phones_and_Accessories_5_sample.json");
		//jr.jsonFileReader("qa_Cell_Phones_and_Accessories_sample.json");
		jr.jsonFileReader("qa_Cell_Phones_and_Accessories.json");
		System.out.println("Json file read successfully");
		//System.out.println("ReviewAsinDataStore: " + 
		//		AmazonDataStore.ONE.reviewAsinDataStore.keySet());
		System.out.println("Finding something ReviewAsinDataStore: " + 
				AmazonDataStore.ONE.reviewAsinDataStore.get("6073894996"));
		System.out.println("Finding something quesAnsAsinDataStore: " + 
				AmazonDataStore.ONE.quesAnsAsinDataStore.get("6073894996"));
		
		
	}

}
