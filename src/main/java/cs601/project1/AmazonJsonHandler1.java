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
import java.util.LinkedList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

/**
 * @author anuragjha
 *
 *  AmazonJsonReader reads the json file and creates the corresponding Review or QuesAns object
 *  and then calls notifyDataStore method on the object
 * 		files - reviews_Cell_Phones_and_Accessories_5_sample.json
 * 		files - qa_Cell_Phones_and_Accessories_sample.json
 */
public class AmazonJsonHandler1 {

	
	private String inputFileType;
	//**// logic to check the filename to understand the type of record in the file
	/**
	 * checks the inputfile type and sends it across to jsonFileReader method
	 * @param inputFile
	 */
	public void takeJsonInput(String inputFile)	{
		if((inputFile.contains("reviews")) && !(inputFile.contains("qa")))	{
			System.out.println("Processing review file");
			this.inputFileType = "review";
			this.jsonFileReader(inputFile);
		}
		else if((inputFile.contains("qa")) && !(inputFile.contains("reviews")))	{
			System.out.println("Processing qa file");
			this.inputFileType = "qa";
			this.jsonFileReader(inputFile);
		}	
		else	{
			System.out.println("File type not recognised");
		}
	}


	/**
	 * jsonFileReader method takes a input file sends it to readReviewsData or readQuesAnsData based on 
	 * record type
	 * @param inputFile
	 */
	private void jsonFileReader(String inputFile)	{
		
		JsonParser parser = new JsonParser();
		Path path = Paths.get(inputFile);
		
		
		

		try(
				BufferedReader reader = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))
				)	{
			String line;
			
			while((line = reader.readLine()) != null)	{
				try {
					//JsonElement element = parser.parse(line);
					JsonObject object =  parser.parse(line).getAsJsonObject();
					
					if(this.inputFileType.matches("review"))	{
						//readReviewsData(object);
						LinkedList<AmazonReviews> allReviewRecords = new LinkedList<AmazonReviews>();
						AmazonReviews thisAmazonReview = new Gson().fromJson(object, AmazonReviews.class);
						allReviewRecords.add(thisAmazonReview);
						this.readReviewsData(allReviewRecords);
						
					}
					else if(this.inputFileType.matches("qa"))	{
						//readQuesAnsData(object);
						LinkedList<AmazonQuesAns> allQuesAnsRecords = new LinkedList<AmazonQuesAns>();
						AmazonQuesAns thisAmazonQuesAns = new Gson().fromJson(object, AmazonQuesAns.class);
						allQuesAnsRecords.add(thisAmazonQuesAns);
						this.readQuesAnsData(allQuesAnsRecords);

					}
					else	{
						System.out.println("File type not recognised");
					}
				} catch(JsonSyntaxException jse)	{
					System.out.println("Skipping line ...");
				}
			}	
			
		}	catch(FileNotFoundException fnfe)	{
			System.out.println("File NotFound: "+fnfe.getMessage());
			System.out.println("Exiting System");
			System.exit(0);
		}
		catch(IOException ioe)	{
			System.out.println("IO exception: "+ioe.getMessage());
			System.out.println("Exiting System");
			System.exit(1);
		}
		
	}



	/**
	 * readReviewsData method takes the Json object and creates the Review Object then notifies datastore
	 * @param object
	 */
	private void readReviewsData(LinkedList<AmazonReviews> allReviewRecords) {
		//https://github.com/google/gson/blob/master/UserGuide
		//AmazonReviews thisAmazonReview = new Gson().fromJson(object, AmazonReviews.class);
		//++++add element to structure
		//thisAmazonReview.notifyDataStore();
		for(AmazonReviews amazonReview : allReviewRecords )	{
			amazonReview.notifyDataStore();
		}
	}



	/**
	 * readQuesAnsData takes the Json object and creates the QuesAns Object then notifies datastore
	 * @param object
	 */
	private void readQuesAnsData(LinkedList<AmazonQuesAns> allQuesAnsRecords) {
		//AmazonQuesAns thisAmazonQuesAns = new Gson().fromJson(object, AmazonQuesAns.class);
		//thisAmazonQuesAns.notifyDataStore();
		for(AmazonQuesAns amazonQuesAns : allQuesAnsRecords )	{
			amazonQuesAns.notifyDataStore();
		}
	}


	/**
	 * using main method to test this class only
	 * @param args
	 */
	public static void main(String[] args) {
		
		long startTime = System.currentTimeMillis();
		AmazonJsonHandler jr = new AmazonJsonHandler();
		jr.takeJsonInput("reviews_Cell_Phones_and_Accessories_5.json","-reviews");
		//jr.takeJsonInput("reviews_Cell_Phones_and_Accessories_5_sample.json");
		//AmazonJsonHandler jr1 = new AmazonJsonHandler();
		jr.takeJsonInput("qa_Cell_Phones_and_Accessories.json","-qa");
		//jr.takeJsonInput("qa_Cell_Phones_and_Accessories_sample.json");

		System.out.println("Json file read and DataStore built successfully");
		
		CmdProcessor cmd = new CmdProcessor();
		System.out.println("Finding something ReviewWordDataStore: " );
		cmd.reviewSearch("the");
		//System.out.println("Finding something quesAnsWordDataStore: " + cmd.qaSearch("the"));

		System.out.println("time taken AmazonJsonHandler1:"+ ((System.currentTimeMillis() - startTime)/60000.0) + " minutes");
		 

	}

}
