package cs601.project1;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;

///derived heavily out of AmazonWords class
// Class creates user output ///invertedIndexValues
public class AmazonWordDetails {

	private LinkedHashMap<Integer, Integer> invertedIndexValues;
	private boolean isSorted;
	
	public AmazonWordDetails()	{
		invertedIndexValues = new LinkedHashMap<Integer, Integer>();
		isSorted = false;
	}
	

	/**
	 * @return the sortedOutput
	 */
	public LinkedHashMap<Integer, Integer> getSortedOutput() {
		return invertedIndexValues;
		//return createSortedOutput();
	}


	/**
	 * createCortedOutput method takes in Hashmap and returns a sorted Linked Hashmap of Details of a word
	 * @param wordDetails
	 * @return sortedOutput
	 */
	public LinkedHashMap<Integer, Integer> createSortedOutput()	{

		//Sort based on the enrtyset for the map
		//if(!this.sortedOutput.isEmpty())	{
		//	this.sortedOutput.clear();
		//}
		if(!this.isSorted)	{ //if invertedIndex values are not sorted
			
			LinkedList<Map.Entry<Integer,Integer>> sortedInvertedIndexValues = 
					new LinkedList<Map.Entry<Integer,Integer>>(this.invertedIndexValues.entrySet());

			
			Collections.sort(sortedInvertedIndexValues, new Comparator<Map.Entry<Integer,Integer>>() {
				public int compare(Map.Entry<Integer,Integer> r1,Map.Entry<Integer,Integer> r2){
					return r2.getValue().compareTo(r1.getValue());
				}
			});


			this.invertedIndexValues.clear();
			for(Map.Entry<Integer, Integer> thisRecord : sortedInvertedIndexValues)	{
				//this.getSortedOutput().clear();
				this.invertedIndexValues.put(thisRecord.getKey(), thisRecord.getValue());
			}
			//System.out.println("Sorted List :"+ sortedWordDetails.toString());
			this.isSorted = true;
			return this.invertedIndexValues;
		}
		else	{ //if invertedIndexValues sorted
			
			return this.invertedIndexValues;
			
		}

	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
