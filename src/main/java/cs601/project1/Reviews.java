package cs601.project1;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class Reviews {

	private String reviewerID;
	private String asin;
	private String reviewername;
	private int[] helpful; // array of 2 ints -  int[2]
	private String reviewText;
	private double overall;
	private String summary;
	private String unixReviewTime; // will have to find correct datatype
	private String reviewTime; //will have to find correct datatype
	
	private static int counter = 0;
	
	
	public Reviews(String reviewerID, String asin, String reviewername, int[] helpful, String reviewText,
			double overall, String summary, String unixReviewTime, String reviewTime) {
		super();
		this.reviewerID = reviewerID;
		this.asin = asin;
		this.reviewername = reviewername;
		this.helpful = helpful;
		this.reviewText = reviewText;
		this.overall = overall;
		this.summary = summary;
		this.unixReviewTime = unixReviewTime;
		this.reviewTime = reviewTime;
		// send the data to DataStore
		Reviews.counter += 1;
		System.out.println("yay !! review object, counter: " + counter);
		//so if everything is ok.. send review record to DataStore
	}

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
