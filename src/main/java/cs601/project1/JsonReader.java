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

	private String thisFileIs;
	
	/**
	 * Constructor
	 */
	public JsonReader()	{
		super();
		
	}
	/**
	 * Constructor
	 * @param inputfile
	 * Method readNow takes Json file as input and sends it to appropriate class
	 * 
	 */ 
	//	 * ? is it good practice to put reader in constructor 
	public JsonReader(String inputFile)	{
		super();
	}
	
	/**
	 * 
	 * @param inputFile
	 * @return boolean
	 */
	//	 * ? is it a good practice to return boolean from methods that dont 
	// necessarily have to return anything
	public boolean readNow(String inputFile)	{
		
		//String thisFileIs;
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
				this.FileTypeSorter(object); //can check inputFile string to save
				//from calling he function for every record and insted call for every file
				//should i declare all the object variables here ?
				
				
			}
			return true; //indicate all records of file read and sent to DataSorter
		}
		catch(IOException ioe)	{
			System.out.println("IOE exception raised");
			System.out.println(ioe.getMessage());
			System.out.println(ioe.getStackTrace());
			return false;
		}
		
	}
	
	private void FileTypeSorter(JsonObject object)	{
		
		//if nameElement == reviewerID
		//		send to ReviewsData
		//if nameElement == questionType
		//		send to QuesAnsData
		JsonElement nameElement; //cannot declare under if stmt
		if((nameElement = object.get("reviewerID")) != null)	{
			
			System.out.println("it is review object");
			readReviewsData(object);
			//return "Review";
			
		}
		else
		if((nameElement = object.get("questionType")) != null)	{
			
			System.out.println("QuestionType");
			readQuesAnsData(object);
			//return "QuesAns";
			
		}
		else	{
			System.out.println("Unreadable data format");
			//return "Dontknow";
		}
		
		
	}
	
	private void readReviewsData(JsonObject object)	{
		
		//Reviews object ->
		//reviewerID, asin, reviewerName, helpful, reviewText, overall, summary, 
		//unixReviewTime, reviewTime
		System.out.println("inside review reader");
		//JsonElement nameElement;
		//nameElement = object.get("reviewerID");
		//String reviewerID = nameElement.getAsString();
		String reviewerID = object.get("reviewerID").getAsString(); //can directly do this
		String asin = object.get("asin").getAsString();
		String reviewerName; //needed coz of line 270 in json file
		if(object.get("reviewerName") != null)	{
			reviewerName = object.get("reviewerName").getAsString();
		}
		else	{
			reviewerName = "";
		}
		int[] helpful = new int[2];
		helpful[0] = object.get("helpful").getAsJsonArray().get(0).getAsInt();
		helpful[1] = object.get("helpful").getAsJsonArray().get(1).getAsInt();
		String reviewText = object.get("reviewText").getAsString();
		double overall = object.get("overall").getAsDouble();
		String summary = object.get("summary").getAsString();
		String unixReviewTime = object.get("unixReviewTime").getAsString();
		String reviewTime = object.get("reviewTime").getAsString();
		//to test this method
		//System.out.printf("in review reader: %s, %s, %s, [%d,%d], %s, %f, %s, %s, %s \n", 
		//		reviewerID, asin, reviewerName, helpful[0], helpful[1], reviewText,
		//		overall, summary, unixReviewTime, reviewTime);
		new Reviews(reviewerID, asin, reviewerName, helpful, reviewText, 
				overall, summary, unixReviewTime, reviewTime);
		
	}
	
	private void readQuesAnsData(JsonObject object)	{
		
		//because nameElement was QuesType in FileTypeSorter
		//QuesAns object ->
		//questionType;asin;answerTime;unixTime;question;answerType;answer;
		System.out.println("inside QuesAns reader");
		//JsonElement nameElement;
		//nameElement = object.get("questionType");
		//String questionType = nameElement.getAsString();
		String questionType = object.get("questionType").getAsString(); //can directly do this
		String asin = object.get("asin").getAsString();
		String answerTime = object.get("answerTime").getAsString();
		String unixTime;
		if(object.get("unixTime") != null)	{
			unixTime = object.get("unixTime").getAsString();
		}
		else	{
			unixTime = "";
		}
		String question = object.get("question").getAsString();
		String answerType;
		if(object.get("answerType") != null)	{
			answerType = object.get("answerType").getAsString();
		}
		else	{
			answerType = "";
		}
		String answer = object.get("answer").getAsString();
		System.out.printf("in QuesAns reader: %s, %s, %s, %s, %s, %s, %s \n", 
				questionType, asin, answerTime, unixTime,
				question, answerType, answer);
		new QuesAns(questionType, asin, answerTime, unixTime, question,
				answerType, answer);
	}
	
	public static void main(String[] args) {
		
		//Testing class JsonReader here
		
		JsonReader jr1 = new JsonReader();
		//jr1.readNow("reviews_Cell_Phones_and_Accessories_5.json");
		jr1.readNow("qa_Cell_Phones_and_Accessories.json");
		//JsonReader jr2 = new JsonReader("qa_Cell_Phones_and_Accessories_sample.json");
		//jr2.readNow("qa_Cell_Phones_and_Accessories_sample.json");
	}

}
