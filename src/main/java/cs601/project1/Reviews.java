package cs601.project1;

import java.util.LinkedList;

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
	
	private static int recordCounter = 0; //need for unique record id
	private static final String RecordType = "rv";
	private String recordId;
	
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
		
		Reviews.recordCounter += 1;
		recordId = Reviews.RecordType+String.valueOf(Reviews.recordCounter);
		
		//System.out.println("yay !! review object, counter: " + recordCounter);
		//so if everything is ok.. send review record to DataStore
		// send the data to DataStore
		this.sendToDataStore();
		
	}
	
	public void sendToDataStore()	{
		DataStore1.ONE.updateDataStore(this);
	}
	
	//getters
	public String getRecordType()	{
		return RecordType;
	}

	public String getRecordId() {
		return recordId;
	}

	
	public String toString()	{
		return this.reviewerID+"\n"+this.asin+"\n"+this.reviewername+"\n"+this.helpful+"\n"+
				this.reviewText+"\n"+this.overall+"\n"+this.summary+"\n"+this.unixReviewTime +
				"\n"+this.reviewTime+"\n";
	}
	
	private String getStringToGetWords()	{
		//return this.reviewerID+" "+
		//		this.asin+" "+
		//		this.reviewername+" "+ 
		return		this.reviewText+" "+
				this.summary;
	}
	
	public String[] getWords()	{
		String[] wordsInRecord = this.getStringToGetWords().split(" ");
		for(int i = 0; i < wordsInRecord.length; i++)	{
			//System.out.println("s: "+ wordsInRecord[i]);
			wordsInRecord[i] = wordsInRecord[i].replaceAll(",","").trim().toLowerCase(); //put regex to exclude everything except alpha numeric
			//System.out.println("s: "+ wordsInRecord[i]);
		}
		return wordsInRecord;
		
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Reviews r = new Reviews("Anu123","23094jshdf","Anurag",new int[] {7,8} , "just testing but dummy serious", 6.0,
				"dummy ok, dummy is summary , but creating dummy record", "23-40sdf", "sdf02323");
		System.out.println(r.getRecordId());
		System.out.println(r.toString());
		r.getWords();
		System.out.println("keyWordStore: "+DataStore1.ONE.keyWordStore);
		Reviews r1= new Reviews("Anu123","23094jshdf","Anurag",new int[] {7,8} , "just testing nothing serious", 6.0,
				"yea ok, this but is summary, creating second dummy record", "23-40sdf", "sdf02323");
		System.out.println(r1.getRecordId());
		System.out.println(r1.toString());
		System.out.println("keyWordStore: "+DataStore1.ONE.keyWordStore);
		Reviews r2= new Reviews("Anu34123","2309hdf","Andcurag",new int[] {7,8} , "just testing nothing serious", 6.0,
				"yea ok, this is summary, creating but second dumafmy record", "23-40sdf", "sdf02323");
		System.out.println(r2.toString());
	}

}
