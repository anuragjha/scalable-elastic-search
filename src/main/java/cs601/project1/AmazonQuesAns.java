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
	////private String questionType;
	private String asin;
	////private String answerTime; // have to find correct datatype
	////private long unixTime; //have to find correct datatype
	private String question;
	////private String answerType; //in record when question type is Yes/No
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
	public AmazonQuesAns()	{
		
	}


	//getters
	public String getAsin() {
		return asin;
	}
	
	public String getRecordId() {
		return recordId;
	}
	
	//increment counter
	private void incrementCounter()	{
		AmazonQuesAns.recordCounter += 1;
		this.recordId = AmazonQuesAns.recordType + AmazonQuesAns.recordCounter;
	}
	
	
	//notify DataStore
	public void notifyDataStore()	{
		this.incrementCounter();
		AmazonDataStore.ONE.newRecord(this);
	}
	
	
	public String toString()	{
		return "\n\n" + this.recordId + "\n" + this.asin + "\n" + this.question + "\n" + this.answer;
	
	}
	
	
	/**
	 * 
	 * @return
	 */
	public String getWords()	{
		//String forWordDataStore = new String(this.question+this.answer);
		///return	forWordDataStore.toString().replaceAll("[^A-Za-z0-9\']", " ").toLowerCase();
		return (this.question+this.answer);
	}
	
	

	
	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
