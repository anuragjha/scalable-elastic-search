/**
 * 
 */
package cs601.project1;

import java.util.Collection;
import java.util.HashMap;

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
 */
public enum AmazonDataStore {

	ONE;

	

	//private boolean isRecordDataStoreBuilt = false;
	// key - recId, value - AmazonObject
	HashMap<Integer, AmazonReviews> reviewDataStore = new HashMap<Integer, AmazonReviews>();
	HashMap<Integer, AmazonQuesAns> quesAnsDataStore = new HashMap<Integer, AmazonQuesAns>();


	// key - word, value - list of AmazonWords (Sorted by word frequency)
	InvertedIndex reviewWordDataStore = new InvertedIndex();
	InvertedIndex quesAnsWordDataStore = new InvertedIndex();

	/**
	 * This method is called via notifydatastore method of AmazonReviews object
	 * @param newRecord
	 */
	public void newRecord(AmazonReviews newRecord)	{
		processNewRecord(newRecord);
	}
	
	private void processNewRecord(AmazonReviews newRecord)	{
		// create/update 2 data store for Reviews
		updateReviewDataStore(newRecord); //cast to AmazonReviews
		//updateReviewAsinDataStore(newRecord);
		updateReviewWordDataStore(newRecord);  //** do this after review data store is built
	}
	
	
	/**
	 * updateReviewDataStore method updates  datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateReviewDataStore(AmazonReviews newRecord)	{
		reviewDataStore.put(newRecord.getRecordId(), newRecord);
	}

	
	
	/**
	 * updateReviewWordDataStore method updates Word datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateReviewWordDataStore(AmazonReviews newReviewRecord)	{

		this.reviewWordDataStore.getTextString(
				newReviewRecord.getStringText(), newReviewRecord.getRecordId());
	}
	
	
	public void newRecord(AmazonQuesAns newRecord)	{
		processNewRecord(newRecord);
	}
	
	
	private void processNewRecord(AmazonQuesAns newRecord)	{
		// create/update 2 data store for Reviews
		updateQuesAnsDataStore(newRecord); //cast to AmazonReviews
		updateQuesAnsWordDataStore(newRecord); //** do this after quesAns data store is built
	}




	/**
	 * updateQuesAnsAsinDataStore method updates datastore for AmazonQuesAns
	 * @param newReviewRecord
	 */	
	private void updateQuesAnsDataStore(AmazonQuesAns newRecord)	{

		quesAnsDataStore.put(newRecord.getRecordId(), newRecord);

	}

	
	/**
	 * updateReviewWordDataStore method updates Word datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateQuesAnsWordDataStore(AmazonQuesAns newQuesAnsRecord)	{
		
		this.quesAnsWordDataStore.getTextString(
				newQuesAnsRecord.getStringText(), newQuesAnsRecord.getRecordId());
	}
	
	
	//**** method to build Review Word data store
//	public void buildRevwWordDataStore()	{
//		Collection<AmazonReviews> records = AmazonDataStore.ONE.reviewDataStore.values();
//		for(AmazonReviews newRecord : records)	{
//			updateReviewWordDataStore(newRecord);
//		}
//	}
	
	
	//**** method to build quesAns Word data store
//	public void buildQusAnsWordDataStore()	{
//		Collection<AmazonQuesAns> records = AmazonDataStore.ONE.quesAnsDataStore.values();
//		for(AmazonQuesAns newRecord : records)	{
//			updateQuesAnsWordDataStore(newRecord);
//		}
//	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
