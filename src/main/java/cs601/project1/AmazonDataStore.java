package cs601.project1;

import java.util.HashMap;


/**
 * @author anuragjha
 *	AmazonDataStore class contains all the Data Structure to hold Record details and Word details
 *  and contain methods to update those data structure, also a AmazonDataStoreSearcher Object
 */
public enum AmazonDataStore {

	ONE;

	private AmazonDataStoreSearcher searcher = new AmazonDataStoreSearcher();


	// key - recId, value - AmazonObject
	private HashMap<Integer, AmazonReviews> reviewDataStore = new HashMap<Integer, AmazonReviews>();
	private HashMap<Integer, AmazonQuesAns> quesAnsDataStore = new HashMap<Integer, AmazonQuesAns>();


	// key - word, value - list of recordIds and frequency 
	private InvertedIndex reviewWordDataStore = new InvertedIndex();
	private InvertedIndex quesAnsWordDataStore = new InvertedIndex();


	/**
	 * @return the searcher
	 */
	public AmazonDataStoreSearcher getSearcher() {
		return searcher;
	}

	/**
	 * @return the reviewDataStore
	 */
	public HashMap<Integer, AmazonReviews> getReviewDataStore() {
		return reviewDataStore;
	}

	/**
	 * @return the quesAnsDataStore
	 */
	public HashMap<Integer, AmazonQuesAns> getQuesAnsDataStore() {
		return quesAnsDataStore;
	}

	/**
	 * @return the reviewWordDataStore
	 */
	public InvertedIndex getReviewWordDataStore() {
		return reviewWordDataStore;
	}

	/**
	 * @return the quesAnsWordDataStore
	 */
	public InvertedIndex getQuesAnsWordDataStore() {
		return quesAnsWordDataStore;
	}

	/**
	 * newRecord method is called via notifyDataStore method of Amazon objects - AmazonReviews and AmazonQuesAns
	 * This method process the new record to 2 Review DataStores
	 * @param newRecord
	 */
	public void newRecord(AmazonObject newRecord)	{
		if(newRecord instanceof AmazonReviews)	{
			processNewRecord((AmazonReviews)newRecord);
		}
		else if(newRecord instanceof AmazonQuesAns)	{
			processNewRecord((AmazonQuesAns)newRecord);
		}

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
