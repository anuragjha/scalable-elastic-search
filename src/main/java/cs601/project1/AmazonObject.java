/**
 * 
 */
package cs601.project1;

/**
 * @author anuragjha
 * This class is the parent class for AmazonReviews and AmazonQuesAns classes
 */
public abstract class AmazonObject {

	protected int recordId;
	protected static int recordCounter = 0;
	
	abstract String getStringText();

	abstract int getRecordId();
	
	
	//increment counter
	/**
	 * incrementing static counter and assigning the value to RecordId
	 */
	private void incrementCounter()	{
		AmazonObject.recordCounter += 1;
		this.recordId = AmazonObject.recordCounter;
	}

	
	/**
	 * sends the new AmazonObject record to DataStore
	 */
	public void notifyDataStore()	{
		this.incrementCounter();
		//notify DataStore
		AmazonDataStore.ONE.newRecord(this);
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
