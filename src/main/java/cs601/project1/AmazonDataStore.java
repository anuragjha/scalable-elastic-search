/**
 * 
 */
package cs601.project1;

import java.util.LinkedList;

/**
 * @author anuragjha
 *	AmazonDataStore class is parent class for all data stores used
 *  takes the record, process it and update datastore
 */

/*
 * https://crunchify.com/hashmap-vs-concurrenthashmap-vs-synchronizedmap-how-a-hashmap-can-be-synchronized-
 * in-java/
 * https://github.com/srollins/software-dev-materials/blob/master/notes/advanced/datastructures.md
 * -- changing hashmap to linkedHashMap
 * =====> will have to implement sorting at insertion
 * 
 * 
 * 
 * 
 * 
 * 
 */
public enum AmazonDataStore {

	ONE;

	// key - recId, value - AmazonObject

	//---converting dataset to hashset
	//HashMap<String, LinkedList<AmazonReviews>> reviewAsinDataStore = 
	//		new HashMap<String, LinkedList<AmazonReviews>>();
	LinkedList<AmazonReviews> reviewDataStore = new LinkedList<AmazonReviews>();



	//HashMap<String, LinkedList<AmazonQuesAns>> quesAnsAsinDataStore = 
	//		new HashMap<String, LinkedList<AmazonQuesAns>>();
	LinkedList<AmazonQuesAns> quesAnsDataStore = new LinkedList<AmazonQuesAns>();


	// key - word, value - list of AmazonWords (Sorted by word frequency)
	/* old
	HashMap<String, AmazonWords> reviewWordDataStore = 
			new HashMap<String, AmazonWords>(); 
	 */
	//**// - every object of amazonword will have "word" as LHS of =
	//creating from generic instance
	InvertedIndex reviewWordDataStore = new InvertedIndex();

	//LinkedList quesAnsWordDataStore = new LinkedList();
	//HashMap<word, list of recid-count>
	/* old
	HashMap<String, AmazonWords> quesAnsWordDataStore = 
			new HashMap<String, AmazonWords>();
	 */
	InvertedIndex quesAnsWordDataStore = new InvertedIndex();

	/**
	 *  is called by each Amazon record to notify data store 
	 * @param newRecord
	 */
	
	public void newRecord(AmazonReviews newRecord)	{
		processNewRecord(newRecord);
	}
	
	public void newRecord(AmazonQuesAns newRecord)	{
		processNewRecord(newRecord);
	}
	
	
	private void processNewRecord(AmazonReviews newRecord)	{
		// create/update 2 data store for Reviews
		updateReviewDataStore(newRecord); //cast to AmazonReviews
		updateReviewWordDataStore(newRecord);
	}
	
	
	private void processNewRecord(AmazonQuesAns newRecord)	{
		// create/update 2 data store for Reviews
		updateQuesAnsDataStore(newRecord); //cast to AmazonReviews
		updateQuesAnsWordDataStore(newRecord);
	}

	/**
	 * updateReviewAsinDataStore method updates Asin datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateReviewDataStore(AmazonReviews newReviewRecord)	{
		reviewDataStore.add(newReviewRecord);
	}




	/**
	 * updateQuesAnsAsinDataStore method updates Asin datastore for AmazonQuesAns
	 * @param newReviewRecord
	 */	

	//*****// updating asin datastore 
	private void updateQuesAnsDataStore(AmazonQuesAns newReviewRecord)	{

		quesAnsDataStore.add(newReviewRecord);

	}



	/**
	 * updateReviewWordDataStore method updates Word datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateReviewWordDataStore(AmazonReviews newReviewRecord)	{

		//** stmts for the new index **//
		reviewWordDataStore.getTextString(newReviewRecord.getStringText(), newReviewRecord.getRecordId());
	}



	//QuesAns
	/**
	 * updateReviewWordDataStore method updates Word datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateQuesAnsWordDataStore(AmazonQuesAns newQuesAnsRecord)	{

		quesAnsWordDataStore.getTextString(newQuesAnsRecord.getTextString(), newQuesAnsRecord.getRecordId());
		
	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
