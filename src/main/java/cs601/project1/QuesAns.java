package cs601.project1;

public class QuesAns {

	private String questionType;
	private String asin;
	private String answerTime; // have to find correct datatype
	private String unixTime; //have to find correct datatype
	private String question;
	private String answerType; //in record when question type is Yes/No
	private String answer;
	
	private static int counter = 0;
	
	
	public QuesAns(String questionType, String asin, String answerTime, String unixTime, String question,
			String answerType, String answer) {
		super();
		QuesAns.counter += 1;
		this.questionType = questionType;
		this.asin = asin;
		this.answerTime = answerTime;
		this.unixTime = unixTime;
		this.question = question;
		this.answerType = answerType;
		this.answer = answer;
		//send data to DataStore
		System.out.println("yay !! QuestionType1: counter: "+QuesAns.counter);
		
	}
	
	public QuesAns(String questionType, String asin, String answerTime, String unixTime, 
			String question, String answer) {
		super();
		QuesAns.counter += 1;
		this.questionType = questionType;
		this.asin = asin;
		this.answerTime = answerTime;
		this.unixTime = unixTime;
		this.question = question;
		//this.answerType = answerType;
		this.answer = answer;
		//send data to DataStore
		System.out.println("yay !! QuestionType2: counter: "+QuesAns.counter);
		
	}




	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
