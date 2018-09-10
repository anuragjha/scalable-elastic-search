/**
 * 
 */
package cs601.project1;

import java.util.HashMap;

/**
 * @author anuragjha
 *
 */

//https://docs.oracle.com/javase/8/docs/api/java/util/SortedMap.html
//https://docs.oracle.com/javase/8/docs/api/java/util/SortedMap.html#comparator--
public class AmazonWords {

	//objects of Amazonwords are to be value of hashmap<word, AmazonWord object>
	//and AmazonWord can hold the details of recId and count - a list of it - a sorted list ***
	//AmazonWords holds data to document and word frequency 
	//so it will have a vraiable  = word  enum ?
	//anycase another variable is pair of recid,count
	private final String word;   //inverted index key
	
	//private String recordId;
	//private int wordFreq;
	//private Hashmap 
	//private HashMap<recordId, wordFreq> invertedIndexValues;
	//sorted HashMap ??
	private HashMap<String, Integer> invertedIndexValues;
	//private HashMap<word, invertedIndexValues>
	
	public AmazonWords(String word)	{  //new word in wordDataStore
		super();
		this.word = word;
	}
	
	public AmazonWords(String word, String recordId)	{  //new word in wordDataStore
		super();
		this.word = word;
		//this.recordId = recordId;
		//this.wordFreq = 1;/				//recId, count//
		this.invertedIndexValues = new HashMap<String, Integer>();
		//add //
		invertedIndexValues.put(recordId, 1);
	}
	
	public void updateInvertedIndexValues(String newWord, String newRecordId)	{ //old word in wordDataStore
		
		if(this.invertedIndexValues.containsKey(newRecordId))	{
			//record id alrady there for this word
			int currentRecFreq = this.invertedIndexValues.get(newRecordId);
			currentRecFreq += 1;
			this.invertedIndexValues.put(newRecordId, currentRecFreq);	
		}
		else	{
			//new recordId
			this.invertedIndexValues.put(newRecordId, 1);
		}
	}
	
	
	
	
	//getter for invertedIndexvalues
	/**
	 * @return the invertedIndexValues
	 */
	public HashMap<String, Integer> getInvertedIndexValues() {
		return invertedIndexValues;
	}
	

	//private void setInvertedIndexValues()	{
		
	//}
	
	
	
	//so best map for holding sorted data ? - implement sortable hashmap
	//entending comparable
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
