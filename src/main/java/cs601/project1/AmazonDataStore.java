package cs601.project1;

import java.util.HashMap;
import java.util.Map;


/**
 * @author anuragjha
 *	AmazonDataStore class contains all the Data Structure to hold Record details and Word details
 *  and contain methods to update those data structure
 */
public enum AmazonDataStore {

	ONE;


	// key - recId, value - AmazonObject
	private HashMap<Integer, AmazonReviews> reviewDataStore = new HashMap<Integer, AmazonReviews>();
	private HashMap<Integer, AmazonQuesAns> quesAnsDataStore = new HashMap<Integer, AmazonQuesAns>();


	// key - word, value - list of recordIds and frequency 
	private InvertedIndex reviewWordDataStore = new InvertedIndex();
	private InvertedIndex quesAnsWordDataStore = new InvertedIndex();


	//variables to keep count of search results
	private int resultCount = 0;
	private int partialResultCount = 0;

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


	//adding search methods here

	//finding ASIN
	/**
	 * getAsinFind method is a public method that calls asinFind method
	 * @param cmdTerm
	 */
	public void getAsinFind(String cmdTerm)	{
		this.asinFind(cmdTerm);
	}


	/**
	 * asinFind method finds all the Review and QA record for matching ASIN
	 * @param cmdTerm
	 */
	private void asinFind(String cmdTerm)	{
		this.resultCount = 0;

		for(AmazonReviews review : AmazonDataStore.ONE.reviewDataStore.values())	{
			if(review.getAsin().equalsIgnoreCase(cmdTerm))	{
				this.resultCount += 1;
				System.out.println("\n"+review.toString());
			}
		}

		for(AmazonQuesAns quesAns : AmazonDataStore.ONE.quesAnsDataStore.values())	{
			if(quesAns.getAsin().equalsIgnoreCase(cmdTerm))	{
				this.resultCount += 1;
				System.out.println("\n"+quesAns.toString());
			}
		}

		if(this.resultCount == 0)	{
			System.out.println("No results found");
		}
		else	{
			System.out.println("\nResults found: "+ resultCount+"\n");
		}
	}




	/**
	 * getReviewSearch method is a public method that calls reviewSearch method
	 * @param cmdTerm
	 */
	public void getReviewSearch(String cmdTerm)	{
		this.reviewSearch(cmdTerm);
	}


	/**
	 * reviewSearch method prints out the Review Records that match the term
	 * @param cmdTerm
	 */
	public void reviewSearch(String cmdTerm)	{

		this.resultCount = 0;

		if(AmazonDataStore.ONE.reviewWordDataStore.getIndex().containsKey(cmdTerm))	{ //word in store
			for(Map.Entry<Integer, Integer> recordId :
				AmazonDataStore.ONE.reviewWordDataStore.searchWord(cmdTerm).createSortedOutput().entrySet())	{

				this.resultCount += 1;
				System.out.println("\nSearched term: "+ cmdTerm + "\t|\tFrequency: "+recordId.getValue());
				System.out.println(AmazonDataStore.ONE.reviewDataStore.get(recordId.getKey()).toString());
			}
			System.out.println("\nResults found: "+ this.resultCount+"\n");
		}
		else	{
			System.out.println("No result found");
		}

	}



	/**
	 * getQASearch method is a public method that calls qaSearch method
	 * @param cmdTerm
	 */
	public void getQASearch(String cmdTerm)	{
		this.qaSearch(cmdTerm);
	}


	/**
	 * qaSearch method prints out the QuesAns Records that match the term
	 * @param cmdTerm
	 */
	private void qaSearch(String cmdTerm)	{

		this.resultCount = 0;

		if(AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().containsKey(cmdTerm))	{ //word in store
			for(Map.Entry<Integer, Integer> recordId : 
				AmazonDataStore.ONE.quesAnsWordDataStore.searchWord(cmdTerm).createSortedOutput().entrySet())	{

				this.resultCount += 1;
				System.out.println("\nSearched term: "+ cmdTerm + "\t|\tFrequency: "+recordId.getValue());
				System.out.println(AmazonDataStore.ONE.reviewDataStore.get(recordId.getKey()).toString());
			}
			System.out.println("\nResults found: "+ this.resultCount+"\n");
		}
		else	{
			System.out.println("No result found");
		}

	}



	/**
	 * getReviewPartialSearch method is a public method that calls reviewPartialSearch method
	 * @param cmdTerm
	 */
	public void getReviewPartialSearch(String cmdTerm)	{
		this.reviewPartialSearch(cmdTerm);
	}

	/**
	 * reviewPartialSearch method prints out the Review Records that partially match the term
	 * @param cmdTerm
	 */
	private void reviewPartialSearch(String cmdTerm)	{

		this.partialResultCount = 0;

		for(String word : AmazonDataStore.ONE.reviewWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call -> reviewSearch(String cmdTerm);
				this.reviewSearch(word);
				this.partialResultCount += this.resultCount;
			}
		}
		if(this.partialResultCount == 0)	{
			System.out.println("No results found");
		}
		else	{
			System.out.println("\nTotal results found: "+ this.partialResultCount+"\n");
		}

	}

	/**
	 * getQAPartialSearch method is a public method that calls qaPartialSearch method
	 * @param cmdTerm
	 */
	public void getQAPartialSearch(String cmdTerm)	{
		this.qaPartialSearch(cmdTerm);
	}

	/**
	 * qaPartialSearch method prints out the QuesAns Records that partially match the term
	 * @param cmdTerm
	 */
	private void qaPartialSearch(String cmdTerm)	{

		this.partialResultCount = 0;
		for(String word : AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call -> qaSearch(String cmdTerm);
				this.qaSearch(word);
				this.partialResultCount += this.resultCount;
			}
		}
		if(this.partialResultCount == 0)	{
			System.out.println("No results found");
		}
		else	{
			System.out.println("\nTotal results found: "+ this.partialResultCount+"\n");
		}

	}




	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
