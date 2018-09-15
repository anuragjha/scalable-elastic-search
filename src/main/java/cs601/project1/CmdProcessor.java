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

	private AmazonWordDetails userOutput = new AmazonWordDetails();	

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
			this.asinFind(cmdTerm);
			
			break;
		case "reviewsearch" : 
			this.reviewSearch(cmdTerm);
			
			break;
		case "qasearch"	: 
			this.qaSearch(cmdTerm);
			
			break;
		case "reviewpartialsearch" : 
			this.reviewPartialSearch(cmdTerm);
			
			break;
		case "qapartialsearch" : 
			this.qaPartialSearch(cmdTerm);
			
			break;
		}

	}

	public void processCmd(String cmdMethod)	{
		switch(cmdMethod)	{
		case "help"	: System.out.println(help());
		break;
		}
	}

	private void asinFind(String cmdTerm)	{
		//search from hashmap of recid, AmazonReviews
		//search from hashmap of recid, AmazonReviews
		//StringBuilder output = new StringBuilder();
		this.resultCount = 0;

		for(AmazonReviews review : AmazonDataStore.ONE.reviewDataStore.values())	{
			if(review.getAsin().toLowerCase().equals(cmdTerm))	{
				//output.append(review.toString());
				this.resultCount += 1;
				System.out.println(review.toString());
			}
		}

		for(AmazonQuesAns quesAns : AmazonDataStore.ONE.quesAnsDataStore.values())	{
			if(quesAns.getAsin().toLowerCase().equals(cmdTerm))	{
				//output.append(quesAns.toString());
				this.resultCount += 1;
				System.out.println(quesAns.toString());
			}
		}

		if(resultCount == 0)	{
			//String noOfResults = "Total results found: "+ resultCount;
			//output.append(noOfResults);
			//return output.toString();
			System.out.println("No results found");
		}
		else	{
			//return output.append("No results found").toString();
			System.out.println("\nResults found: "+ this.resultCount+"\n");
		}

	}

	
	private void reviewSearch(String cmdTerm)	{
		//StringBuilder output = new StringBuilder();
		this.resultCount = 0;
		
		if(AmazonDataStore.ONE.reviewWordDataStore.getIndex().containsKey(cmdTerm))	{
			//put the new datastructure to have sorted search result 
			//userOutput.createSortedOutput(AmazonDataStore.ONE.reviewWordDataStore.searchWord(cmdTerm)).keySet();
			
			//AmazonDataStore.ONE.reviewWordDataStore.searchWord(cmdTerm)).keySet() ->
			//  returns sorted KeySet()
			for(int recordId : userOutput.createSortedOutput(
					AmazonDataStore.ONE.reviewWordDataStore.searchWord(cmdTerm)).keySet())	{
				this.resultCount += 1;
				//output.append(AmazonDataStore.ONE.reviewDataStore.get(recordId).toString());
				System.out.println(AmazonDataStore.ONE.reviewDataStore.get(recordId).toString());
			}

			//return output.toString();
			System.out.println("\nResults found: "+ this.resultCount+"\n");
		}
		else	{
			//return output.append("No results found").toString();
			System.out.println("No results found");
		}

	}

	private void qaSearch(String cmdTerm)	{
		//StringBuilder output = new StringBuilder();
		this.resultCount = 0;
		if(AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().containsKey(cmdTerm))	{
			//output.append("Matching term: "+ cmdTerm);
			
			//userOutput.createSortedOutput(AmazonDataStore.ONE.quesAnsWordDataStore.searchWord(cmdTerm)).keySet();
			for(int recordId : userOutput.createSortedOutput(
					AmazonDataStore.ONE.quesAnsWordDataStore.searchWord(cmdTerm)).keySet())	{
				this.resultCount += 1;
				//output.append(AmazonDataStore.ONE.quesAnsDataStore.get(recordId).toString());
				System.out.println(AmazonDataStore.ONE.reviewDataStore.get(recordId).toString());
			}
			//return output.toString();
			System.out.println("\nTotal results found: "+ this.resultCount+"\n");
		}
		else	{
			//return output.append("No results found").toString();
			System.out.println("Results found");
		}

	}

	private void reviewPartialSearch(String cmdTerm)	{
		//StringBuilder output = new StringBuilder();
		this.partialResultCount = 0;
		for(String word : AmazonDataStore.ONE.reviewWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call ->
				//reviewSearch(String cmdTerm);
				//output.append("Partial search ResultCount: "+ resultCount+1);
				this.reviewSearch(word);
				this.partialResultCount += this.resultCount;
			}
		}
		if(partialResultCount == 0)	{
			System.out.println("No results found");
			//return output.toString();
		}
		else	{
			//return output.append("No results found").toString();
			System.out.println("\nTotal results found: "+ this.partialResultCount+"\n");
		}


	}

	private void qaPartialSearch(String cmdTerm)	{
		//StringBuilder output = new StringBuilder();
		this.partialResultCount = 0;
		for(String word : AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call ->
				//qaSearch(String cmdTerm);
				qaSearch(word);
				this.partialResultCount += this.resultCount;
			}
		}
		if(partialResultCount == 0)	{
			System.out.println("No results found");
			//return output.toString();
		}
		else	{
			//return output.append("No results found").toString();
			System.out.println("\nTotal results found: "+ this.partialResultCount+"\n");
		}

	}


	private String help()	{
		return "****Valid Commands are ****\n1. find <asin>\n2. reviewsearch <term>\n3. qasearch <term>\n"
				+ "4. reviewpartialsearch <term>\n5. qapartialsearch <term>\n____________________________";
	}

	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
