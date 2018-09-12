/**
 * 
 */
package cs601.project1;

/**
 * @author anuragjha
 *
 *	AmazonReviews class holds the Review type record and notifies DataStore after successful initialization
 */
public class AmazonReviews extends AmazonObject {
	
	//record type
	private static final String recordType = "rv";
	//class variable - counter
	private static int recordCounter = 0;    //class variable recordCounter initializing 
	private String recordId;			// instance variable for each record
	//data fields for Review record type
	////private String reviewerID;
	private String asin;
	////private String reviewerName;
	////private int[] helpful; // array of 2 ints -  int[2]
	private String reviewText;
	////private double overall;
	////private String summary;
	////private long unixReviewTime; // will have to find correct datatype
	////private String reviewTime; //will have to find correct datatype
	

	
	/**
	 *  constructor method
	 * @param reviewerID
	 * @param asin
	 * @param reviewerName
	 * @param helpful
	 * @param reviewText
	 * @param overall
	 * @param summary
	 * @param unixReviewTime
	 * @param reviewTime
	 */

	public AmazonReviews()	{

	}
	
	//getters
	public String getAsin() {
		return this.asin;
	}
	
	public String getRecordId()	{
		return this.recordId;
	}
	
	//increment counter
	private void incrementCounter()	{
		AmazonReviews.recordCounter += 1;
		this.recordId = AmazonReviews.recordType + AmazonReviews.recordCounter;
	}
	
	//notify DataStore
	public void notifyDataStore()	{
		this.incrementCounter();
		AmazonDataStore.ONE.newRecord(this);
	}
	

	/**
	 * overriding toString method
	 */
	public String toString()	{
		return "\n\n" + this.recordId + "\n" +  this.asin + "\n" + this.reviewText ;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getWords()	{
		//return	this.reviewText.replaceAll("[^A-Za-z0-9]", " ").toLowerCase();  
		return this.reviewText;
	}
	
	public String getStringText()	{
		//return	this.reviewText.replaceAll("[^A-Za-z0-9]", " ").toLowerCase();  
		return this.reviewText;
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
