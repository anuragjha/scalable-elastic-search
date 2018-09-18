/**
 * 
 */
package cs601.project1;

/**
 * @author anuragjha
 * This class is the parent class for AmazonReviews and AmazonQuesAns classes
 */
public abstract class AmazonObject {

	abstract String getStringText();

	abstract int getRecordId();

	abstract void notifyDataStore();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
