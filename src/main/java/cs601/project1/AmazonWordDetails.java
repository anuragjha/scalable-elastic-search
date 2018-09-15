package cs601.project1;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

///derived heavily out of AmazonWords class
// Class creates user output
public class AmazonWordDetails {

	private static LinkedHashMap<Integer, Integer> sortedOutput = new LinkedHashMap<Integer, Integer>();



	/**
	 * createCortedOutput method takes in Hashmap and returns a sorted Linked Hashmap of Details of a word
	 * @param wordDetails
	 * @return sortedOutput
	 */
	public LinkedHashMap<Integer, Integer> createSortedOutput( HashMap<Integer, Integer> wordDetails)	{

		//Sort based on the enrtyset for the map
		if(!AmazonWordDetails.sortedOutput.isEmpty())	{
			AmazonWordDetails.sortedOutput.clear();
		}

		LinkedList<Map.Entry<Integer,Integer>> sortedWordDetails = 
				new LinkedList<Map.Entry<Integer,Integer>>(wordDetails.entrySet());

		Collections.sort(sortedWordDetails, new Comparator<Map.Entry<Integer,Integer>>() {
			public int compare(Map.Entry<Integer,Integer> r1,Map.Entry<Integer,Integer> r2){
				return r2.getValue().compareTo(r1.getValue());
			}
		});

		

		for(Map.Entry<Integer, Integer> thisRecord : sortedWordDetails)	{
			AmazonWordDetails.sortedOutput.put(thisRecord.getKey(), thisRecord.getValue());
		}
		System.out.println("Sorted List :"+ sortedWordDetails.toString());
		return AmazonWordDetails.sortedOutput;

	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
