/**
 * 
 */
package cs601.project1;

import java.util.Map;

/**
 * @author anuragjha
 * CmdProcessor class contains the execution logic for the User commands
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

	/**
	 * cmdValidityCheck method implements the checking for the user commands
	 * @param cmdList
	 * @return true/false
	 */
	private boolean cmdValidityCheck(String[] cmdList)	{

		String[] validCmds = {"find", "reviewsearch", "qasearch", "reviewpartialsearch", "qapartialsearch"};
		//String cmdList[] = cmd.split(" ");
		if(cmdList.length == 1)	{
			if(cmdList[0].toLowerCase().equals("help"))	{
				return true;
			}
		}
		if(cmdList.length == 2)	{
			//System.out.println("first loop");
			for(String validCmd : validCmds)	{
				if(validCmd.matches(cmdList[0].toLowerCase()))	{
					//	System.out.println("second loop");
					//	if(cmdList[1].replaceAll("[^A-Za-z0-9]", "").toLowerCase().matches("[a-zA-Z0-9]+"))	{
					//		System.out.println("third loop");
					return true;
					//	}
				}
			}
		}
		return false;
	}

	/**
	 * processCmd method takes in 2 parameters to execute the user command
	 * @param cmdMethod
	 * @param cmdTerm
	 */
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

	/**
	 * processCmd method takes in 1 parameter to execute the user command
	 * @param cmdMethod
	 */
	public void processCmd(String cmdMethod)	{
		switch(cmdMethod)	{
		case "help"	: System.out.println(help());
		break;
		}
	}

	/**
	 * asinFind method finds all the Review and QA record for matching ASIN
	 * @param cmdTerm
	 */
	private void asinFind(String cmdTerm)	{

		this.resultCount = 0;

		for(AmazonReviews review : AmazonDataStore.ONE.reviewDataStore.values())	{
			if(review.getAsin().equalsIgnoreCase(cmdTerm))	{
				//output.append(review.toString());
				this.resultCount += 1;
				System.out.println("\n"+review.toString());
			}
		}

		for(AmazonQuesAns quesAns : AmazonDataStore.ONE.quesAnsDataStore.values())	{
			if(quesAns.getAsin().equalsIgnoreCase(cmdTerm))	{
				//output.append(quesAns.toString());
				this.resultCount += 1;
				System.out.println("\n"+quesAns.toString());
			}
		}

		if(resultCount == 0)	{
			System.out.println("No results found");
		}
		else	{
			System.out.println("\nResults found: "+ this.resultCount+"\n");
		}

	}


	/**
	 * reviewSearch method prints out the Review Records that match the term
	 * @param cmdTerm
	 */
	public void reviewSearch(String cmdTerm)	{

		this.resultCount = 0;

		if(AmazonDataStore.ONE.reviewWordDataStore.getIndex().containsKey(cmdTerm))	{ //word in store
			//put the new data structure to have sorted search result 
			for(Map.Entry<Integer, Integer> recordId : (userOutput.createSortedOutput(
					AmazonDataStore.ONE.reviewWordDataStore.searchWord(cmdTerm))).entrySet())	{
				this.resultCount += 1;
				System.out.println("\nSearched term: "+ cmdTerm + "\t|\tFrequency: "+recordId.getValue());
				System.out.println(AmazonDataStore.ONE.reviewDataStore.get(recordId.getKey()).toString());
			}
			System.out.println("\nResults found: "+ this.resultCount+"\n");
		}
		else	{
			System.out.println("No result found");
		}

	}
	
	
	/**
	 * qaSearch method prints out the QuesAns Records that match the term
	 * @param cmdTerm
	 */
	private void qaSearch(String cmdTerm)	{
		
		this.resultCount = 0;
		
		if(AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().containsKey(cmdTerm))	{ //word in store
			//put the new data structure to have sorted search result 
			for(Map.Entry<Integer, Integer> recordId : (userOutput.createSortedOutput(
					AmazonDataStore.ONE.quesAnsWordDataStore.searchWord(cmdTerm))).entrySet())	{
				this.resultCount += 1;
				System.out.println("\nSearched term: "+ cmdTerm + "\t|\tFrequency: "+recordId.getValue());
				System.out.println(AmazonDataStore.ONE.reviewDataStore.get(recordId.getKey()).toString());
			}
			System.out.println("\nResults found: "+ this.resultCount+"\n");
		}
		else	{
			System.out.println("No result found");
		}

	}

	
	/**
	 * reviewPartialSearch method prints out the Review Records that partially match the term
	 * @param cmdTerm
	 */
	private void reviewPartialSearch(String cmdTerm)	{
		
		this.partialResultCount = 0;
		
		for(String word : AmazonDataStore.ONE.reviewWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call -> reviewSearch(String cmdTerm);
				this.reviewSearch(word);
				this.partialResultCount += this.resultCount;
			}
		}
		if(partialResultCount == 0)	{
			System.out.println("No results found");
		}
		else	{
			System.out.println("\nTotal results found: "+ this.partialResultCount+"\n");
		}

	}
	

	/**
	 * qaPartialSearch method prints out the QuesAns Records that partially match the term
	 * @param cmdTerm
	 */
	private void qaPartialSearch(String cmdTerm)	{

		this.partialResultCount = 0;
		
		for(String word : AmazonDataStore.ONE.quesAnsWordDataStore.getIndex().keySet())	{
			if(word.contains(cmdTerm))	{
				//for each word match call -> qaSearch(String cmdTerm);
				qaSearch(word);
				this.partialResultCount += this.resultCount;
			}
		}
		if(partialResultCount == 0)	{
			System.out.println("No results found");
		}
		else	{
			System.out.println("\nTotal results found: "+ this.partialResultCount+"\n");
		}

	}


	public void getHelp()	{
		System.out.println(this.help());
	}
	
	private String help()	{
		return    "____________________________\n" 
				+ "**** Valid Commands are ****\n"
				+ "1. find <asin>\n"
				+ "2. reviewsearch <term>\n"
				+ "3. qasearch <term>\n"
				+ "4. reviewpartialsearch <term>\n"
				+ "5. qapartialsearch <term>\n"
				+ "6. exit\n"
				+ "____________________________";
	}






	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
