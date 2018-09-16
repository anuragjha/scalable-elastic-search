/**
 * 
 */
package cs601.project1;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.google.gson.JsonParser;

/**
 * @author anuragjha
 * JsonHandler Class has methods to process Json Files
 */
public class JsonHandler {

	
	//public JsonHandler(String inputFile)	{
	//	this.jsonFileReader(inputFile);
	//}
	
	
	/**
	 * JsonHandler method takes file as input and returns List of JsonObjects. Method reads the file
	 * line by line and creates JsonObject. 
	 * @param inputFile
	 * @return List of Json Objects
	 */
	public static List<String> jsonFileReader(String inputFile)	{
		
		JsonParser parser = new JsonParser();
		Path path = Paths.get(inputFile);
		
		//List<String> allRecords = new LinkedList<String>();

	//	try(
				//BufferedReader reader = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"));
				//BufferedReader reader = Files.newBufferedReader(path, Charset.forName("ISO-8859-1"));
				//FileReader reader = Files.newFileRead(inputFile, Charset.forName("ISO-8859-1"))
				//FileReader fileReader = new FileReader(reader);
		//	)			
		//			{
			//String lines;
			
			//while((line = reader.readLine()) != null)	{
		List<String> allRecords = new LinkedList<String>();
				try {
					//JsonElement element = parser.parse(line);
					allRecords = Files.readAllLines(Paths.get(inputFile), Charset.forName("ISO-8859-1"));
					//JsonObject object =  parser.parse(line).getAsJsonObject();
				//	allRecords.add(object);
					
			//	} catch(JsonSyntaxException jse)	{
			//		System.out.println("Skipping line ...");
			//	}
		//}	
			
		}	catch(IOException ioe)	{
			System.out.println("Could not process the files");
			System.out.println("Exiting System");
			System.exit(0);
		}
				return allRecords;
				
		
	}
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
