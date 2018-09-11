/**
 * 
 */
package cs601.project1;

/**
 * @author anuragjha
 *
 *		AmazonQuesAns class holds data for QuesAns type record and notifies DataStore after successful initialization
 */
public class AmazonQuesAns extends AmazonObject {
	
	//record type
	private static final String recordType = "qa";
	//class variable - counter
	private static int recordCounter = 0;    //class variable recordCounter initializing 
	private String recordId;  	//instance variable for each record
	// data field for QuesAns type record
	private String questionType;
	private String asin;
	private String answerTime; // have to find correct datatype
	private String unixTime; //have to find correct datatype
	private String question;
	private String answerType; //in record when question type is Yes/No
	private String answer;
	
	
	/**
	 *  constructor method
	 * @param questionType
	 * @param asin
	 * @param answerTime
	 * @param unixTime
	 * @param question
	 * @param answerType
	 * @param answer
	 */
	public AmazonQuesAns(String questionType, String asin, String answerTime, String unixTime,
			String question, String answerType, String answer) {
		super();
		AmazonQuesAns.recordCounter += 1;
		this.recordId = AmazonQuesAns.recordType + AmazonQuesAns.recordCounter;
		this.questionType = questionType;
		this.asin = asin;
		this.answerTime = answerTime;
		this.unixTime = unixTime;
		this.question = question;
		this.answerType = answerType;
		this.answer = answer;
		
		// initialization complete - now notify DataStore
		//System.out.println(this.toString());
		AmazonDataStore.ONE.newRecord(this);
	}


	//getters
	public String getAsin() {
		return asin;
	}
	
	public String getRecordId() {
		return recordId;
	}
	
	
	
	public String toString()	{
		return this.recordId + " " + this.questionType + " " + this.asin + " " + 
				this.answerTime + " " + this.unixTime + " " + this.question + " " +
				this.answerType + " " + this.answer;
	
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getWords()	{
		//return this.reviewerID+" "+
		//		this.asin+" "+
		//		this.reviewername+" "+ 
		//"[^A-Za-z0-9\']", " ").toLowerCase();
		StringBuilder forWordDataStore = new StringBuilder();
		forWordDataStore.append(this.question);
		forWordDataStore.append(this.answer);
		return	forWordDataStore.toString().replaceAll("[^A-Za-z0-9\']", " ").toLowerCase();
	}
	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
