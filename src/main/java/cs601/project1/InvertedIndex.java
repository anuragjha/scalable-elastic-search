/**
 * 
 */
package cs601.project1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author anuragjha
 * InvertedIndex class stores word as key and keeps track of recordId where the word occurs 
 * and its frequency
 */

/* https://github.com/srollins/software-dev-materials/blob/master/notes/advanced/datastructures.md
 * -- changing hashmap to linkedHashMap
 * ==>> will have to implement sort at insertion 
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
 * ===>> convert linkedhashmap to hashmap to get values - coz get() is order of 1
 * 
 */
public class InvertedIndex implements Comparator<Map.Entry<Integer,Integer>>  {
	//+++++++// checking for comparator

	private HashMap<String, HashMap<Integer,Integer>> invertedIndex;
	//+++++private HashMap<String, LinkedList<AmazonWordDetails>> invertedIndex;

	public InvertedIndex()	{
		this.invertedIndex = new HashMap<String, HashMap<Integer,Integer>>();
	}


	/**
	 * @return the invertedIndex
	 */
	public HashMap<String, HashMap<Integer, Integer>> getIndex() {
		return invertedIndex;
	}

	/**
	 * getTextString method breaks the TextString into words and sends it to add method
	 * @param newString
	 * @param recordId
	 */
	public void getTextString(String newString, int recordId)	{
		String[] newWordStringArray = newString.split(" ");
		for(String word : newWordStringArray)	{
			if((word != null) && (word != ""))	{
				String newWord = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
				if(newWord.length() > 0)	{
					//System.out.println("word:"+newWord);
					add(newWord, recordId);
				}
			}
		}
	}

	
	
	/**
	 * add method takes in a word and recordId adds it into a InvertedIndex
	 * @param word
	 * @param recordId
	 */
	private void add(String word, int recordId)	{
		if(this.invertedIndex.containsKey(word))	{ // key - word is already present
			if(this.invertedIndex.get(word).containsKey(recordId))	{ //recordId is already present
				int currentFreq = this.invertedIndex.get(word).get(recordId);
				currentFreq += 1;
				this.invertedIndex.get(word).put(recordId, currentFreq);
			}
			else	{  //recordId is not present/// but word is present
				this.invertedIndex.get(word).put(recordId, 1);
			}
		}
		else	{   // key - word is not present 
			HashMap<Integer, Integer> invertedIndexValues = new HashMap<Integer, Integer>();	
			invertedIndexValues.put(recordId, 1);
			this.invertedIndex.put(word, invertedIndexValues);
		}
	}


	/**
	 * searchWord method takes in a word and returns the recordIds associated with the word
	 * @param word
	 * @return keySet
	 */
	public HashMap<Integer, Integer> searchWord(String word)	{

		// build a datatype for this ==> this.invertedIndex.get(word).keySet() to have sorted result
		return this.invertedIndex.get(word);
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InvertedIndex ir = new InvertedIndex();
		//ir.getTextString("This Text is new .. whaat ??", 1);
		//ir.getTextString("this te'xt was new .. what ??", 2);
		//ir.getTextString("Th-is Text was old .. whaat ??", 3);
		//ir.getTextString("This Text is old .. whaat ??", 4);
		//ir.getTextString("This This is n:ew .. whaat ??", 5);

		System.out.println("InvertedIndex: " + ir.getIndex().toString() );


	}

	/*
	 * 	/**/
	// declaration from eclipse
	@Override
	public int compare(Entry<Integer, Integer> o1, Entry<Integer, Integer> o2) {

		return o2.getValue() - (o1.getValue());

	}





}
