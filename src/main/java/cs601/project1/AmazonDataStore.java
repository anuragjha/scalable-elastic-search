/**
 * 
 */
package cs601.project1;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * @author anuragjha
 *	AmazonDataStore class is parent class for all data stores used
 *  takes the record, process it and update datastore
 */
public enum AmazonDataStore {

	ONE;

	// key - asin, value - AmazonObject
	HashMap<String, LinkedList<AmazonReviews>> reviewAsinDataStore = 
			new HashMap<String, LinkedList<AmazonReviews>>();
	
	HashMap<String, LinkedList<AmazonQuesAns>> quesAnsAsinDataStore = 
			new HashMap<String, LinkedList<AmazonQuesAns>>();

	//LinkedList amazonObjectList = new LinkedList();
	// key - word, value - list of AmazonWords (Sorted by word frequency)
	HashMap<String, AmazonWords> reviewWordDataStore = 
			new HashMap<String, AmazonWords>();
	//**// - every object of amazonword will have "word" as LHS of =
	
	//LinkedList quesAnsWordDataStore = new LinkedList();
	//HashMap<word, list of recid-count>
	HashMap<String, LinkedList<AmazonWords>> quesAnsWordDataStore = 
			new HashMap<String, LinkedList<AmazonWords>>();

	/**
	 *  is called by each Amazon record to notify data store 
	 * @param newRecord
	 */
	public void newRecord(AmazonObject newRecord)	{
		//data store now knows that there is new record to process
		processNewRecord(newRecord);

	}

	/**
	 * is used by AmazonDataStore class to create/update its data stores
	 * @param newRecord
	 */
	private void processNewRecord(AmazonObject newRecord)	{
		if(newRecord instanceof AmazonReviews)	{
			// create/update 2 data store for Reviews
			updateReviewAsinDataStore((AmazonReviews)newRecord); //cast to AmazonReviews
			updateReviewWordDataStore((AmazonReviews)newRecord);
		}
		else if(newRecord instanceof AmazonQuesAns)	{
			// create/update 2 data store for QuesAns
			updateQuesAnsAsinDataStore((AmazonQuesAns)newRecord);
			////updateQuesAnsWordDataStore();
		}
	}
	
	
	
	/**
	 * updateReviewAsinDataStore method updates Asin datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateReviewAsinDataStore(AmazonReviews newReviewRecord)	{
		if(AmazonDataStore.ONE.reviewAsinDataStore.containsKey(newReviewRecord.getAsin()))	{
			//means asin is already in datastore	
			LinkedList<AmazonReviews> amazonRecords = 
					AmazonDataStore.ONE.reviewAsinDataStore.get(newReviewRecord.getAsin());
			amazonRecords.add(newReviewRecord);
			AmazonDataStore.ONE.reviewAsinDataStore.put(newReviewRecord.getAsin(), amazonRecords);
		}
		else	{
			// asin in not in datastore		
			LinkedList<AmazonReviews> amazonRecords = new LinkedList<AmazonReviews>();
			amazonRecords.add(newReviewRecord);  //adding Review record in new LinkedList
			AmazonDataStore.ONE.reviewAsinDataStore.put(newReviewRecord.getAsin(), amazonRecords);
		}
	}
	
	

	
	/**
	 * updateQuesAnsAsinDataStore method updates Asin datastore for AmazonQuesAns
	 * @param newReviewRecord
	 */	
	private void updateQuesAnsAsinDataStore(AmazonQuesAns newReviewRecord)	{
		if(AmazonDataStore.ONE.quesAnsAsinDataStore.containsKey(newReviewRecord.getAsin()))	{
			//means asin is already in datastore
			LinkedList<AmazonQuesAns> amazonRecords = 
				AmazonDataStore.ONE.quesAnsAsinDataStore.get(newReviewRecord.getAsin());
			amazonRecords.add(newReviewRecord);
			AmazonDataStore.ONE.quesAnsAsinDataStore.put(newReviewRecord.getAsin(), amazonRecords);
		}
		else	{
			// asin is not in datastore
			LinkedList<AmazonQuesAns> amazonRecords = new LinkedList<AmazonQuesAns>();
			amazonRecords.add(newReviewRecord);
			AmazonDataStore.ONE.quesAnsAsinDataStore.put(newReviewRecord.getAsin(), amazonRecords);
			
		}
	}
	
	
	
	/**
	 * updateReviewWordDataStore method updates Word datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateReviewWordDataStore(AmazonReviews newReviewRecord)	{
		String[] words = newReviewRecord.getWords().split(" "); //spliting words in record to array
		for(String word : words)	{
			if(AmazonDataStore.ONE.quesAnsWordDataStore.containsKey(word))	{
				//word already present in data store
				//LinkedList<AmazonWord> amazonWords = 
				//		AmazonDataStore.ONE.reviewAsinDataStore.get(word);
				//amazonWords.add(newReviewRecord.)
				
				//conditions to check if recordId is already present or do we have to create new entry
				//for this recordId
				AmazonWords thisAmazonWord = 
						AmazonDataStore.ONE.reviewWordDataStore.get(word);
				boolean recordIdCheck = 
						thisAmazonWord.getInvertedIndexValues().containsKey(newReviewRecord.getRecordId());
				if(recordIdCheck)	{ //word has this recordId already - so just increment the count
					thisAmazonWord.getInvertedIndexValues().put(key, 1)
				}
				else	{ //word does not have this recordId - so add the recordId in invertedIndexValues
					
				}
				
			}
			else	{
				// new word for data store
				AmazonWords thisAmazonWord = new AmazonWords(word, newReviewRecord.getRecordId());
				//LinkedList<AmazonWords> amazonWord = new LinkedList<AmazonWords>();
				//amazonWord.add(thisAmazonWord); // adding AmazonWord in the list
				AmazonDataStore.ONE.reviewWordDataStore.put(word, thisAmazonWord);
			}
		}
	
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
