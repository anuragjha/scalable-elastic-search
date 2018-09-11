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
	private String reviewerID;
	private String asin;
	private String reviewerName;
	private int[] helpful; // array of 2 ints -  int[2]
	private String reviewText;
	private double overall;
	private String summary;
	private String unixReviewTime; // will have to find correct datatype
	private String reviewTime; //will have to find correct datatype
	
	
	
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
	public AmazonReviews(String reviewerID, String asin, String reviewerName, int[] helpful,
			String reviewText, double overall, String summary, String unixReviewTime, String reviewTime) {
		super();
		AmazonReviews.recordCounter += 1;
		this.recordId = AmazonReviews.recordType + AmazonReviews.recordCounter;
		this.reviewerID = reviewerID;
		this.asin = asin;
		this.reviewerName = reviewerName;
		this.helpful = helpful;
		this.reviewText = reviewText;
		this.overall = overall;
		this.summary = summary;
		this.unixReviewTime = unixReviewTime;
		this.reviewTime = reviewTime;
		
		// initialization complete - now notify DataStore
		//System.out.println(this.toString());
		AmazonDataStore.ONE.newRecord(this);
	}

	//getters
	public String getAsin() {
		return this.asin;
	}
	
	public String getRecordId()	{
		return this.recordId;
	}
	
	

	/**
	 * overriding toString method
	 */
	public String toString()	{
		return this.recordId + " " + this.reviewerID + " " + this.asin + " " + 
				this.reviewerName + " " + this.helpful + " " + this.reviewText + " " + 
				this.overall + " " + this.summary + " " + this.unixReviewTime + " " + 
				this.reviewTime;
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getWords()	{
		//return this.reviewerID+" "+
		//		this.asin+" "+
		//		this.reviewerName+" "+ 
		return	this.reviewText.replaceAll("[^A-Za-z0-9\']", " ").toLowerCase();  
		// !!! change this to also remove fullStop
	}



	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
