package cs601.project1;

public class QuesAns {

	private String questionType;
	private String asin;
	private String answerTime; // have to find correct datatype
	private String unixTime; //have to find correct datatype
	private String question;
	private String answerType; //in record when question type is Yes/No
	private String answer;
	
	private static int recordCounter = 0; //need for unique record id
	private static final String RecordType = "qa";
	private String recordId;
	
	
	public QuesAns(String questionType, String asin, String answerTime, String unixTime, String question,
			String answerType, String answer) {
		super();
		
		this.questionType = questionType;
		this.asin = asin;
		this.answerTime = answerTime;
		this.unixTime = unixTime;
		this.question = question;
		this.answerType = answerType;
		this.answer = answer;
		
		QuesAns.recordCounter += 1;
		this.recordId = QuesAns.RecordType+String.valueOf(QuesAns.recordCounter);

		//send data to DataStore
		////System.out.println("yay !! QuestionType: counter: "+QuesAns.recordCounter);
		this.sendToDataStore();
	}

	public void sendToDataStore()	{
		DataStore1.ONE.updateQaDataStore(this);
	}
	
	//getters
	public String getRecordType()	{
		return RecordType;
	}

	public String getRecordId() {
		return recordId;
	}
	
	public String toString()	{
		return this.questionType+"\n"+this.asin+"\n"+this.answerTime+"\n"+this.unixTime+"\n"+
				this.question+"\n"+this.answerType+"\n"+this.answer+"\n";
	}
	
	private String getStringToGetWords()	{
		return		this.question+" "+this.answer;
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

	}

}
