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
	/* old
	HashMap<String, LinkedList<AmazonReviews>> reviewAsinDataStore = 
			new HashMap<String, LinkedList<AmazonReviews>>();
	*/
	// key - String, value - String ,  String
	//       asin            recordId, 
	InvertedIndex reviewAsinDataStore = new InvertedIndex();
	
	
	HashMap<String, LinkedList<AmazonQuesAns>> quesAnsAsinDataStore = 
			new HashMap<String, LinkedList<AmazonQuesAns>>();


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
			updateQuesAnsWordDataStore((AmazonQuesAns)newRecord);
		}
	}
	
	
	
	/**
	 * updateReviewAsinDataStore method updates Asin datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateReviewAsinDataStore(AmazonReviews newReviewRecord)	{
		
		
		/* old
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
		*/
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
		
		//** stmts for the new index **//
		reviewWordDataStore.getTextString(newReviewRecord.getStringText(), newReviewRecord.getRecordId());
		
		//**        **//
		/* old
		String[] words = newReviewRecord.getWords().split(" "); //spliting words in record to array
		//System.out.println("words in this review");
		for(String word : words)	{
			///****/// putting logic to transform word here
		/*	word = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
			//System.out.println("word : "+ word);
			if(AmazonDataStore.ONE.reviewWordDataStore.containsKey(word))	{
				//conditions to check if recordId is already present or do we have to create new entry
				//for this recordId
				//System.out.println("inside if");
				AmazonWords thisAmazonWord = 
						AmazonDataStore.ONE.reviewWordDataStore.get(word);
				boolean recordIdCheck = 
						thisAmazonWord.getInvertedIndexValues().containsKey(newReviewRecord.getRecordId());
				if(recordIdCheck)	{ //word has this recordId already - so just increment the count
					thisAmazonWord.getInvertedIndexValues().put(newReviewRecord.getRecordId(), 
							(thisAmazonWord.getInvertedIndexValues().get(newReviewRecord.getRecordId()) + 1) );
				}
				else	{ //word does not have this recordId - so add the recordId in invertedIndexValues
					thisAmazonWord.getInvertedIndexValues().put(newReviewRecord.getRecordId(), 1);
				}
				
			}
			else	{
				// new word for data store
				AmazonWords thisAmazonWord = new AmazonWords(newReviewRecord.getRecordId());
				//LinkedList<AmazonWords> amazonWord = new LinkedList<AmazonWords>();
				//amazonWord.add(thisAmazonWord); // adding AmazonWord in the list
				AmazonDataStore.ONE.reviewWordDataStore.put(word, thisAmazonWord);
			}
		}
	*/
	}
	
	
	
	//QuesAns
	/**
	 * updateReviewWordDataStore method updates Word datastore for AmazonReviews
	 * @param newReviewRecord
	 */
	private void updateQuesAnsWordDataStore(AmazonQuesAns newQuesAnsRecord)	{
		
		String[] words = newQuesAnsRecord.getWords().split(" ");
		for(String word : words)	{
		quesAnsWordDataStore.add(word, newQuesAnsRecord.getRecordId());
		}
		/* old
		String[] words = newQuesAnsRecord.getWords().split(" "); //spliting words in record to array
		//System.out.println("words in this review");
		for(String word : words)	{
			///****/// putting logic to transform word here
		/*	word = word.replaceAll("[^A-Za-z0-9]", "").toLowerCase();
			//System.out.println("word : "+ word);
			if(AmazonDataStore.ONE.quesAnsWordDataStore.containsKey(word))	{
				//conditions to check if recordId is already present or do we have to create new entry
				//for this recordId
				//System.out.println("inside if");
				AmazonWords thisAmazonWord = 
						AmazonDataStore.ONE.quesAnsWordDataStore.get(word);
				boolean recordIdCheck = 
						thisAmazonWord.getInvertedIndexValues().containsKey(newQuesAnsRecord.getRecordId());
				if(recordIdCheck)	{ //word has this recordId already - so just increment the count
					thisAmazonWord.getInvertedIndexValues().put(newQuesAnsRecord.getRecordId(), 
							(thisAmazonWord.getInvertedIndexValues().get(newQuesAnsRecord.getRecordId()) + 1) );
				}
				else	{ //word does not have this recordId - so add the recordId in invertedIndexValues
					thisAmazonWord.getInvertedIndexValues().put(newQuesAnsRecord.getRecordId(), 1);
				}
				
			}
			else	{
				// new word for data store
				AmazonWords thisAmazonWord = 
						new AmazonWords(newQuesAnsRecord.getRecordId());
				//LinkedList<AmazonWords> amazonWord = new LinkedList<AmazonWords>();
				//amazonWord.add(thisAmazonWord); // adding AmazonWord in the list
				AmazonDataStore.ONE.quesAnsWordDataStore.put(word, thisAmazonWord);
			}
		}
	*/
	}

	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
