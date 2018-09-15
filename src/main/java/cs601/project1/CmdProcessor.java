/**
 * 
 */
package cs601.project1;

/**
 * @author anuragjha
 *
 */
public class CmdProcessor {

	private int resultCount = 0;
	private int partialResultCount = 0;
	
	/**
	 * method starts to check if the user inputs are valid
	 * @param cmdList
	 * @return
	 */
	public boolean isCmdValid(String[] cmdList)	{
		return cmdValidityCheck(cmdList);
	}

	//
	private boolean cmdValidityCheck(String[] cmdList)	{

		String validCmds = "find, reviewsearch, qasearch, reviewpartialsearch, qapartialsearch";
		//String cmdList[] = cmd.split(" ");
		if(cmdList.length == 1)	{
			if(cmdList[0].equals("help"))	{
				return true;
			}
		}
		if(cmdList.length == 2)	{
			//System.out.println("first loop");
			if(validCmds.contains(cmdList[0].toLowerCase()))	{
				//	System.out.println("second loop");
				//	if(cmdList[1].replaceAll("[^A-Za-z0-9]", "").toLowerCase().matches("[a-zA-Z0-9]+"))	{
				//		System.out.println("third loop");
				return true;
				//	}
			}
		}
		return false;
	}


	public void processCmd(String cmdMethod, String cmdTerm)	{

		switch(cmdMethod)	{
		case "find"	: 
			System.out.println(this.asinFind(cmdTerm));
			System.out.println("Total results found: "+ this.resultCount+"\n");
			break;
		case "reviewsearch" : 
			System.out.println(this.reviewSearch(cmdTerm));
			System.out.println("Total results found: "+ this.resultCount+"\n");
			break;
		case "qasearch"	: 
			System.out.println(this.qaSearch(cmdTerm));
			System.out.println("Total results found: "+ this.resultCount+"\n");
			break;
		case "reviewpartialsearch" : 
			System.out.println(this.reviewPartialSearch(cmdTerm));
			System.out.println("Total results found: "+ this.partialResultCount+"\n");
			break;
		case "qapartialsearch" : 
			System.out.println(this.qaPartialSearch(cmdTerm));
			System.out.println("Total results found: "+ this.partialResultCount+"\n");
			break;
		}

	}

	public void processCmd(String cmdMethod)	{
		switch(cmdMethod)	{
		case "help"	: System.out.println(help());
		break;
		}
	}

	private String asinFind(String cmdTerm)	{
		//search from hashmap of recid, AmazonReviews
		//search from hashmap of recid, AmazonReviews
		StringBuilder output = new StringBuilder();
		this.resultCount = 0;

		for(AmazonReviews review : AmazonDataStore.ONE.reviewDataStore.values())	{
			if(review.getAsin().toLowerCase().equals(cmdTerm))	{
				output.append(review.toString());
				this.resultCount += 1;
			}
		}

		for(AmazonQuesAns quesAns : AmazonDataStore.ONE.quesAnsDataStore.values())	{
			if(quesAns.getAsin().toLowerCase().equals(cmdTerm))	{
				output.append(quesAns.toString());
				this.resultCount += 1;
			}
		}

		if(output.length() > 1)	{
			//String noOfResults = "Total results found: "+ resultCount;
			//output.append(noOfResults);
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}

	}

	
	private String reviewSearch(String cmdTerm)	{
		StringBuilder output = new StringBuilder();
		this.resultCount = 0;
		
		if(AmazonDataStore.ONE.reviewWordDataStore.getIndex().containsKey(cmdTerm))	{
			//output.append("Matching term: "+ cmdTerm);
			for(int recordId : AmazonDataStore.ONE.reviewWordDataStore.searchWord(cmdTerm))	{
				this.resultCount += 1;
				output.append(AmazonDataStore.ONE.reviewDataStore.get(recordId).toString());
			}

			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}

	}

	private String qaSearch(String cmdTerm)	{
		StringBuilder output = new StringBuilder();
		this.resultCount = 0;
		if(AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().containsKey(cmdTerm))	{
			//output.append("Matching term: "+ cmdTerm);
			for(int recordId : AmazonDataStore.ONE.quesAnsWordDataStore.searchWord(cmdTerm))	{
				this.resultCount += 1;
				output.append(AmazonDataStore.ONE.quesAnsDataStore.get(recordId).toString());
			}
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}

	}

	private String reviewPartialSearch(String cmdTerm)	{
		StringBuilder output = new StringBuilder();
		this.partialResultCount = 0;
		for(String word : AmazonDataStore.ONE.reviewWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call ->
				//reviewSearch(String cmdTerm);
				//output.append("Partial search ResultCount: "+ resultCount+1);
				output.append(reviewSearch(word));
				this.partialResultCount += this.resultCount;
			}
		}
		if(output.length() > 1)	{
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}


	}

	private String qaPartialSearch(String cmdTerm)	{
		StringBuilder output = new StringBuilder();
		this.partialResultCount = 0;
		for(String word : AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call ->
				//qaSearch(String cmdTerm);
				output.append(qaSearch(word));
				this.partialResultCount += this.resultCount;
			}
		}
		if(output.length() > 1)	{
			return output.toString();
		}
		else	{
			return output.append("No results found").toString();
		}

	}


	private String help()	{
		return "Valid Commands are:\nfind <asin>\nreviewsearch <term>\nqasearch <term>\n"
				+ "reviewpartialsearch <term>\nqapartialsearch <term>";
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
