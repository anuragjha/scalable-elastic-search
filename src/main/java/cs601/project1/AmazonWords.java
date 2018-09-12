/**
 * 
 */
package cs601.project1;

import java.util.HashMap;

/**
 * @author anuragjha
 *  AmazonWords class keeps a hashmap of reciID and count for a particular word
 */

public class AmazonWords {
	//private HashMap<word, invertedIndexValues> --- implementing invertedIndexValues
	//private final String word;
	private HashMap<String, Integer> invertedIndexValues;
	
	
	public AmazonWords()	{  //new word in wordDataStore
		super();
		///********this.word = word;
		//wordFreq = 1;/				//recId, count//
		this.invertedIndexValues = new HashMap<String, Integer>();
	}
	
	public AmazonWords(String recordId)	{  //new word in wordDataStore
		super();
		///********this.word = word;
		//wordFreq = 1;/				//recId, count//
		this.invertedIndexValues = new HashMap<String, Integer>();
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
	public HashMap<String, Integer> getInvertedIndexValues() {
		return invertedIndexValues;
	}
	

	//private void setInvertedIndexValues()	{
		
	//}
	
	
	
	//so best map for holding sorted data ? - implement sortable hashmap - treemap ///
	//entending comparable
	

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


}
