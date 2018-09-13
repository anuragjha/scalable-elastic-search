/**
 * 
 */
package cs601.project1;

import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author anuragjha
 *
 */

/* https://github.com/srollins/software-dev-materials/blob/master/notes/advanced/datastructures.md
 * -- changing hashmap to linkedHashMap
 * ==>> will have to implement sort at insertion 
 * 
 * https://docs.oracle.com/javase/8/docs/api/java/util/LinkedHashMap.html
 * ===>> convert linkedhashmap to hashmap to get values - coz get() is order of 1
 * 
 */
public class InvertedIndex implements Comparator<Map.Entry<String,Integer>>  {
	//+++++++// checking for comparator
	//private HashMap<String, Integer> invertedIndexValues = new HashMap<String, Integer>() ;
	private HashMap<String, HashMap<String,Integer>> invertedIndex;
	//private HashMap<String, Integer> invertedIndexValues;

	public InvertedIndex()	{
		this.invertedIndex = new HashMap<String, HashMap<String,Integer>>();
	}


	/**
	 * @return the invertedIndex
	 */
	public HashMap<String, HashMap<String, Integer>> getIndex() {
		return invertedIndex;
	}




	public void getTextString(String newString, String recordId)	{
		String[] newWordStringArray = newString.split(" ");
		for(String word : newWordStringArray)	{
			if(word != null && word != "" )
				add(word.replaceAll("[^A-Za-z0-9]", "").toLowerCase(), recordId);
		}
	}

	private void add(String word, String recordId)	{
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
			HashMap<String, Integer> invertedIndexValues = new HashMap<String, Integer>();	
			invertedIndexValues.put(recordId, 1);
			this.invertedIndex.put(word, invertedIndexValues);
		}
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InvertedIndex ir = new InvertedIndex();
		ir.getTextString("This Text is new .. whaat ??", "rv1");
		ir.getTextString("this te'xt was new .. what ??", "rv2");
		ir.getTextString("Th-is Text was old .. whaat ??", "rv3");
		ir.getTextString("This Text is old .. whaat ??", "rv4");
		ir.getTextString("This This is n:ew .. whaat ??", "rv5");

		System.out.println("InvertedIndex: " + ir.getIndex().toString() );


	}

	/*
	 * 	/**/
	// declaration from eclipse
	@Override
	public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {

		return o2.getValue() - (o1.getValue());

	}





}
