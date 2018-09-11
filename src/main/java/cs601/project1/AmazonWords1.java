/**
 * 
 */
package cs601.project1;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * @author anuragjha
 *
 */

//https://docs.oracle.com/javase/8/docs/api/java/util/SortedMap.html
//https://docs.oracle.com/javase/8/docs/api/java/util/SortedMap.html#comparator--
public class AmazonWords1 {

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
	private TreeMap<String, Integer> invertedIndexValues;
	//private HashMap<word, invertedIndexValues>
	
	public AmazonWords1(String word)	{  //new word in wordDataStore
		super();
		this.word = word;
	}
	
	public AmazonWords1(String word, String recordId)	{  //new word in wordDataStore
		super();
		this.word = word;
		//this.recordId = recordId;
		//this.wordFreq = 1;/				//recId, count//
		this.invertedIndexValues = new TreeMap<String, Integer>(new ComparingFreq());
		//add // !!! sorting 
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
	public TreeMap<String, Integer> getInvertedIndexValues() {
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
	
	
	class ComparingFreq implements Comparator<String>	{
		
		public int compare(String recId1, String recId2)	{ 
			if(invertedIndexValues.size() >= 2)	{     // handling null pointer exception
				int freq1 = invertedIndexValues.get(recId1) ;
				int freq2 = invertedIndexValues.get(recId2);
				if(freq2 > freq1)	{
					return 1;
				} else if(freq2 < freq1)	{
					return -1;
				}	else	{
					return 1;
				}
			}
			return 0;
		
		}
		
		
	}


}
