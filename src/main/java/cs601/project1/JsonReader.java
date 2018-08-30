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

/**
 * 
 * The class takes the Json file and sends data in the file to appropriate class 
 * files - reviews_Cell_Phones_and_Accessories_5_sample.json
 * files - qa_Cell_Phones_and_Accessories_sample.json
 *
 * @author anuragjha
 *
 */
public class JsonReader {

	private boolean isReviews;
	private boolean isQuesAns;
	
	/**
	 * Constructor
	 */
	public JsonReader()	{
		super();
		isReviews = false;
		isQuesAns = false;
		
	}
	/**
	 * Constructor
	 * @param inputfile
	 * Method readNow takes Json file as input and sends it to appropriate class
	 * 
	 */ 
	//	 * ? is it good practice to put reader 
	public JsonReader(String inputFile)	{
		super();
		isReviews = false;
		isQuesAns = false;
	}
	
	/**
	 * 
	 * @param inputFile
	 * @return boolean
	 */
	//	 * ? is it a good practice to return boolean from methods that dont 
	// necessarily have to return anything
	public boolean readNow(String inputFile)	{
		
		JsonParser parser = new JsonParser();
		Path path = Paths.get(inputFile);
		
		try(
				BufferedReader reader = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"))
				)	{
			String line;
			while(((line = reader.readLine())) != null)	{
				JsonElement element = parser.parse(line);
				//System.out.println("element: " + element); // checking JsonElement
				//Reviews - element: {"reviewerID":"A30TL5EWN6DFXT","asin":"120401325X","reviewerName":"christina","helpful":[0,0],"reviewText":"They look good and stick good! I just don't like the rounded shape because I was always bumping it and Siri kept popping up and it was irritating. I just won't buy a product like this again","overall":4.0,"summary":"Looks Good","unixReviewTime":1400630400,"reviewTime":"05 21, 2014"}
				//QuesAns - element: {"questionType":"yes/no","asin":"1466736038","answerTime":"Mar 8, 2014","unixTime":1394265600,"question":"Is there a SIM card in it?","answerType":"Y","answer":"Yes. The Galaxy SIII accommodates a micro SIM card."}
				
				JsonObject object = element.getAsJsonObject();
				//System.out.println("object: " + object); // checking JsonObject
				//object: {"reviewerID":"A2RF9FHC4HC3JO","asin":"8288855504","reviewerName":"E. Hall","helpful":[0,0],"reviewText":"I bought this for my Samsung Charge. It works perfectly and is built as solidly as the $20 Verizon charger. I highly recommend this car charger and thanks for the great price!","overall":5.0,"summary":"Great product - Great price","unixReviewTime":1307145600,"reviewTime":"06 4, 2011"}
				
				//JsonElement nameElement = object.get("asin");
				//System.out.println("nameElelemt: " + nameElement);  //checking asin
				//nameElelemt: "1466736038"
				
				//sending JsonObject into the data type sorter method and beyond
				this.FileTypeSorter(object);
				
				
			}
			return true; //indicate all records of file read and sent to DataSorter
		}
		catch(IOException ioe)	{
			System.out.println(ioe.getMessage());
			System.out.println(ioe.getStackTrace());
		}
		
		return false;
		
	}
	
	private void FileTypeSorter(JsonObject object)	{
		
	}
	
	public static void main(String[] args) {
		
		//Testing class JsonReader here
		
		JsonReader jr = new JsonReader("reviews_Cell_Phones_and_Accessories_5_sample.json");
		jr.readNow("reviews_Cell_Phones_and_Accessories_5_sample.json");
		
		//JsonReader jr = new JsonReader("qa_Cell_Phones_and_Accessories_sample.json");
		//jr.readNow("qa_Cell_Phones_and_Accessories_sample.json");
	}

}
