package cs601.project1;

import java.util.HashMap;
import java.util.LinkedList;


/**
 * @author anuragjha
 *	AmazonDataStore class contains all the Data Structure to hold Record details and Word details
 *  and contain methods to update those data structure
 */
public enum AmazonDataStore1 {

	ONE;

	// key - recId, value - AmazonObject
	HashMap<Integer, AmazonReviews> reviewDataStore = new HashMap<Integer, AmazonReviews>();
	HashMap<Integer, AmazonQuesAns> quesAnsDataStore = new HashMap<Integer, AmazonQuesAns>();


	// key - word, value - list of recordIds and frequency 
	InvertedIndex reviewWordDataStore = new InvertedIndex();
	InvertedIndex quesAnsWordDataStore = new InvertedIndex();


	/**
	 * newRecord method is called via notifyDataStore method of AmazonReviews object
	 * This method process the new record to 2 Review DataStores
	 * @param newRecord
	 */
	public void newRecord(AmazonReviews newRecord)	{
		processNewRecord(newRecord);
		
	}
	
	/**
	 * processNewRecord method implements update of Review DataStores for each new Record
	 * @param newRecord
	 */
	private void processNewRecord(AmazonReviews newRecord)	{
		// update 2 data store for Reviews
		//updating reviewDataStore
		reviewDataStore.put(newRecord.getRecordId(), newRecord);
		//updating reviewWordDataStore
		this.reviewWordDataStore.getTextStringAndAddWords(
				newRecord.getStringText(), newRecord.getRecordId());
	}
	
	
	/**
	 * newRecord method is called via notifyDataStore method of AmazonQuesAns object
	 * This method process the new record to 2 QuesAns DataStores
	 * @param newRecord
	 */
	public void newRecord(AmazonQuesAns newRecord)	{
		processNewRecord(newRecord);
	}
	
	/**
	 * processNewRecord method implements update of QuesAns DataStores for each new Record
	 * @param newRecord
	 */
	private void processNewRecord(AmazonQuesAns newRecord)	{
		// create/update 2 data store for Reviews
		//updateQuesAnsDataStore(newRecord); //cast to AmazonReviews
		quesAnsDataStore.put(newRecord.getRecordId(), newRecord);
		//updateQuesAnsWordDataStore(newRecord); //** do this after quesAns data store is built
		this.quesAnsWordDataStore.getTextStringAndAddWords(
				newRecord.getStringText(), newRecord.getRecordId());
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
